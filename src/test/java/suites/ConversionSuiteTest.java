package suites;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import sbb.pages.converter_page.SberbankConverterMainPage;

import java.util.Collection;

@RunWith(Parameterized.class)
public class ConversionSuiteTest extends AbstractSuite {

    private String currencyFrom;
    private String currencyTo;

    public ConversionSuiteTest(String currencyFrom, String currencyTo) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
    }

    @Test
    public void conversionCheck() {
        SberbankConverterMainPage mainPage = openMainPage();
        mainPage.selectCurrencyFrom(currencyFrom);
        mainPage.selectCurrencyTo(currencyTo);
        double rateToBuy = mainPage.getRateToBuy();
        mainPage.setConversionAmount(String.valueOf(rateToBuy));
        mainPage.clickCalculate();
        Assert.assertTrue("Conversion error: calculation result is: " + mainPage.getCalculationResult(),
                mainPage.getCalculationResult() > 0.97d && mainPage.getCalculationResult() <= 1d);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> provideParameters() {
        return readParametersFromXml("conversionTestsData.xml");
    }

}
