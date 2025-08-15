package org.ecos.logic.selenide_demo.steps;

import io.cucumber.core.gherkin.FeatureParserException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.ecos.logic.selenide_demo.action.Action;
import org.ecos.logic.selenide_demo.action.ActionByKey;
import org.ecos.logic.selenide_demo.action.EqualsMatchAction;
import org.ecos.logic.selenide_demo.pages.TelerikKendoPage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.ecos.logic.selenide_demo.utils.Constants.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class TelerikKendoSteps {
    @Autowired
    private TelerikKendoPage page;

    private int numberOfRows;
    private final List<Action> filteringActions;
    private final List<ActionByKey> filteringValidationActions;

    public TelerikKendoSteps() {
        this.filteringActions = new ArrayList<>();
        this.filteringActions.add(new TelerikKendoFilteringAction(FILTER_NAME_PRODUCT_NAME, KENDO_TABLE_PRODUCT_NAME_FILTER));
        this.filteringActions.add(new TelerikKendoFilteringAction(FILTER_NAME_PRODUCT_CATEGORY, KENDO_TABLE_CATEGORY_FILTER));
        this.filteringActions.add(new TelerikKendoFilteringAction(FILTER_NAME_DISCONTINUED, KENDO_TABLE_DISCONTINUED_FILTER));
        this.filteringActions.add(new TelerikKendoFilteringAction(FILTER_NAME_PRICE, KENDO_TABLE_UNIT_PRICE_FILTER));
        this.filteringActions.add(new TelerikKendoFilteringAction(FILTER_NAME_IN_STOCK, KENDO_TABLE_UNITS_IN_STOCK_FILTER));

        this.filteringValidationActions = new ArrayList<>();
        this.filteringValidationActions.add(new EqualsMatchAction(FILTER_NAME_PRODUCT_NAME) {
            @Override
            public void execute(String filterValue) {
                List<String> result = TelerikKendoSteps.this.page.getAllValuesOfProductName();
                assertThat(
                        result.stream().allMatch(value -> value.toLowerCase().contains(filterValue.toLowerCase()))
                ).isTrue();
            }
        });
        this.filteringValidationActions.add(new EqualsMatchAction(FILTER_NAME_PRODUCT_CATEGORY) {
            @Override
            public void execute(String filterValue) {
                List<String> result = TelerikKendoSteps.this.page.getAllValuesProductCategory();
                assertThat(
                        result.stream().allMatch(value -> value.toLowerCase().contains(filterValue.toLowerCase()))
                ).isTrue();
            }
        });
        this.filteringValidationActions.add(new EqualsMatchAction(FILTER_NAME_DISCONTINUED) {
            @Override
            public void execute(String filterValue) {
                List<String> result = TelerikKendoSteps.this.page.getAllValuesOfIsDiscontinuedField();
                assertThat(
                        result.stream().allMatch(value -> value.toLowerCase().contains(filterValue.toLowerCase()))
                ).isTrue();
            }
        });
        this.filteringValidationActions.add(new EqualsMatchAction(FILTER_NAME_PRICE) {
            @Override
            public void execute(String filterValue) {
                List<String> result = TelerikKendoSteps.this.page.getAllValuesOfPriceField();
                assertThat(
                        result.stream().allMatch(value -> value.toLowerCase().contains(filterValue.toLowerCase()))
                ).isTrue();
            }
        });

        this.filteringValidationActions.add(new EqualsMatchAction(FILTER_NAME_IN_STOCK) {
            @Override
            public void execute(String filterValue) {
                List<String> result = TelerikKendoSteps.this.page.getAllValuesOfInStockField();
                assertThat(
                        result.stream().allMatch(value -> value.toLowerCase().contains(filterValue.toLowerCase()))
                ).isTrue();
            }
        });
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
        for (Action filteringAction : this.filteringActions) {
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
        for (Action filteringValidationAction : this.filteringValidationActions) {
            if (filteringValidationAction.match(columnName))
                filteringValidationAction.execute(filterValue);
        }
        if (this.filteringValidationActions.stream().noneMatch(item -> item.match(columnName))) {
            String join = String.join(
                ",",
                this.filteringValidationActions.stream().map(item->format("'%s'",item.getKey())).toList()
            );
            String message = format("There's not colum named as '%s' inside the collection keys: %n [%s]", columnName, join);
            throw new FeatureParserException(message);
        }
    }

    @When("I type {string} on {string} filter as {string} clause")
    public void iTypeOnFilterAsClause(String filterValue, String columnName, String typeOfFiltering) {
        if(columnName.equalsIgnoreCase(FILTER_NAME_PRICE)) {
            if(typeOfFiltering.equals("Is equal to"))
                this.page.setTypeOfFilteringAs(typeOfFiltering).typeTheFilterValueOnAFieldCalled(KENDO_TABLE_UNIT_PRICE_FILTER,filterValue);
        }
        if(columnName.equalsIgnoreCase(FILTER_NAME_PRODUCT_CATEGORY)) {
            if (typeOfFiltering.equals("Starts with"))
                this.page.setTypeOfFilteringAs(typeOfFiltering).typeTheFilterValueOnAFieldCalled(KENDO_TABLE_CATEGORY_FILTER,filterValue);
        }
    }

    @Then("The {string} rows only contains the number {string}")
    public void theRowsOnlyContainsTheNumber(String columnName, String valueResultAsString) {
        if(columnName.equalsIgnoreCase(FILTER_NAME_PRICE)) {
            List<String> result = TelerikKendoSteps.this.page.getAllValuesOfPriceField();
            assertThat(
                    result.stream().allMatch(value -> Integer.valueOf(value).equals(Integer.valueOf(valueResultAsString)))
            ).isTrue();
        }
    }

    @Then("The {string} rows only contains rows that starts with {string}")
    public void theRowsOnlyContainsRowsThatStartsWith(String columnName, String filterValue) {
        if(columnName.equalsIgnoreCase(FILTER_NAME_PRODUCT_CATEGORY)) {
            List<String> result = TelerikKendoSteps.this.page.getAllValuesProductCategory();
            assertThat(
                    result.stream().allMatch(value -> value.toLowerCase().startsWith(filterValue.toLowerCase()))
            ).isTrue();
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
