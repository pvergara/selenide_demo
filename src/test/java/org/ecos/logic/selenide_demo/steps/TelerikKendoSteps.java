package org.ecos.logic.selenide_demo.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.ecos.logic.selenide_demo.action.Action;
import org.ecos.logic.selenide_demo.action.EqualsMatchAction;
import org.ecos.logic.selenide_demo.pages.TelerikKendoPage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class TelerikKendoSteps {
    private static final String FILTER_NAME_PRODUCT_NAME = "Name";
    private static final String FILTER_NAME_PRODUCT_CATEGORY = "Category";
    private static final String FILTER_NAME_DISCONTINUED = "Discontinued";
    private static final String FILTER_NAME_PRICE = "Price";
    private static final String FILTER_NAME_IN_STOCK = "In Stock";

    @Autowired
    private TelerikKendoPage page;

    private int numberOfRows;
    private final List<Action> filteringActions;

    public TelerikKendoSteps() {
        this.filteringActions = new ArrayList<>();
        this.filteringActions.add(new TelerikKendoFilteringAction(FILTER_NAME_PRODUCT_NAME, "ProductName Filter"));
        this.filteringActions.add(new TelerikKendoFilteringAction(FILTER_NAME_PRODUCT_CATEGORY, "Category.CategoryName Filter"));
        this.filteringActions.add(new TelerikKendoFilteringAction(FILTER_NAME_DISCONTINUED, "Discontinued Filter"));
        this.filteringActions.add(new TelerikKendoFilteringAction(FILTER_NAME_PRICE, "UnitPrice Filter"));
        this.filteringActions.add(new TelerikKendoFilteringAction(FILTER_NAME_IN_STOCK, "UnitsInStock Filter"));
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    @Given("Open browser with Telerik Kendo Grid example")
    public void anOpenBrowserWithDuckduckgoCom() {
        this.page.openTelerikPage();

    }

    @Given("The number of rows are the default ones")
    public void theNumberOfRowsAreTheDefaultOnes() {
        this.setNumberOfRows(this.page.getTheNumberOfRows());
    }

    @When("I type {string} on {string} filter")
    public void iTypeOnTheNameFilter(String filterValue, String filterName) {
        for (Action filteringAction : filteringActions) {
            if (filteringAction.match(filterName))
                filteringAction.execute(filterValue);
        }
    }

    @Then("The number of rows decrease")
    public void theNumberOfRowsDecrease() {
        assertThat(this.page.getTheNumberOfRows()).isLessThan(this.getNumberOfRows());
    }

    @Then("The {string} rows only has text that contains {string}")
    public void theRowsOnlyHasTextThatContains(String columnName, String filterValue) {
        if(columnName.equals(FILTER_NAME_PRODUCT_NAME)) {
            List<String> result = this.page.getAllValuesOfProductName();
            assertThat(
                result.stream().allMatch(value -> value.toLowerCase().contains(filterValue.toLowerCase()))
            ).isTrue();
        } else if(columnName.equals(FILTER_NAME_DISCONTINUED)) {
            List<String> result = this.page.getAllValuesOfIsDiscontinuedField();
            assertThat(
                    result.stream().allMatch(value -> value.toLowerCase().contains(filterValue.toLowerCase()))
            ).isTrue();
        } else {
            throw new IllegalArgumentException("The column name '%s' does not exist".formatted(columnName));
        }
    }

    private class TelerikKendoFilteringAction extends EqualsMatchAction {
        private final String innerFilterValue;

        public TelerikKendoFilteringAction(String filterNameProductName, String filterName) {
            super(filterNameProductName);
            this.innerFilterValue = filterName;
        }

        @Override
        public void execute(String filterValue) {
            TelerikKendoSteps.this.page.typeTheFilterValueOnAFieldCalled(this.innerFilterValue, filterValue);
        }
    }
}
