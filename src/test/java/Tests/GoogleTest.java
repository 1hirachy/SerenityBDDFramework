package Tests;

import Steps.GoogleSteps;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@RunWith(SerenityRunner.class)
public class GoogleTest {

    @Steps
    GoogleSteps googleSteps;

    @Managed(driver="chrome")
    WebDriver driver;

    Properties prop = new Properties();

    @Test
    public void TestGoogle() throws IOException {

        prop.load(new FileInputStream("src/main/resources/testdata.properties"));

        googleSteps.launchGoogleApp();
        googleSteps.searchItem(prop.getProperty("searchItem"));
        googleSteps.collectItem();
        googleSteps.selectItem(prop.getProperty("selectItem"));
    }
}
