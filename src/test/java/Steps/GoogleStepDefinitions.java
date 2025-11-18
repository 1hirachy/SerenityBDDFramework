package Steps;

import com.mycompany.Utils.TestData;
import groovy.util.logging.Slf4j;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

@Slf4j
public class GoogleStepDefinitions {

    @Steps
    GoogleSteps googleSteps;

    @Given("the user is on the google page")
    public void the_user_is_on_the_google_page() {
        googleSteps.launchGoogleApp();
    }

    @When("the user enters search item")
    public void the_user_enters_search_item() {

        String searchItem = TestData.get("searchItem");
        googleSteps.searchItem(searchItem);
    }

    @When("the user collect the search results")
    public void the_user_collect_the_search_results() {
        googleSteps.collectItem();
    }

    @Then("the user should click the suggestion")
    public void the_user_should_click_the_suggestion() {

        String selectItem = TestData.get("selectItem");
        googleSteps.selectItem(selectItem);
    }


}
