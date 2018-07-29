package steps;

import components.LanguageComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;
import static settings.SeleniumListener.LOG;

public class LanguageStep {

    private LanguageComponent component;
    public static final String ENGLISH_LANG = "en";
    public static final String RUSSIAN_LANG = "ru";

    public LanguageStep(WebDriver driver) {
        this.component = new LanguageComponent(driver);
    }

    @Step("Select interface language")
    public void selectInterfaceLanguage(String language) {
        language = language.toLowerCase().trim();
        WebElement container = component.getLanguageContainer();
        if (!container.getText().equals(language)) {
            container.click();
            List<WebElement> list = component.getLanguagesList();
            for (WebElement element : list) {
                if (element.getText().equals(language)) {
                    element.click();
                    component.assertThat(urlContains(language));
                    break;
                }
            }
            if (!getInterfaceLanguage().equals(language)){
                throw new InvalidArgumentException("Language '" + language + "' not found in list");
            }
        } else {
            LOG.info("Language '" + language + "' is already applied");
        }
    }

    @Step("Get interface language")
    public String getInterfaceLanguage() {
        return component.getHtmlLanguageValue();
    }
}
