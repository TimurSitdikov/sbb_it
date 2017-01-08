package sbb.pages.converter_page.elements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Select;
import ru.yandex.qatools.htmlelements.element.TextInput;

@FindBy(xpath = "//*[@class='filter-block filter-block-converter']")
public class ConverterBlock extends HtmlElement{

    @FindBy(xpath = ".//input")
    private TextInput amountInput;

    @FindBy(xpath = ".//*[@name='converterTo']")
    private Select fromCurrencySelect;

    @FindBy(xpath = ".//*[@name='converterFrom']")
    private Select toCurrencySelect;

    public void setAmount(String amount){
        amountInput.clear();
        amountInput.sendKeys(amount);
    }

    public void selectFromCurrency(String currencyFrom){
        fromCurrencySelect.selectByValue(currencyFrom.toUpperCase());
    }

    public void selectCurrencyTo(String currencyTo){
        toCurrencySelect.selectByValue(currencyTo.toUpperCase());
    }

    public String getTextFromInput() {
        return amountInput.getText();
    }
}
