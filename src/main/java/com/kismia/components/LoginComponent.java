package com.kismia.components;

import com.kismia.common.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.*;

public class LoginComponent extends BaseComponent {

    public LoginComponent(WebDriver driver) {
        super(driver);
    }

    public WebElement getLoginInput() {
        return $(name("email"));
    }

    public WebElement getPasswordInput() {
        return $(name("password"));
    }

    public WebElement getSubmitButton() {
        return $(cssSelector(".home-page-form.js_signInForm > a"));
    }

    public WebElement getHeaderMenu() {
        return $(className("new-header__photo"));
    }

    public WebElement getLogoutButton() {
        return $(cssSelector("a[href=\"/sign/out\"]"));
    }
}
