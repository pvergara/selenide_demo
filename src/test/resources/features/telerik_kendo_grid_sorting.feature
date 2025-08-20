Feature: Filter, sorting and paginating a grid

  @Telerik_grid_sort @name_sort @sorting
  Scenario: User can sort by product names
    Given Open browser with Telerik Kendo Grid Sorting example
    And The rows are unsorted
    When The user sorts the Telerik Kendo Grid by "Product Name" column
    Then Now the rows are sorted by the "Product Name" column
