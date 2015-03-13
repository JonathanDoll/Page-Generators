package com.johndoll.pagegenerators;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import com.johndoll.utility.ExcelReader;

/**
 * @author Jonathan Doll
 */
public class OrasiFrameworkPageStyle {

    public OrasiFrameworkPageStyle(String file, String destination) throws FileNotFoundException, IOException {
        ExcelReader excel = new ExcelReader(file);
        ArrayList decVars = new ArrayList();
        for (int j = 0; j < excel.getSheetCount(); j++) {
            excel.setSheet(j);
            String fileName = excel.getCellData(0, 0);
            String fold = destination + "\\";
            fold += excel.getCellData(1, 0).replace(".", "\\");
            File folder = new File(fold);
            folder.mkdir();
            FileOutputStream fout = new FileOutputStream(folder + "\\" + fileName + ".java");
            fout.write(("package " + excel.getCellData(1, 0) + ";\n\n").getBytes());
            fout.write(("import org.openqa.selenium.WebDriver;\n").getBytes());
            fout.write(("import org.openqa.selenium.support.FindBy;\n").getBytes());
            fout.write(("import org.testng.Assert;\n\n").getBytes());
            fout.write(("import com.orasi.core.interfaces.impl.internal.ElementFactory;\n").getBytes());
            fout.write(("import com.orasi.utils.PageLoaded;\n").getBytes());
            fout.write(("import com.orasi.core.interfaces.*;\n\n").getBytes());
            fout.write(("public class " + fileName + "{\n\n").getBytes());
            
            
            int row = 2;
            while(excel.getColumnCount(row) == 4){
                decVars.add(excel.getCellData(row, 0));
                fout.write(("    @FindBy(" + excel.getCellData(row, 1) + " = \"" + excel.getCellData(row, 2) + "\")\n").getBytes());
                fout.write(("    private " + excel.getCellData(row, 3) + " " + excel.getCellData(row, 0) + ";\n\n").getBytes());
                row++;
            }
            
            fout.write(("    private WebDriver driver;\n\n").getBytes());
            
            fout.write(("    public " + fileName + "(WebDriver driver){\n").getBytes());
            fout.write(("        this.driver = driver;\n").getBytes());
            fout.write(("        ElementFactory.initElements(driver, this);\n    }\n\n").getBytes());
            
            fout.write(("    public boolean pageLoaded(){\n").getBytes());
            fout.write(("        return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, " + excel.getCellData(row, 0) + ");\n    }\n\n").getBytes());
            fout.write(("").getBytes());
            
            fout.write(("    public boolean pageLoaded(Element element){\n").getBytes());
            fout.write(("        return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);\n    }\n\n").getBytes());
            fout.write(("").getBytes());
            
            fout.write(("    public " + fileName + " initialize(){\n").getBytes());
            fout.write(("        return ElementFactory.initElements(driver, this.getClass());\n    }\n\n").getBytes());
            
            row++;
            
            while(excel.getColumnCount(row) == 2){
                fout.write(("    public void " + excel.getCellData(row, 0)).getBytes());
                if(excel.getCellData(row, 1).equalsIgnoreCase("none")){
                    fout.write(("(){\n").getBytes());
                }else{
                    fout.write(("(" + excel.getCellData(row, 1) + "){\n").getBytes());
                }
                row++;
                
                
                while(excel.getColumnCount(row) == 3){
                    fout.write(("        " + excel.getCellData(row, 0)).getBytes());
                    if(decVars.indexOf(excel.getCellData(row, 1)) >= 0){
                        fout.write(("(" + excel.getCellData(row, 1) + ");\n").getBytes());
                    }else if(excel.getCellData(row, 1).equalsIgnoreCase("none")){
                        fout.write(("();\n").getBytes());
                    }else{
                        fout.write(("." + excel.getCellData(row, 1)).getBytes());
                        if(excel.getCellData(row, 2).equalsIgnoreCase("none")){
                            fout.write(("();\n").getBytes());
                        }else{
                            fout.write(("(" + excel.getCellData(row, 2) + ");\n").getBytes());
                        }
                    }
                    row++;
                }
                fout.write(("    }\n\n").getBytes());
            }

            fout.write("}".getBytes());
            fout.close();
        }

    }

}
