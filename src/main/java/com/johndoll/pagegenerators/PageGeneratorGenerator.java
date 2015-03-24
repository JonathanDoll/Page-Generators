package com.johndoll.pagegenerators;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Jonathan Doll
 */

//So you can generate pages while you generate pages.
public class PageGeneratorGenerator {
    
    public PageGeneratorGenerator(String file, String newFileName){
        
        try {
            BufferedReader read = new BufferedReader(new FileReader(file));
            FileWriter write = new FileWriter("C:\\Users\\Jonathan.Doll\\Documents\\NetBeansProjects\\PageGenerators\\src\\main\\java\\com\\johndoll\\pagegenerators\\" + newFileName + ".java");
            
            write.write("package com.johndoll.pagegenerators;\n\n");
            write.write("import java.io.FileWriter;\n");
            write.write("import java.io.IOException;\n\n");
            write.write("public class " + newFileName + "{\n\n");
            write.write("    public " + newFileName + "(String fileLoc, String fileName){\n\n");
            write.write("        try {\n\n");
            write.write("            FileWriter write = new FileWriter(fileLoc + \"\\\\\" + fileName + \".java\");\n\n");
            
            String r;
            while((r = read.readLine()) != null){
                r = r.replaceAll("\"", "\\\\\"");
                write.write("            write.write(\"" + r + "\\n\");\n");
            }
            
            write.write("            write.close();\n");
            write.write("        }catch (IOException e){\n");
            write.write("            System.err.println(e);\n");
            write.write("        }\n    }\n}");
            
            read.close();
            write.close();
            
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        
    }
    
}
