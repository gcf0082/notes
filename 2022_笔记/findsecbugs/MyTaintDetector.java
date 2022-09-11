/**
 * Find Security Bugs
 * Copyright (c) Philippe Arteau, All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */
package com.h3xstream.findsecbugs.injection;

import com.h3xstream.findsecbugs.BCELUtil;
import com.h3xstream.findsecbugs.taintanalysis.Taint;
import com.h3xstream.findsecbugs.taintanalysis.TaintFrame;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.Priorities;
import edu.umd.cs.findbugs.SourceLineAnnotation;
import edu.umd.cs.findbugs.ba.AnalysisContext;
import edu.umd.cs.findbugs.ba.ClassContext;
import edu.umd.cs.findbugs.ba.DataflowAnalysisException;

import java.util.*;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InvokeInstruction;
import org.apache.bcel.generic.Type;

/**
 * Detector designed for extension to detect injection vulnerabilities
 *
 * @author David Formanek (Y Soft Corporation, a.s.)
 */
public class MyTaintDetector extends AbstractTaintDetector {

    protected final Map<ClassMethodSignature, Set<InjectionSink>> injectionSinks = new HashMap<>();
    private final Map<MethodAndSink, Taint> sinkTaints = new HashMap<MethodAndSink, Taint>();

    public MyTaintDetector(BugReporter bugReporter) {
        super(bugReporter);
    }

    /**
     * Once the analysis is completed, all the collected sinks are reported as bugs.
     */
    @Override
    public void report() {
        // collect sinks and report each once
        Set<InjectionSink> injectionSinksToReport = new HashSet<InjectionSink>();
        for (Set<InjectionSink> injectionSinkSet : injectionSinks.values()) {
            for (InjectionSink injectionSink : injectionSinkSet) {
                injectionSinksToReport.add(injectionSink);
            }
        }
        for (InjectionSink injectionSink : injectionSinksToReport) {
            bugReporter.reportBug(injectionSink.generateBugInstance(false));
        }
    }

    @Override
    protected void analyzeLocation(ClassContext classContext, Method method, InstructionHandle handle,
                                   ConstantPoolGen cpg, InvokeInstruction invoke, TaintFrame fact, ClassMethodSignature classMethodSignature)
            throws DataflowAnalysisException {
        SourceLineAnnotation sourceLine = SourceLineAnnotation.fromVisitedInstruction(classContext, method, handle);
        //checkSink(cpg, invoke, fact, sourceLine, classMethodSignature);
        InjectionPoint injectionPoint = getInjectionPoint(invoke, cpg, handle);
        for (int offset : injectionPoint.getInjectableArguments()) {
            int priority = getPriorityFromTaintFrame(fact, offset);
            if (priority == Priorities.IGNORE_PRIORITY) {
                continue;
            }

            Taint parameterTaint = fact.getStackValue(offset);

            String injectableMethod = invoke.getClassName(cpg).replaceAll("\\.","/")+"."+invoke.getMethodName(cpg)+invoke.getSignature(cpg);
            InjectionSink injectionSink = new InjectionSink(this, injectionPoint.getBugType(), priority,
                    classContext, method, handle, injectableMethod, offset);
            injectionSink.addLines(parameterTaint.getAllLocations());
            injectionSink.addSources(parameterTaint.getSources());
            if (parameterTaint.hasParameters()) {
                int numArguments = fact.getNumArguments(invoke, cpg);
                Set<Integer> parameters = parameterTaint.getParameters();
                 for(int paramter : parameters) {
                     //如果参数位置为0，认为参数来自this，而不是函数的参数，需要忽略调
                     int pos = getMethodParameterPos(method,paramter);
                     if (pos == 0) {
                         continue;
                     }
                    System.out.print(sourceLine.getStartLine()+" "+ pos +" > " + (numArguments-offset) + " ");
                    System.out.println(classContext.toString()+"#"+method.getName() + " > " + invoke.getClassName(cpg)+"#"+ invoke.getMethodName(cpg));
                }
                //System.out.println(classMethodSignature.getClassName()+"#"+classMethodSignature.getMethodName());
                // add sink to multi map
                ///Set<InjectionSink> sinkSet = injectionSinks.get(classMethodSignature);
                //if (sinkSet == null) {
                //    sinkSet = new HashSet<InjectionSink>();
                //}
                //assert !sinkSet.contains(injectionSink) : "duplicate sink";
                //sinkSet.add(injectionSink);
                //injectionSinks.put(classMethodSignature, sinkSet);
                //sinkTaints.put(new MethodAndSink(classMethodSignature, injectionSink), parameterTaint);
            } else {
                // sink cannot be influenced by other methods calls, so report it immediately
                //bugReporter.reportBug(injectionSink.generateBugInstance(true));
            }
        }
    }

