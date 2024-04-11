import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.WebDriverHandler;

import java.io.File;
import java.io.IOException;

@CucumberOptions(
        features = "src/test/java/features/test.feature",
        glue = "steps",
        plugin = { "pretty", "html:src/test/java/resources/reports/cucumber.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
