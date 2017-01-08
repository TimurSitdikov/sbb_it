package suites;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import sbb.pages.converter_page.SberbankConverterMainPage;

import java.util.Collection;

@RunWith(Parameterized.class)
public class ValidationSuiteTest extends AbstractSuite {

    private String amountToCalculate;
    private String expectedAmount;

    public ValidationSuiteTest(String amountToCalculate, String expectedAmount) {
        this.amountToCalculate = amountToCalculate;
        this.expectedAmount = expectedAmount;
    }

    @Test
    public void conversionValidationTest() {
        SberbankConverterMainPage mainPage = openMainPage();
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
