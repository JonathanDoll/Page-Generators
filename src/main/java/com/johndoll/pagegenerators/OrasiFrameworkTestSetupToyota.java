package com.johndoll.pagegenerators;

import java.io.FileWriter;
import java.io.IOException;

public class OrasiFrameworkTestSetupToyota{

    public OrasiFrameworkTestSetupToyota(String fileLoc, String fileName, String packageName, String workbookName, String worksheetName, String appName){

        try {

            FileWriter write = new FileWriter(fileLoc + "\\" + fileName + ".java");

            write.write("package " + packageName + ";\n");
            write.write("\n");
            write.write("import java.io.IOException;\n");
            write.write("import java.util.HashMap;\n");
            write.write("import java.util.Map;\n");
            write.write("\n");
            write.write("import org.openqa.selenium.WebDriver;\n");
            write.write("import org.testng.ITestResult;\n");
            write.write("import org.testng.annotations.AfterMethod;\n");
            write.write("import org.testng.annotations.BeforeTest;\n");
            write.write("import org.testng.annotations.DataProvider;\n");
            write.write("import org.testng.annotations.Optional;\n");
            write.write("import org.testng.annotations.Parameters;\n");
            write.write("import org.testng.annotations.Test;\n");
            write.write("\n");
            write.write("import com.orasi.utils.Constants;\n");
            write.write("import com.orasi.utils.Screenshot;\n");
            write.write("import com.orasi.utils.WebDriverSetup;\n");
            write.write("import com.orasi.utils.dataProviders.ExcelDataProvider;\n");
            write.write("\n");
            write.write("public class " + fileName + " {\n");
            write.write("\n");
            write.write("	private String application = \"\";\n");
            write.write("	private String browserUnderTest = \"\";\n");
            write.write("	private String browserVersion = \"\";\n");
            write.write("	private String operatingSystem = \"\";\n");
            write.write("	private String runLocation = \"\";\n");
            write.write("	private String environment = \"\";\n");
            write.write("	private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();\n");
            write.write("\n");
            write.write("	@DataProvider(name = \"dataScenario\")\n");
            write.write("	public Object[][] scenarios() {\n");
            write.write("		return new ExcelDataProvider(Constants.TOYOTA_DATAPROVIDER_PATH\n");
            write.write("				+ \""+ workbookName + "\", \"" + worksheetName + "\").getTestData();\n");
            write.write("	}\n");
            write.write("\n");
            write.write("	@BeforeTest(groups = { \"regression\" })\n");
            write.write("	@Parameters({ \"runLocation\", \"browserUnderTest\", \"browserVersion\",\n");
            write.write("			\"operatingSystem\", \"environment\" })\n");
            write.write("	public void setup(@Optional String runLocation, String browserUnderTest,\n");
            write.write("			String browserVersion, String operatingSystem, String environment) {\n");
            write.write("		this.application = \""+appName+"\";\n");
            write.write("		this.runLocation = runLocation;\n");
            write.write("		this.browserUnderTest = browserUnderTest;\n");
            write.write("		this.browserVersion = browserVersion;\n");
            write.write("		this.operatingSystem = operatingSystem;\n");
            write.write("		this.environment = environment;\n");
            write.write("	}\n");
            write.write("\n");
            write.write("	@AfterMethod(groups = { \"regression\" })\n");
            write.write("	public synchronized void closeSession(ITestResult test) {\n");
            write.write("		System.out.println(test.getMethod().getMethodName());\n");
            write.write("		WebDriver driver = drivers.get(test.getMethod().getMethodName());\n");
            write.write("\n");
            write.write("		if (test.getStatus() == ITestResult.FAILURE) {\n");
            write.write("			new Screenshot().takeScreenShot(test, driver);\n");
            write.write("		}\n");
            write.write("		\n");
            write.write("		if(driver.getWindowHandles().size() != 0){\n");
            write.write("			driver.quit();	\n");
            write.write("		}\n");
            write.write("	}\n");
            write.write("\n");
            write.write("	@Test(dataProvider = \"dataScenario\", groups = { \"regression\" })\n");
            write.write("	public void testChangeZipCode(\n");
            write.write("			String testScenario, String zipCode) throws InterruptedException, IOException {\n");
            write.write("		\n");
            write.write("		String testName = new Object() {\n");
            write.write("		}.getClass().getEnclosingMethod().getName();\n");
            write.write("		WebDriverSetup setup = new WebDriverSetup(application,\n");
            write.write("				browserUnderTest, browserVersion, operatingSystem, runLocation,\n");
            write.write("				environment);\n");
            write.write("		WebDriver driver = setup.initialize();\n");
            write.write("		\n");
            write.write("		System.out.println(testName);\n");
            write.write("		drivers.put(testName, driver);\n");
            write.write("	}\n");
            write.write("}\n");
            write.close();
        }catch (IOException e){
            System.err.println(e);
        }
    }
}