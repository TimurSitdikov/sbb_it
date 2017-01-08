package sbb.pages.converter_page.elements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@FindBy(xpath = "//*[@class='rates-aside-filter rates-container']")
public class RatesContainer extends HtmlElement {

    private ConverterBlock converterBlock;

    @FindBy(xpath = ".//*[@class='button']")
    private Button calculateButton;

    public ConverterBlock getConverterBlock() {
        return converterBlock;
    }

    public void clickCalculate(){
        calculateButton.click();
    }

}
