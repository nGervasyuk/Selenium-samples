package com.kismia.common;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class ConciseApi {

    public abstract WebDriver getDriver();

    protected WebElement $(By locator) {
        return getDriver().findElement(locator);
    }

    protected List<WebElement> $$(By locator) {
        return getDriver().findElements(locator);
    }

    public <V> void assertThat(Function<? super WebDriver, V> condition) {
        (new WebDriverWait(getDriver(), 15)).until(condition);
    }

    public void open(String url) {
        getDriver().get(url);
    }
}
