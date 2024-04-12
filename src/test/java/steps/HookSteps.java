package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.FileUtilities;
import utils.WebDriverHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HookSteps {

    public static final String REPORT_DIR = "src" + File.separator + "test" + File.separator + "java" + File.separator + "resources" + File.separator + "reports" + File.separator;

    @BeforeAll
    public static void setup() {
        try {
            FileUtilities.cleanDir(REPORT_DIR);
        } catch (IOException e) {
            System.out.println("Error cleaning directory: " + e.getMessage());
        }
    }

    @Before
    public static void setupDriver() {
        WebDriverHandler.setupDriver();
    }

    @After
    public static void cleanup(Scenario scenario) {
        takeScreeshot(scenario);
        WebDriverHandler.closeDriver();
    }

    public static void takeScreeshot(Scenario scenario) {
        if (scenario.isFailed()) {
            // Capture screenshot
            byte[] screenshot = ((TakesScreenshot) WebDriverHandler.setupDriver()).getScreenshotAs(OutputType.BYTES);
            // Attach screenshot to report
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }
}
