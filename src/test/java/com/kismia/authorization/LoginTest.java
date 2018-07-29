package com.kismia.authorization;

import com.kismia.common.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.kismia.steps.LanguageStep;
import com.kismia.steps.LoginStep;

import static org.testng.Assert.*;
import static com.kismia.steps.LanguageStep.*;
import static com.kismia.steps.LoginStep.*;

@Feature("User's authorization")
@Story("Functional tests for login/logout")
public class LoginTest extends BaseTest {

    private LoginStep loginStep;
    private LanguageStep languageStep;

    @BeforeMethod(description = "Preconditions", alwaysRun = true)
    public void setUp() {
        loginStep = new LoginStep(driver);
        languageStep = new LanguageStep(driver);
        loginStep.openLandingPage();
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Change language to english, login, check russian interface, logout")
    public void checkRussianLanguageAfterLoginWithEnglish() {
        languageStep.selectInterfaceLanguage(ENGLISH_LANG);
        loginStep.login(USERNAME, PASSWORD);
        assertEquals(languageStep.getInterfaceLanguage(), RUSSIAN_LANG);

        loginStep.logOut();
        assertTrue(loginStep.isUserLogout());
    }
}
