Feature: Filter, sorting and paginating a grid

  @Telerik_grid @name_filter
  Scenario: User can filter by product names
    Given Open browser with Telerik Kendo Grid example
    And The number of rows are the default ones
    When I type "Che" on "Name" filter
    Then The "Name" rows only has text that contains "Che"
    Then The number of rows decrease

  @Telerik_grid @category_filter
  Scenario: User can filter by product categories
    Given Open browser with Telerik Kendo Grid example
    And The number of rows are the default ones
    When I type "Grain" on "Category" filter
    Then The "Category" rows only has text that contains "Grain"
    And The number of rows decrease

  @Telerik_grid @discontinued_filter
  Scenario: User can filter by if is or not discontinued
    Given Open browser with Telerik Kendo Grid example
    And The number of rows are the default ones
    When I type "false" on "Discontinued" filter
    Then The "Discontinued" rows only has text that contains "false"
    And The number of rows decrease

  @Telerik_grid @price_filter
  Scenario: User can filter by product price
    Given Open browser with Telerik Kendo Grid example
    And The number of rows are the default ones
    When I type "18" on "Price" filter
    Then The "Price" rows only has text that contains "18"
    And The number of rows decrease

  @Telerik_grid @in_Stock_filter
  Scenario: User can filter by product in stock quantity
    Given Open browser with Telerik Kendo Grid example
    And The number of rows are the default ones
    When I type "0" on "In Stock" filter
    Then The "In Stock" rows only has text that contains "0"
    And The number of rows decrease


    #Add a "Numeric filtering"
    #   * filter==0 is not the same than filter field that contains "0"
    #   * filter>=x
    #   * filter<x

    #Add filtering combinations
    # (ie category and price filter AS NUMBER)