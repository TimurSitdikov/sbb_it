package sbb.pages.converter_page.elements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

@FindBy(xpath = "//*[@class='rates-aside-filter rates-container']")
public class RatesContainer extends HtmlElement {

    @FindBy(xpath = ".//*[@name='converterFrom']/following-sibling::*[contains(@class,'select')]")
    private CustomSelect fromCurrencySelect;

    @FindBy(xpath = ".//*[@name='converterTo']/following-sibling::*[contains(@class,'select')]")
    private CustomSelect toCurrencySelect;

    @FindBy(xpath = ".//*[@class='button']")
    private Button calculateButton;

    @FindBy(xpath = ".//input")
    private TextInput amountInput;

    public void setAmount(String amount){
        amountInput.clear();
        amountInput.sendKeys(amount);
    }

    public String getTextFromInput() {
        return amountInput.getText();
    }

    public void clickCalculate(){
        calculateButton.click();
    }

    public void selectCurrencyFrom(String currencyFrom){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fromCurrencySelect.click();
        fromCurrencySelect.selectByText(currencyFrom.toUpperCase());
    }

    public void selectCurrencyTo(String currencyTo){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        toCurrencySelect.click();
        toCurrencySelect.selectByText(currencyTo.toUpperCase());
    }
}
