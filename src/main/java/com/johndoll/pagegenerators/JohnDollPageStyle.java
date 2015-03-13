package com.johndoll.pagegenerators;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.johndoll.utility.ExcelReader;

/**
 * @author Jonathan Doll
 */
public class JohnDollPageStyle {

    public JohnDollPageStyle(String file, String destination) throws FileNotFoundException, IOException {
        ExcelReader excel = new ExcelReader(file);
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
            fout.write(("import org.openqa.selenium.WebElement;\n").getBytes());
            fout.write(("import org.openqa.selenium.By;\n\n").getBytes());
            fout.write(("public class " + fileName + "{\n\n").getBytes());
            fout.write(("    private WebDriver driver;\n\n").getBytes());
            fout.write(("    public " + fileName + "(WebDriver driver){\n").getBytes());
            fout.write(("        this.driver = driver;\n    }\n\n").getBytes());
            
            int row = 2;
            while(excel.getColumnCount(row) >= 3){
                fout.write(("    private WebElement " + excel.getCellData(row, 0)).getBytes());
                if(excel.getCellData(row, 3).equals("")){
                    fout.write(("(){\n").getBytes());
                }else{
                    fout.write(("("+excel.getCellData(row, 3)+"){\n").getBytes());
                }
                fout.write(("        return driver.findElement(By." + excel.getCellData(row, 1) + "(\"" + excel.getCellData(row, 2) + "\"));\n    }\n\n").getBytes());
                row++;
            }
            
            while(excel.getColumnCount(row) == 2){
                fout.write(("    public void " + excel.getCellData(row, 0)).getBytes());
                if(excel.getCellData(row, 1).equalsIgnoreCase("none")){
                    fout.write(("(){\n").getBytes());
                }else{
                    fout.write(("(" + excel.getCellData(row, 1) + "){\n").getBytes());
                }
                row++;
                while(excel.getColumnCount(row) == 3){
                    fout.write(("        " + excel.getCellData(row, 0) + "()." + excel.getCellData(row, 1)).getBytes());
                    if(excel.getCellData(row, 2).equalsIgnoreCase("none")){
                        fout.write(("();\n").getBytes());
                    }else{
                        fout.write(("(" + excel.getCellData(row, 2) + ");\n").getBytes());
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
