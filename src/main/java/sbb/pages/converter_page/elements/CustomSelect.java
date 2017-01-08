package sbb.pages.converter_page.elements;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;

public class CustomSelect extends HtmlElement{

    @FindBy(xpath = ".//span")
    private List<HtmlElement> variants;

    public void selectByText(String text){
        boolean found = false;
        for(HtmlElement variant: variants){
            if(variant.getText().equalsIgnoreCase(text)){
                variant.click();
                found = true;
                break;
            }
        }
        if(!found) throw new RuntimeException("Variant with text: " + text + " not found.");
    }
}
