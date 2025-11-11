package Pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class GooglePage extends PageObject {

    // Using 'name="q"' as the stable locator for the search input
    @FindBy(name = "q")
    WebElementFacade searchBar;

    @FindBy(css = "ul[role='listbox']")
    WebElementFacade dropDownList;

    By dropDownElementsLocator = By.cssSelector("li.sbct div[role='option']");

    List<WebElementFacade> dropDownElementsList;

    public void maximize(){
        getDriver().manage().window().maximize();
    }

    // --- Methods ---

    /**
     * Step 1: Enters the search query into the search bar.
     */
    public void searchItem(String input) {
        // Serenity's type() method automatically handles basic visibility waits
        searchBar.type(input);
    }

    /**
     * Step 2: Waits for the dropdown to appear and collects the suggestion elements.
     */
    public void collectItems() {
        // Explicitly wait for the dropdown container to be visible
        dropDownList.waitUntilVisible();

        // Wait until there is at least one item inside the list
        waitForCondition().until(d -> dropDownList.findElements(dropDownElementsLocator).size() > 0);

        // Populate the list using Serenity's findAll(By) method
        dropDownElementsList = findAll(dropDownElementsLocator);
    }

    /**
     * Step 3: Iterates through the collected items and clicks the specific match.
     *
     * @param searchItem The exact text of the item to select
     */
    public void selectItem(String searchItem) {
        if (dropDownElementsList == null || dropDownElementsList.isEmpty()) {
            throw new RuntimeException("Dropdown list was empty during selection attempt.");
        }

        System.out.println("Attempting to select item containing: " + searchItem);

        for (WebElementFacade element : dropDownElementsList) {
            // Check for null aria-label explicitly to prevent NullPointerException
            String ariaLabel = element.getAttribute("aria-label");
            if (ariaLabel == null || ariaLabel.isEmpty()) {
                continue; // Skip this problematic 'null' element
            }

            System.out.println("Found suggestion: " + ariaLabel);

            // Use contains() ignoring case for flexible matching
            if (ariaLabel.toLowerCase().contains(searchItem.trim().toLowerCase()) || searchItem.trim().toLowerCase().contains(ariaLabel.toLowerCase())) {
                try {
                    // Use Serenity's JS click if standard click fails
                    element.click();
                    System.out.println("Clicked successfully: " + ariaLabel);
                    return; // Exit the method immediately after a successful click
                } catch (StaleElementReferenceException e) {
                    // Handle cases where the DOM updated right before we clicked
                    System.out.println("Element stale, attempting JS click backup...");
                    evaluateJavascript("arguments[0].click();", element);
                    return; // Exit after JS click
                } catch (RuntimeException e) {
                    // Handle cases where the DOM updated right before we clicked
                    System.out.println("Element stale, attempting JS click backup...");
                    evaluateJavascript("arguments[0].click();", element);
                    return; // Exit after JS click
                }
            }
        }

        // If the loop finishes without finding or clicking anything
        throw new RuntimeException(
                "Could not find search item containing: '" + searchItem + "'"
        );
    }


}