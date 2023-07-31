package runner;
import java.io.IOException;

import java.time.Duration;

import org.apache.log4j.Logger;

import org.openqa.selenium.By;

import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.edge.EdgeOptions;

import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import com.runner.PageObjects.LoginPage;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.aventstack.extentreports.reporter.configuration.Theme;

import com.test.ParentDriver.MainDriver;

import com.test.utitlty.ExcelUtility;

import com.test.utitlty.ScreenSHotUtility;
public class LoginTest {
    WebDriverWait wait;

ExcelUtility exl=new ExcelUtility();

Logger log;

public ExtentSparkReporter spark;

public ExtentReports extent;

public ExtentTest logger;

@Test

public void TC_Login_001() throws IOException {

log=Logger.getLogger("rootLogger");

log.info("Test Started");

LoginPage lp = new LoginPage(driver);

exl.setExcelSheet(MainDriver.filepath, "Login");

logger = extent.createTest("Verify Login");

logger.createNode("Login Test");

for(int row=1;row<exl.rowCount();row++)

{

String uname=exl.readData(row, 0);

String pass=exl.readData(row, 1);

driver.get("https://demowebshop.tricentis.com/login/");

lp.enterUname(uname);

lp.enterPassword(pass);

lp.clickLogin();

String actualResult = driver.findElement(By.partialLinkText("Log")).getText();

/*Assert.assertEquals(actualResult, "Log in");

ScreenSHotUtility.getScreen("LoginSucess");*/

logger.addScreenCaptureFromPath(ScreenSHotUtility.getScreen("LoginTest"));

if(!(actualResult.contains("Log in")))

{

ScreenSHotUtility.getScreen("LoginError");

logger.addScreenCaptureFromPath(ScreenSHotUtility.getScreen("LoginError"));

}

log.debug(actualResult);

Assert.assertEquals(actualResult, "Log out");

}

}

@Test

public void TC_Register_002() throws IOException

{

log=Logger.getLogger("rootLogger");

log.info("Test Started");

logger = extent.createTest("To verify user able to Register");

driver.get("https://demowebshop.tricentis.com/login/");

driver.findElement(By.linkText("Register")).click();

driver.findElement(By.id("gender-male")).click();

driver.findElement(By.id("FirstName")).sendKeys("ABC");

driver.findElement(By.id("LastName")).sendKeys("WER");

driver.findElement(By.id("Email")).sendKeys("ABC@gmail.com");

driver.findElement(By.id("Password")).sendKeys("123456");

driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");

driver.findElement(By.id("register-button")).click();

log.info("Test Case done");

String actualResult = driver.findElement(By.partialLinkText("Log")).getText();

Assert.assertEquals(actualResult, "Log out");

logger.addScreenCaptureFromPath(ScreenSHotUtility.getScreen("RegisterError"));

}

@Test

public void TC_Vote_003() throws InterruptedException, IOException

{

log=Logger.getLogger("rootLogger");

logger = extent.createTest("To verify user able to vote");

log.info("Test Started");

driver.get("https://demowebshop.tricentis.com/");

driver.findElement(By.id("pollanswers-2")).click();

driver.findElement(By.id("vote-poll-1")).click();

Thread.sleep(500);

String actualResult =driver.findElement(By.id("block-poll-vote-error-1")).getText();

Assert.assertEquals(actualResult, "Only registered users can vote.");

logger.addScreenCaptureFromPath(ScreenSHotUtility.getScreen("VoteTest"));

}

@BeforeMethod

public void beforeMethod() {

System.setProperty("webdriver.edge.driver", "D:\\Selenium Jars Feb27 2023\\edge\\msedgedriver.exe");

EdgeOptions options = new EdgeOptions();

options.addArguments("--remote-allow-origins=*");

driver = new EdgeDriver(options);

driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

wait = new WebDriverWait(driver, Duration.ofSeconds(60));

}

@BeforeClass

public void beforeClass()

{

System.out.println("BeforeClass");

extent = new ExtentReports();

spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/STMExtentReport.html");

extent.attachReporter(spark);

extent.setSystemInfo("Host Name", "LIT Project");

extent.setSystemInfo("Environment", "Production");

extent.setSystemInfo("User Name", "Suvitha Murali");

spark.config().setDocumentTitle("Milestone Project ");

// Name of the report

spark.config().setReportName("Milestone Project ");

// Dark Theme

spark.config().setTheme(Theme.STANDARD);

}

@AfterTest

public void endReport() {

extent.flush();

}
}
