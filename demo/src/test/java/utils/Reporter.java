package utils;


import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporter {
	
	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentReport = new ExtentReports();
		PropsLoader propsLoader = PropsLoader.getInstance("/src/main/java/config.properties");
		File extentReportFile = new File(System.getProperty("user.dir")+"/src/main/reports/extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Gillette Test Report");
		sparkReporter.config().setDocumentTitle("Gillette Automation Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
		extentReport.setSystemInfo("Application URL",propsLoader.getProperty("url"));
		extentReport.setSystemInfo("Browser Name",propsLoader.getProperty("browserName"));
		extentReport.setSystemInfo("Email",propsLoader.getProperty("validEmail"));
		extentReport.setSystemInfo("Password",propsLoader.getProperty("validPassword"));
		extentReport.setSystemInfo("Operating System",System.getProperty("os.name"));
		extentReport.setSystemInfo("Username",System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version",System.getProperty("java.version"));
        
		return extentReport;
	}

}
