Feature: Saucedemo Test

  Background:
    Given I open the saucedemo site
    And I login with user "standard_user" and password "secret_sauce"
    And I am at the "Products" page
    And I add the following products to the cart:
      | Sauce Labs Backpack |
      | Sauce Labs Onesie   |

  Scenario: Remove products from the cart
    Then the number of items in the cart should be 2
    When I remove the following products from the cart:
      | Sauce Labs Backpack |
      | Sauce Labs Onesie   |
    Then the number of items in the cart should be 0

  Scenario: Checkout items without providing user details
    When I navigate to the cart
    Then the cart should contain the following items:
      | Item                | Quantity | Price |
      | Sauce Labs Backpack | 1        | 29.99 |
      | Sauce Labs Onesie   | 1        | 7.99  |
    When I click the checkout button
    And I click the continue button
    Then the first name field should have validation error
    And the last name field should have validation error
    And the postcode field should have validation error
    And the form error message should be "Error: First Name is required"
    When I clear the validation error
    Then the first name field should not have validation error
    And the last name field should not have validation error
    And the postcode field should not have validation error

  Scenario: Checkout items successfully and logout
    When I navigate to the cart
    And I click the checkout button
    And I click the continue button
    And I populate the checkout form with the following data:
      | First Name | Last Name | Postcode |
      | John       | Doe       | 12345    |
    And I click the continue button
    Then I am at the "Checkout Overview" page
    And the cart should contain the following items:
      | Item                | Quantity | Price |
      | Sauce Labs Backpack | 1        | 29.99 |
      | Sauce Labs Onesie   | 1        | 7.99  |
    And the subtotal price should be 41.02
    When I click the finish button
    Then I am at the "Checkout Complete" page
    And I should see a header with text "Thank you for your order!"
    And I logout
