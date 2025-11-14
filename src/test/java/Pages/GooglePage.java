package Pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GooglePage extends PageObject {


    // Using 'name="q"' as the stable locator for the search input
    @FindBy(name = "q")
    WebElementFacade searchBar;

    @FindBy(css = "ul[role='listbox']")
    WebElementFacade dropDownList;

    By dropDownElementsLocator = By.cssSelector("li.sbct div[role='option']");

    List<WebElementFacade> dropDownElementsList;

    public void maximize() {
        getDriver().manage().window().maximize();
    }

    /**
     * Step 1: Enters the search query into the search bar.
     */
    public void searchItem(String input) {
        searchBar.waitUntilVisible().clear();
        searchBar.type(input);

        //small delay to allow dropdown suggestion to load
        waitABit(1000);
    }

    /**
     * Step 2: Waits for the dropdown to appear and collects the suggestion elements.
     */
    public void collectItems() {

      try {
          // Using Explicit wait for the dropdown container to be visible
          dropDownList.waitUntilVisible();

          // Wait until there is at least one item inside the list
          waitForCondition().until(d -> dropDownList.findElements(dropDownElementsLocator).size() > 0);

          // Populating the list using Serenity's findAll(By) method
          dropDownElementsList = findAll(dropDownElementsLocator);

      } catch (Exception e){
          System.out.println("DropDown is not visible (since we are running using github cli),");
          dropDownElementsList = null;
      }

    }

    /**
     * Step 3: Iterates through the collected items and clicks the specific match.
     *
     * @param searchItem The exact text of the item to select
     */
    public void selectItem(String searchItem) {
        if (dropDownElementsList == null && dropDownElementsList.isEmpty()) {

            System.out.println("No matching suggestion found, pressing ENTER instead.");
            searchBar.sendKeys(Keys.ENTER);
        }

        for (WebElementFacade element : dropDownElementsList) {
            String ariaLabel = element.getAttribute("aria-label");
            if (ariaLabel != null && ariaLabel.toLowerCase().contains(searchItem.trim().toLowerCase())) {
                element.click();
                //System.out.println("Clicked suggestion: " + ariaLabel);
                return;
            }
        }

        // Fallback: hit Enter key to perform search anyway
        searchBar.sendKeys(Keys.ENTER);
    }

}