    //获取调整后的参数个数，如果类型是long或者double，参数个数+1
    private int getMethodParameterPos(Method method, int paramter) {
        int offset = 0;
        int index = 0;
        Type[] types = method.getArgumentTypes();
        int length = method.getArgumentTypes().length;
        for(int i = length-1; i>=0; i--) {
            if (paramter <= offset) {
                break;
            }

            if (types[i].getSignature().equals("J") || types[i].getSignature().equals("D")) {
                offset = offset + 2;
            } else {
                offset = offset + 1;
            }

            if (paramter < offset) {
                break;
            } else if (paramter == offset){
                index = index + 1;
                break;
            }
            index = index + 1;
        }
        return method.getArgumentTypes().length - index;
    }

    /**
     * The default implementation of <code>getPriorityFromTaintFrame()</code> can be overridden if the detector must base its
     * priority on multiple parameters or special conditions like constant values.
     *
     * By default, this method will call the <code>getPriority()</code> method with the parameter taint at the specified offset.
     *
     * @param fact The TaintFrame for the inspected instruction call.
     * @param offset The offset of the checked parameter.
     * @return Priorities interface values from 1 to 5 (Enum-like interface)
     * @throws DataflowAnalysisException An exception thrown when the TaintFrame cannot be analyzed.
     */
    protected int getPriorityFromTaintFrame(TaintFrame fact, int offset)
            throws DataflowAnalysisException {
        Taint parameterTaint = fact.getStackValue(offset);
        return getPriority(parameterTaint);
    }

    /**
     * The default implementation of <code>getPriority()</code> can be overridden if the severity and the confidence for risk
     * is particular.
     *
     * By default, injection will be rated "High" if the complete link between source and sink is made.
     * If it is not the case but concatenation with external source is made, "Medium" is used.
     *
     * @param taint Detail about the state of the value passed (Cumulative information leading to the variable passed).
     * @return Priorities interface values from 1 to 5 (Enum-like interface)
     */
    protected int getPriority(Taint taint) {
        return Priorities.NORMAL_PRIORITY;
    }

    private void checkSink(ConstantPoolGen cpg, InvokeInstruction invoke, TaintFrame fact,
                           SourceLineAnnotation line, ClassMethodSignature classMethodSignature) throws DataflowAnalysisException {
        for (MethodAndSink methodAndSink : getSinks(cpg, invoke, fact)) {
            Taint sinkTaint = sinkTaints.get(methodAndSink);
            assert sinkTaint != null : "sink taint not stored in advance";
            Set<Integer> taintParameters = sinkTaint.getParameters();
            Taint finalTaint = Taint.valueOf(sinkTaint.getNonParametricState());
            for (Integer offset : taintParameters) {
                Taint parameterTaint = fact.getStackValue(offset);
                finalTaint = Taint.merge(finalTaint, parameterTaint);
            }
            if (finalTaint == null) {
                continue;
            }
            if (!sinkTaint.isSafe() && sinkTaint.hasTags()) {
                for (Taint.Tag tag : sinkTaint.getTags()) {
                    finalTaint.addTag(tag);
                }
            }
            if (sinkTaint.isRemovingTags()) {
                for (Taint.Tag tag : sinkTaint.getTagsToRemove()) {
                    finalTaint.removeTag(tag);
                }
            }
            InjectionSink sink = methodAndSink.getSink();
            if (finalTaint.hasParameters()) {
                Set<InjectionSink> sinkSet = injectionSinks.get(classMethodSignature);
                if (sinkSet == null) {
                    sinkSet = new HashSet<InjectionSink>();
                }
                sinkSet.add(sink);
                injectionSinks.put(classMethodSignature, sinkSet);
                sinkTaints.put(new MethodAndSink(classMethodSignature, sink), finalTaint);
            } else {
                // confirm sink to be tainted or called only with safe values
                sink.updateSinkPriority(getPriority(finalTaint));
            }
            //if (!finalTaint.isSafe()) {
            sink.addLine(line);
            sink.addLines(finalTaint.getAllLocations());
            //}
        }
    }

