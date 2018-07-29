package com.kismia.steps;

import com.kismia.components.LoginComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static com.kismia.settings.TestConfig.getProperty;

public class LoginStep {

    private LoginComponent component;
    public static final String USERNAME = getProperty("login");
    public static final String PASSWORD = getProperty("pass");

    public LoginStep(WebDriver driver) {
        this.component = new LoginComponent(driver);
    }

    @Step("Open landing page")
    public void openLandingPage() {
        component.open(getProperty("url"));
    }

    @Step("Login with user credential")
    public void login(String email, String pass) {
        component.getLoginInput().sendKeys(email);
        component.getPasswordInput().sendKeys(pass);
        component.getSubmitButton().click();
    }

    @Step("Log out")
    public void logOut() {
        component.getHeaderMenu().click();
        component.assertThat(elementToBeClickable(component.getLogoutButton()));
        component.getLogoutButton().click();
    }

    @Step("Check that user is not authorized")
    public boolean isUserLogout() {
        return component.getLoginInput().isDisplayed();
    }
}
