package suites;

import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sbb.driver.DriverManager;
import sbb.pages.AbstractPage;
import sbb.pages.converter_page.SberbankConverterMainPage;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    protected static Collection<Object[]> readParametersFromXml(String fileName) {
        File inputFile = new File("src/main/resources/" + fileName);
        List<Object[]> parameters = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList parameterList = doc.getElementsByTagName("parameter");
            for(int i = 0; i < parameterList.getLength(); i++){
                parameters.add(parameterList.item(i).getTextContent().split(","));
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return parameters;
    }
}