    private Set<MethodAndSink> getSinks(ConstantPoolGen cpg, InvokeInstruction invoke, TaintFrame frame) {
        ClassMethodSignature classMethodSignature = new ClassMethodSignature(
                getInstanceClassName(cpg, invoke, frame), invoke.getMethodName(cpg), invoke.getSignature(cpg));

        Set<InjectionSink> sinks = injectionSinks.get(classMethodSignature);
        if (sinks != null) {
            assert !sinks.isEmpty() : "empty set of sinks";
            return getMethodAndSinks(classMethodSignature, sinks);
        }
        try {
            if (classMethodSignature.getClassName().endsWith("]")) {
                // not a real class
                return Collections.emptySet();
            }
            JavaClass javaClass = Repository.lookupClass(classMethodSignature.getClassName());
            assert javaClass != null;
            return getSuperSinks(javaClass, classMethodSignature);
        } catch (ClassNotFoundException ex) {
            AnalysisContext.reportMissingClass(ex);
        }
        return Collections.emptySet();
    }

    private Set<MethodAndSink> getMethodAndSinks(ClassMethodSignature classMethodSignature, Set<InjectionSink> sinks) {
        Set<MethodAndSink> methodAndSinks = new HashSet<MethodAndSink>();
        for (InjectionSink sink : sinks) {
            methodAndSinks.add(new MethodAndSink(classMethodSignature, sink));
        }
        return methodAndSinks;
    }

    private Set<MethodAndSink> getSuperSinks(JavaClass javaClass, ClassMethodSignature classMethodSignature) throws ClassNotFoundException {
        Set<String> classNames = BCELUtil.getParentClassNames(javaClass);

        for (String className : classNames) {
            classMethodSignature.setClassName(className);
            Set<InjectionSink> sinks = injectionSinks.get(classMethodSignature);
            if (sinks != null) {
                return getMethodAndSinks(classMethodSignature, sinks);
            }
        }

        return Collections.emptySet();
    }

    private static String getInstanceClassName(ConstantPoolGen cpg, InvokeInstruction invoke, TaintFrame frame) {
        try {
            int instanceIndex = BCELUtil.getNumArgumentsIncludingObjectInstance(invoke, cpg) - 1;
            if (instanceIndex != -1) {
                assert instanceIndex < frame.getStackDepth();
                Taint instanceTaint = frame.getStackValue(instanceIndex);
                String className = instanceTaint.getRealInstanceClassName();
                if (className != null) {
                    return className;
                }
            }
        } catch (DataflowAnalysisException ex) {
            assert false : ex.getMessage();
        }

        return BCELUtil.getSlashedClassName(cpg, invoke);
    }

     private InjectionPoint getInjectionPoint(
            InvokeInstruction invoke, ConstantPoolGen cpg, InstructionHandle handle) {
        //如果是内部类的构造函数，因为编译器自动加外部引用作为参数，因此参数个数要减1
         int num = invoke.getArgumentTypes(cpg).length;
         if (invoke.getClassName(cpg).contains("$") && invoke.getMethodName(cpg).equals("<init>")) {
             num = num - 1;
         }
         if (num == 0 ) {
             return InjectionPoint.NONE;
         }
        int[] args = new int[num];
        for (int i = 0; i<num; ++i) {
            args[i] = i;
        }
        return new InjectionPoint(args, "");
     }
}
