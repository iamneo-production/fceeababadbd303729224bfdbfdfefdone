package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import uistore.Locators;
import utils.Screenshot;

public class Homepage {

    Screenshot screenshot = new Screenshot();

    public void hoverToProducts(WebDriver driver, ExtentTest test) {
        try {
            WebElement productsMenu = driver.findElement(By.xpath(Locators.productsMenu));
            Actions actions = new Actions(driver);
            actions.moveToElement(productsMenu).perform();
            test.log(Status.PASS, "Hovered Products");
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, "Unable to hover products",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshot.captureScreenshot(driver, "hover-products"))
                            .build());
        }
    }

    public void clickBrands(WebDriver driver, ExtentTest test) {
        try {
            WebElement optionElement = driver.findElement(By.xpath(Locators.optionElement));
            optionElement.click();
            test.log(Status.PASS, "Clicked MACH3");
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, "Unable to click MACH3",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshot.captureScreenshot(driver, "click-brands"))
                            .build());
        }
    }

    public void findRazorTurbo(WebDriver driver, ExtentTest test) {
        try {
        WebElement productList = driver.findElement(By.xpath(Locators.productList));
        String productListText = productList.getText();
        test.log(Status.PASS, "Found Razor Turbo");
        } catch (Exception e) {
            e.printStackTrace();
            test.log(Status.FAIL, "Unable to find Razor Turbo",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshot.captureScreenshot(driver, "click-explore"))
                            .build());
        }
    }
}