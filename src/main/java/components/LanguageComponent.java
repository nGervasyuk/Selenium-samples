package components;

import common.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.By.*;

public class LanguageComponent extends BaseComponent {

    public LanguageComponent(WebDriver driver) {
        super(driver);
    }

    public WebElement getLanguageContainer() {
        return $(cssSelector(".js_languagesContainer > a"));
    }

    public List<WebElement> getLanguagesList() {
        return $$(className("home-page-languages__list__link"));
    }

    public String getHtmlLanguageValue() {
        return $(tagName("html")).getAttribute("lang");
    }
}
