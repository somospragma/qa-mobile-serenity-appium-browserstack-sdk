Feature: BrowserStack Demo

#  Scenario: BStack Sample Test - Add product to cart
#    Given I am on the website 'https://www.bstackdemo.com'
#    When I select a product and click on 'Add to cart' button
#    Then the product should be added to cart

  @Formulario1_with_autocomplete
  Scenario Outline: interaction with the first form with autocomplete
    Given the user is in Inputs Screen
    When fill out the name with the <option> suggest with <letter>

    Examples:
      | option | letter |
      | first  | a      |
#      | first |b|
#      | first |c|

