package suites;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Suite;
import sbb.pages.SberbankConverterMainPage;

@RunWith(Parameterized.class)
@Suite.SuiteClasses(ValidationSuite.class)
public class ValidationSuite extends AbstractSuite {

    @Test
    public void conversionValidationTest(){
        SberbankConverterMainPage mainPage = openMainPage();

    }
}
