package common;

import org.openqa.selenium.WebDriver;

public class BaseComponent extends ConciseApi {

    private WebDriver driver;

    protected BaseComponent(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
