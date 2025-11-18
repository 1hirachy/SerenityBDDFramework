Feature: Google Search Feature (uses testdata.properties)

  Scenario: Search and select suggestions using google
    Given the user is on the google page
    When the user enters search item
    And the user collect the search results
    Then the user should click the suggestion
