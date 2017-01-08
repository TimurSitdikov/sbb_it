package sbb.pages.converter_page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import sbb.pages.AbstractPage;
import sbb.pages.converter_page.elements.RatesContainer;

public class SberbankConverterMainPage extends AbstractPage {

    private RatesContainer ratesContainer;

    @FindBy(xpath = "(//span[contains(@class,'rates-')])[2]")
    private HtmlElement rateToBuy;

    @FindBy(xpath = "(//span[contains(@class,'rates-')])[1]")
    private HtmlElement rateToSell;

    @FindBy(xpath = "//*[@class='converter-result']/h4/span[1]")
    private HtmlElement conversionResult;

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

    public void setConversionAmount(String amount) {
        ratesContainer.setAmount(amount);
    }

    public void clickCalculate() {
        waitForElementClickable(ratesContainer);
        ratesContainer.clickCalculate();
    }


    public String getTextFromInput() {
        return ratesContainer.getTextFromInput();
    }

    public void selectCurrencyFrom(String currencyFrom) {
        waitForElementClickable(ratesContainer);
        ratesContainer.selectCurrencyFrom(currencyFrom);
    }

    public void selectCurrencyTo(String currencyTo) {
        waitForElementClickable(ratesContainer);
        ratesContainer.selectCurrencyTo(currencyTo);
    }


    public double getRateToBuy() {
        waitForElementVisible(rateToBuy);
        return Double.parseDouble(rateToBuy.getText().trim().replaceAll(",", "."));
    }

    public double getRateToSell() {
        return Double.parseDouble(rateToSell.getText().trim().replaceAll(",", "."));
    }

    public double getCalculationResult() {
        waitForElementVisible(conversionResult);
        return Double.parseDouble(conversionResult.getText().trim().replaceAll(",", "."));
    }
}
