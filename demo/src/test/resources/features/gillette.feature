Feature: Gillette Website Tests

  Background:
    Given I am on the Gillette website

  Scenario Outline: Verify the search functionality
    When I search for "<searchText>" using the search option
    Then I should see "Results For Razor'" at the top of the search results
    
    Examples:
    | searchText |
    | Razor      |

  Scenario: Verify the display of Gillette MACH3 Turbo under products list
    When I hover on from the top navigation
    And I choose "MACH3" under "By Brands" from the drop-down
    Then I should see "Gillette MACH3 Turbo" under the products list

  Scenario: Verify the presence of "Facial Hair Styles: The Anchor Beard" under styling articles
    When I click on "Styling" under "Learn" in the footer
    Then I should see "Facial Hair Styles: The Anchor Beard" under the list of articles under styling