package com.h3xstream.findsecbugs.injection;

import com.h3xstream.findsecbugs.taintanalysis.Taint;
import com.h3xstream.findsecbugs.taintanalysis.TaintFrame;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.SourceLineAnnotation;
import edu.umd.cs.findbugs.ba.ClassContext;
import edu.umd.cs.findbugs.ba.DataflowAnalysisException;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InvokeInstruction;

import java.util.Set;

//获取数据流，但是字符串相加的场景未解决： str = str1 + "xxx";
public class MyTaintDetector extends AbstractTaintDetector{

    public MyTaintDetector(BugReporter bugReporter) {
        super(bugReporter);
    }

    @Override
    protected void analyzeLocation(ClassContext classContext, Method method, InstructionHandle handle, ConstantPoolGen cpg, InvokeInstruction invoke, TaintFrame fact, ClassMethodSignature classMethodSignature) throws DataflowAnalysisException {
        SourceLineAnnotation sourceLine = SourceLineAnnotation.fromVisitedInstruction(classContext, method, handle);
        //System.out.println(classContext.toString()+"#"+method.getName());
        int numArguments = fact.getNumArguments(invoke, cpg);
        for (int i=0;i<numArguments;++i) {
            Taint taint = fact.getStackValue(i);
            Set<Integer> parameters = taint.getParameters();
            for(int paramter : parameters) {
                System.out.print(""+(method.getArgumentTypes().length-paramter)+" > " + (numArguments-i) + " ");
                System.out.println(classContext.toString()+"#"+method.getName() + " > " + invoke.getClassName(cpg)+"#"+ invoke.getMethodName(cpg));
            }
        }
    }
}
