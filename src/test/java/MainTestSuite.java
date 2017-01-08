import categories.SmokeTestsCategory;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import suites.ConversionSuite;
import suites.ValidationSuite;


@Suite.SuiteClasses({ValidationSuite.class, ConversionSuite.class})
@Categories.IncludeCategory(SmokeTestsCategory.class)
@RunWith(Suite.class)
public class MainTestSuite {
}
