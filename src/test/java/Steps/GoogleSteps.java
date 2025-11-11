package Steps;

import Pages.GooglePage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GoogleSteps {

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
