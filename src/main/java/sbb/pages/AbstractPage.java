package sbb.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.lang.reflect.Constructor;

public abstract class AbstractPage {
    private final WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)),this);
    }

    public abstract void waitUntilPageCompletelyLoaded();

    public abstract String getUrl();

    public WebDriver getDriver() {
        return driver;
    }

    protected void waitForElementVisible(WebElement webElement, int timeout){
        new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitForElementClickable(int timeout, WebElement... webElements){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        for (WebElement webElement: webElements){
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
        }
    }

    protected  <T extends AbstractPage> T initPage(Class<T> clazz){
        try {
            try {
                Constructor<T> constructor = clazz.getConstructor(WebDriver.class);
                return constructor.newInstance(driver);
            } catch (NoSuchMethodException e) {
                return clazz.newInstance();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected <T extends AbstractPage> T openPage(Class<T> clazz){
        T page = initPage(clazz);
        driver.get(page.getUrl());
        page.waitUntilPageCompletelyLoaded();
        return page;
    }

    public void goBack(){
        getDriver().navigate().back();
    }
}
