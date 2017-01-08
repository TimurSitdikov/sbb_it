package sbb.pages;

import org.openqa.selenium.WebDriver;

public class SberbankConverterMainPage extends AbstractPage {

    public SberbankConverterMainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageCompletelyLoaded() {

    }

    @Override
    public String getUrl() {
        return "http://www.sberbank.ru/ru/quotes/converter";
    }
}
