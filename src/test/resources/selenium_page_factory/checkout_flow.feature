Feature: Checkout Flow

  Background:
    Given This website "https://rahulshettyacademy.com/client" for checkout flow
    Then Open that website for checkout flow
    And Init all pages for run automation for checkout flow

  Scenario:
    When Input email "simanjuntakalbert57@gmail.com" and password "XBf@rWNvByn!#K8" in login page for checkout flow
    Then Click login button for checkout flow
    Then Search product with keyword "ZARA COAT 3" for checkout flow
    Then Click view product for checkout flow
    And Verify product name, to make sure the product "ZARA COAT 3" is shown for checkout flow
    Then Click ATC button for checkout flow
    Then Click checkout button for checkout flow
    Then Enter the details payment for checkout flow
    Then Click place order button for checkout flow
    And Verify order, to make sure "ZARA COAT 3" successfully ordered
    Then Delay for 2 seconds
    Then Teardown the test for checkout flow
