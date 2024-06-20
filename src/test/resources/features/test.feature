@JupiterToys
Feature: Jupiter Toys Test

  Background:
    Given I launch the Jupiter Toys website
    Then I should see Jupiter Toys homepage

  Scenario: TC01 - Validate error message display on Contact page
    When I click on Contact from navbar
    Then I should see the Contact page
    When I click on Submit
    Then I should see the following error messages
      | Element  | Error Message                                                                          |
      | Header   | We welcome your feedback - but we won't get it unless you complete the form correctly. |
      | Forename | Forename is required                                                                   |
      | Email    | Email is required                                                                      |
      | Message  | Message is required                                                                    |
    When I populate the mandatory fields
      | Forename | Email           | Message             |
      | Tester   | tester@test.com | This is my message. |
    Then I should not see any errors on the page

  Scenario Outline: TC02 - Validate successful submission message
    When I click on Contact from navbar
    Then I should see the Contact page
    When I populate the mandatory fields
      | Forename     | Email   | Message   |
      | <forename>   | <email> | <message> |
    And I click on Submit
    Then I should see successful submission message

    Examples:
      | forename  | email            | message        |
      | Tester1   | tester1@test.com | First Message  |
      | Tester2   | tester2@test.com | Second Message |
      | Tester3   | tester3@test.com | Third Message  |
      | Tester4   | tester4@test.com | Fourth Message |
      | Tester5   | tester5@test.com | Fifth Message  |

    Scenario: TC03 - Validate Cart Total
      When I click on Shop from navbar
      Then I should see the Shop page
      When I add the following items to cart
        | Item           | Qty |
        | Stuffed Frog   | 2   |
        | Fluffy Bunny   | 5   |
        | Valentine Bear | 3   |
      When I click on Cart from navbar
      Then I should see the Cart page
      And I should see the correct subtotal for each item
      And I should see the correct prices for each item
      And I should see the correct total for all items in cart



