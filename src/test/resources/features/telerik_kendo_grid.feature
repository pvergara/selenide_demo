Feature: Filter, sorting and paginating a grid

  Scenario: user can search images by keyword
    Given Open browser with Telerik Kendo Grid example
    And The number of rows are the default ones
    When I type "Che" on the name filter
    Then The number of rows decrease