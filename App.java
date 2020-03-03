package com.gcf;

import com.alibaba.fastjson.JSON;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class Main
{
    MongoClient mongoClient ;
    MongoDatabase db;
    MongoCollection<Document> collection;

    public void init() {
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        MongoDatabase db = mongoClient.getDatabase("callgraph");
        collection = db.getCollection("callgraph");
        collection.drop();
        db.createCollection("callgraph");
        collection = db.getCollection("callgraph");
    }
    public static void main( String[] args ) throws IOException {
        String path = "C:\\Users\\Administrator\\.m2\\repository";
        Main main = new Main();
        main.init();
        main.handleDir(path);
    }

    public boolean handleDir(String dirPath) throws IOException {
        Stream<Path> paths = Files.walk(Paths.get(dirPath));
        paths.parallel().filter(filePath->filterJarFile(filePath)).
                forEach(jarPath->handleJar(jarPath.toString()));
        return true;
    }

    public boolean handleJar(String jarFilePath) {
        File jarFile = new File(jarFilePath);
        JarPojo jarPojo = new JarPojo();
        jarPojo.dirPath = jarFile.getAbsolutePath();
        jarPojo.name = jarFile.getName();
        jarPojo.classes = new ArrayList<ClassPojo>();
        ParseContext parseContext = new ParseContext();
        parseContext.jarPojo = jarPojo;
        try {
            ZipFile zipFile = new ZipFile(jarFile);
            zipFile.stream().filter(zipEntry->((ZipEntry) zipEntry).getName().endsWith(".class")).forEach(zipEntry->{
                        try {
                            InputStream is = zipFile.getInputStream(zipEntry);
                            //System.out.println(((ZipEntry) zipEntry).getName());
                            handleClass(parseContext, is);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

            );
            //writeToFile(parseContext);
            writeToDB(parseContext);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean handleClassesDir(String dirPath) {
        return true;
    }

    public boolean handleClass(ParseContext parseContext, InputStream is){
        try {
            ClassReader classReader = new ClassReader(is);
            ClassNode classNode = new ClassNode();
            classReader.accept(classNode, 0);
            ClassPojo classPojo = new ClassPojo();
            classPojo.className = classNode.name;
            classPojo.sourceFile = classNode.sourceFile;
            classPojo.supserClassName = classNode.superName;
            classPojo.methods = new ArrayList<MethodPojo>();
            parseContext.jarPojo.addClass(classPojo);
            for (MethodNode methodNode : classNode.methods) {
                MethodPojo methodPojo = new MethodPojo();
                methodPojo.invokeMethods = new ArrayList<InvokeMethod>();
                methodPojo.name = methodNode.name;
                classPojo.addMethod(methodPojo);
                InsnList instructions = methodNode.instructions;
                int currentLineNumber = -1;
                for (int i = 0; i < instructions.size(); i++) {
                    AbstractInsnNode abstractInsnNode = instructions.get(i);
                    if (abstractInsnNode.getType() == AbstractInsnNode.METHOD_INSN) {
                        MethodInsnNode methodInsnNode = (MethodInsnNode) abstractInsnNode;
                        MethodPojo callee = new MethodPojo();
                        InvokeMethod invokeMethod = new InvokeMethod();
                        invokeMethod.lineNumber = currentLineNumber;
                        invokeMethod.callee = callee;
                        invokeMethod.callee.className = methodInsnNode.owner;
                        invokeMethod.callee.name = methodInsnNode.name;
                        methodPojo.invokeMethods.add(invokeMethod);
                    }

                    if (abstractInsnNode.getType() == AbstractInsnNode.LINE) {
                        LineNumberNode lineNumberNode = (LineNumberNode) abstractInsnNode;
                        currentLineNumber = lineNumberNode.line;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean filterJarFile(Path filePath) {
        if (filePath.toString().endsWith(".jar")) {
            return true;
        } else {
            return false;
        }

    }

    private synchronized void writeToFile(ParseContext parseContext) {
        String jsonStr = JSON.toJSONString(parseContext.jarPojo);
        File jsonFile = new File(parseContext.jarPojo.getName()+".json");
        try {
            FileUtils.writeStringToFile(jsonFile, jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  synchronized  void writeToDB(ParseContext parseContext) {
        //db.createCollection("callgraph");
        //MongoCollection<Document> dbCollection = db.getCollection("callgraph");
        String jsonStr = JSON.toJSONString(parseContext.jarPojo);
        Document doc = Document.parse(jsonStr);
        collection.insertOne(doc);
    }
}
