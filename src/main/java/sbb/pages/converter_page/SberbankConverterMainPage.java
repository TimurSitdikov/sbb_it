package sbb.pages.converter_page;

import org.openqa.selenium.WebDriver;
import sbb.pages.AbstractPage;
import sbb.pages.converter_page.elements.RatesContainer;

public class SberbankConverterMainPage extends AbstractPage {

    RatesContainer ratesContainer;

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

    public void setConversionAmount(String amount){
        ratesContainer.getConverterBlock().setAmount(amount);
    }

    public void clickCalculate(){
        ratesContainer.clickCalculate();
    }


    public String getTextFromInput() {
        return ratesContainer.getConverterBlock().getTextFromInput();
    }
}
