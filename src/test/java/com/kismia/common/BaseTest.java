package com.kismia.common;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.ScreenshotException;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.kismia.settings.Browser;
import com.kismia.settings.SeleniumListener;
import com.kismia.settings.TestConfig;

import java.util.concurrent.TimeUnit;

import static java.lang.System.getProperty;
import static java.lang.System.setProperty;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(description = "Open browser", alwaysRun = true)
    public void openBrowser() {
        if (driver == null) {
            Browser browser = TestConfig.getBrowser();
            switch (browser) {
                case CHROME:
                    setProperty("webdriver.chrome.driver", getProperty("user.dir") + "/drivers/chromedriver");
                    registerListener(new ChromeDriver());
                    break;
                case FIREFOX:
                    setProperty("webdriver.gecko.driver", getProperty("user.dir") + "/drivers/geckodriver");
                    registerListener(new FirefoxDriver());
                    break;
                case CHROME_MAC:
                    setProperty("webdriver.chrome.driver", getProperty("user.dir") + "/drivers/chromedriver_mac");
                    registerListener(new ChromeDriver());
                    break;
                default:
                    throw new IllegalStateException("Unsupported browser type");
            }
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod(description = "Close browser", alwaysRun = true)
    public void closeBrowser(ITestResult result) {
        if (!result.isSuccess()) {
            try {
                captureScreenshot();
            } catch (ScreenshotException e) {
                e.printStackTrace();
            }
        }
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] captureScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    private void registerListener(WebDriver driver) {
        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
        eventDriver.register(new SeleniumListener());
        this.driver = eventDriver;
    }
}
