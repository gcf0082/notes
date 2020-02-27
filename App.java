package com.gcf.test.asm.simple;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import org.apache.commons.io.DirectoryWalker;
import org.neo4j.driver.*;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import static org.neo4j.driver.Values.parameters;
import static org.neo4j.driver.SessionConfig.builder;

public class App extends MyClass2
{

	static Driver driver = null;
	static Session session = null;
    public static void main( String[] args ) throws Exception
    {
    	/*Stream<Path> paths = Files.walk(Paths.get("D:\\"));
    	paths.parallel()
				.filter(filePath->filePath.toString().endsWith(".jar"))
				.forEach(
    			filePath->System.out.println(filePath)
		);*/
		connect_neo4j();
        long start = System.currentTimeMillis();
        getJarFiles("F:\\gcf\\test\\java\\asm\\simple\\target\\").forEach(
               jarFilePath->{ try {
                   handlerJar(jarFilePath.toString());

               } catch (Exception e){
                   System.out.println(e);
               }
               }
    );
        System.out.println("it consumes " + (System.currentTimeMillis() - start) + "ms");
        //test_list_files_DirectoryWalker();

    }

    private static void handlerJar(String jarFilePath) throws  Exception {
        //System.out.println(jarFilePath);
        String jarPath = jarFilePath;
        File jarFile = new File(jarPath);
        ZipFile zipFile = new ZipFile(jarFile);
        zipFile.stream().filter(App::isClassFile)
                .forEach(zipEntry -> {
                    try {
                        String zipEntryName = zipEntry.getName();
                        //System.out.println(zipEntryName);

                        // retrieving a new inputStream - do not extract variable
                        ClassReader classReader = new ClassReader(zipFile.getInputStream(zipEntry));
                        ClassNode classNode = new ClassNode();
                        classReader.accept(classNode,0);
                        ClassModel cm = new ClassModel();
                        cm.name = classNode.name;
                        cm.superName = classNode.superName;
                        cm.signature = classNode.signature;
                        cm.sourceFile = classNode.sourceFile;
                        cm.interfaces = classNode.interfaces;
						Result result = session.run("MERGE (:Class{name:$name, superName:$superName,interfaces:$interfaces})"
								,parameters("name", cm.name, "superName",cm.superName, "interfaces",cm.interfaces) );
                        for (MethodNode methodNode : classNode.methods) {
                        	MethodModel mm = new MethodModel();
                        	mm.name = methodNode.name;
                        	mm.className = cm.name;
                        	mm.descriptor = methodNode.desc;
                        	//mm.exceptions = methodNode.exceptions;
                        	mm.itf = (methodNode.access== Opcodes.ACC_ABSTRACT?true:false);
                        	session.run("MERGE (:Method{name:$name, className:$className, descriptor:$descriptor, itf:$itf})"
							,parameters("name", mm.name, "$className", mm.className,"descriptor",mm.descriptor,"itf",mm.itf));
                            if (true) {
                                InsnList instructions = methodNode.instructions;
                                for (int i = 0; i < instructions.size(); i++) {
                                    AbstractInsnNode abstractInsnNode = instructions.get(i);
                                    if (abstractInsnNode.getType() == AbstractInsnNode.METHOD_INSN) {
                                        MethodInsnNode methodInsnNode = (MethodInsnNode) abstractInsnNode;
                                        System.out.println(methodNode.name+" -> "+ methodInsnNode.name+""+	methodInsnNode.desc/* + " " + methodInsnNode.owner + " "+methodInsnNode.itf*/);
                                    }

                                    /*if (abstractInsnNode.getType() == AbstractInsnNode.LINE) {
                                        LineNumberNode lineNumberNode = (LineNumberNode) abstractInsnNode;
                                        System.out.println(lineNumberNode.line+ " " + lineNumberNode.start.getLabel());
                                    }
                                    if (abstractInsnNode.getType() == AbstractInsnNode.LABEL) {
                                        LabelNode methodInsnNode = (LabelNode) abstractInsnNode;
                                        System.out.println(methodInsnNode.getLabel());
                                    }*/

						 /*if (abstractInsnNode.getType() == AbstractInsnNode.INVOKE_DYNAMIC_INSN) {
							 InvokeDynamicInsnNode methodInsnNode = (InvokeDynamicInsnNode) abstractInsnNode;
							 System.out.println(methodInsnNode.toString());
						 }*/
                                }

				/*java.util.List<LocalVariableNode> vars = methodNode.localVariables;
					 for (int i = 0; i < vars.size(); i++) {
						 LocalVariableNode var = vars.get(i);
						 System.out.println(var.name);
						 System.out.println(var.start.getLabel());
						 System.out.println(var.end.getLabel());

					 }*/
                            }
                        }

                    } catch (IOException e) {
                        System.out.println(e);
                    }
                });
        zipFile.close();
        driver.close();
    }

	
  private static boolean isClassFile(ZipEntry zipEntry) {
    if (zipEntry.getName().endsWith(".class")) {
    	return true;
	} else {
    	return false;
	}
  }

  private static Stream<Path> getJarFiles(String direcory) throws IOException {
	List<Path> jarFiles = new ArrayList<Path>();
	  Files.walkFileTree(Paths.get(direcory),new HashSet<>(), Integer.MAX_VALUE, new FileVisitor<Path>() {
		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
				throws IOException {
			//System.out.println("preVisitDirectory: " + dir);
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
				throws IOException {
			//System.out.println("visitFile: " + file);
			//System.out.println(Thread.currentThread().getId());
			if (file.toString().endsWith(".jar")) {
				jarFiles.add(file);
			}
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc)
				throws IOException {
			System.out.println("visitFileFailed: " + file);
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc)
				throws IOException {
			//System.out.println("postVisitDirectory: " + dir);
			return FileVisitResult.CONTINUE;
		}
	});
	  System.out.println(jarFiles.size());
	  return jarFiles.stream();
	}

	private static void test_list_files_DirectoryWalker () throws IOException {

        FileCleaner testlist = new FileCleaner();
        testlist.clean(new File("D:\\"));
	}

	private static void mytest(MyInterface myInterface,MyClass myclass, App app) {
    	//MyInterface myInterface = new MyClass();
    	myInterface.func1();
		myclass.func2();
		app.app_func1();
	}

	public void app_func1() {

	}

	private static void test_neo4j() {
		Driver driver = GraphDatabase.driver("bolt://192.168.0.101:7687", AuthTokens.basic("neo4j", "admin"));
		try (Session session = driver.session()) {
			Result result = session.run("return \"hello\"");
			System.out.println(result.single().get( 0 ).asString());
		}
		driver.close();
	}

	private static void connect_neo4j() {
		driver = GraphDatabase.driver("bolt://192.168.0.101:7687", AuthTokens.basic("neo4j", "admin"));
		try {
			session = driver.session();
			//Result result = session.run("return \"hello\"");
			//System.out.println(result.single().get( 0 ).asString());
		} catch (Exception ex) {
			System.err.println(ex);
		}

	}

	private static void close_neo4j() {
		driver.close();
	}



}

	class FileCleaner extends DirectoryWalker {

		public FileCleaner() {
			super();
		}

		public List clean(File startDirectory) throws java.io.IOException{
			List results = new ArrayList();
			walk(startDirectory, results);
			return results;
		}

		protected boolean handleDirectory(File directory, int depth, Collection results) {
		    return true;
		}

		protected void handleFile(File file, int depth, Collection results) {
			// delete file and add to list of deleted
			//file.delete();
           // System.out.println(Thread.currentThread().getId());
           // System.out.println(file.getAbsolutePath());
		}
	}
