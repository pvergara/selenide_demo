package org.ecos.logic.selenide_demo.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.ecos.logic.selenide_demo.pages.TelerikKendoPage;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class TelerikKendoSteps {

    private static final String FILTER_NAME_PRODUCT_NAME = "Name";
    private static final String FILTER_NAME_PRODUCT_CATEGORY = "Category";
    private static final String FILTER_NAME_DISCONTINUED = "Discontinued";
    @Autowired
    private TelerikKendoPage page;

    private int numberOfRows;

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
    public void iTypeOnTheNameFilter(String filterValue,String filterName) {
        if(filterName.equals(FILTER_NAME_PRODUCT_NAME))
            this.page.typeTheFilterValueOnNameFilteringField(filterValue);

        if(filterName.equals(FILTER_NAME_PRODUCT_CATEGORY))
            this.page.typeTheFilterValueOnCategoryFilteringField(filterValue);

        if(filterName.equals(FILTER_NAME_DISCONTINUED))
            this.page.typeTheFilterValueOnIsDiscontinuedField(filterValue);

    }

    @Then("The number of rows decrease")
    public void theNumberOfRowsDecrease() {
        assertThat(this.page.getTheNumberOfRows()).isLessThan(this.getNumberOfRows());
    }
}
