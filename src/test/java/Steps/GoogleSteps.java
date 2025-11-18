package Steps;

import Pages.GooglePage;
import groovy.util.logging.Slf4j;
import net.serenitybdd.annotations.Step;

@Slf4j
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
