package suites;

import org.openqa.selenium.WebDriver;
import sbb.driver.DriverManager;
import sbb.pages.AbstractPage;
import sbb.pages.SberbankConverterMainPage;

import java.lang.reflect.Constructor;

public abstract class AbstractSuite {

    private WebDriver driver;
    private final String SITE_URL = "http://www.sberbank.ru/ru/quotes/converter";

    protected WebDriver getDriver() {
        if (driver == null) {
            driver = DriverManager.getDriver();
        }
        return driver;
    }

    protected void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    protected <T extends AbstractPage> T initPage(Class<T> clazz) {
        try {
            try {
                Constructor<T> constructor = clazz.getConstructor(WebDriver.class);
                return constructor.newInstance(getDriver());
            } catch (NoSuchMethodException e) {
                return clazz.newInstance();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected <T extends AbstractPage> T openPage(Class<T> clazz) {
        T page = initPage(clazz);
        getDriver().get(page.getUrl());
        page.waitUntilPageCompletelyLoaded();
        return page;
    }

    protected SberbankConverterMainPage openMainPage() {
        if (!SITE_URL.equals(getDriver().getCurrentUrl())) {
            return openPage(SberbankConverterMainPage.class);
        } else {
            return initPage(SberbankConverterMainPage.class);
        }
    }

}
