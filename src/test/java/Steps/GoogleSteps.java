package Steps;

import Pages.GooglePage;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Step;
import org.openqa.selenium.WebDriver;

public class GoogleSteps {

    @Managed(driver = "chrome")
    WebDriver driver;
    GooglePage googlePage;

    @Step
    public void launchGoogleApp() {

        googlePage.maximize();
        googlePage.open();
    }

    @Step
    public void searchItem(String input) {
        googlePage.searchItem(input);
    }

    @Step
    public void collectItem() {
        googlePage.collectItems();
    }

    @Step
    public void selectItem(String input) {
        googlePage.selectItem(input);
    }


}
