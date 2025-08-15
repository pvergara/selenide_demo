Feature: Filter, sorting and paginating a grid

  @Telerik_grid_sort @name_sort @sorting
  Scenario: User can sort by product names
    Given Open browser with Telerik Kendo Grid example
#    And The rows are unsorted are the default ones
    When Sort by "name" column
    Then The rows are sorted by the "name" column
