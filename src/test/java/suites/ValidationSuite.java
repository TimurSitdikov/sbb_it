package suites;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Suite;
import sbb.driver.DriverManager;
import sbb.pages.converter_page.SberbankConverterMainPage;

import java.util.Collection;

@RunWith(Parameterized.class)
@Suite.SuiteClasses(ValidationSuite.class)
public class ValidationSuite extends AbstractSuite {

    private String amountToCalculate;
    private String expectedAmount;
    private SberbankConverterMainPage mainPage;

    public ValidationSuite(String amountToCalculate, String expectedAmount) {
        this.amountToCalculate = amountToCalculate;
        this.expectedAmount = expectedAmount;
    }

    @BeforeClass
    public static void startBrowser() {
        DriverManager.getDriver();
    }

    @AfterClass
    public static void closeBrowser() {
        DriverManager.quitDriver();
    }

    @Test
    public void conversionValidationTest() {
        mainPage = openMainPage();
        mainPage.setConversionAmount(amountToCalculate);
        mainPage.clickCalculate();
        String textFromInput = mainPage.getTextFromInput();
        double expectedDAmount = Double.parseDouble(expectedAmount);
        double realDAmount = Double.parseDouble(textFromInput.replaceAll(" ",""));
        Assert.assertTrue("Numbers mismatch. Expected: " + expectedDAmount + ", but actual is: " + realDAmount,
                expectedDAmount == realDAmount);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> provideParameters() {
        return readParametersFromXml("validationTestsData.xml");
    }


}
