Feature: Filter, sorting and paginating a grid

  Scenario: User can filter by product names
    Given Open browser with Telerik Kendo Grid example
    And The number of rows are the default ones
    When I type "Che" on "Name" filter
    Then The number of rows decrease

  Scenario: User can filter by product categories
    Given Open browser with Telerik Kendo Grid example
    And The number of rows are the default ones
    When I type "Grain" on "Category" filter
    Then The number of rows decrease

  Scenario: User can filter by if is or not discontinued
    Given Open browser with Telerik Kendo Grid example
    And The number of rows are the default ones
    When I type "false" on "Discontinued" filter
    Then The number of rows decrease