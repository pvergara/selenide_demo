package org.ecos.logic.selenide_demo.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.ecos.logic.selenide_demo.grid.control.Row;
import org.ecos.logic.selenide_demo.grid.control.RowPlace;
import org.ecos.logic.selenide_demo.grid.kendo.sort.Product;
import org.ecos.logic.selenide_demo.pages.TelerikKendoSortPage;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.Collator;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class TelerikKendoSortSeps {
    @Autowired
    private TelerikKendoSortPage page;
    private List<Row> data;

    private void setData(List<Row> data) {
        this.data = data;
    }

    private List<Row> getData() {
        return data;
    }

    @Given("Open browser with Telerik Kendo Grid Sorting example")
    public void anOpenBrowserWithTelerikKendoGridSortingExample() {
        this.page.openTelerikSortingPage();
    }

    @Given("The rows are unsorted")
    public void theRowsAreUnsorted() {
        theUserSortsTheTelerikKendoGridByColumn("Product Name");
        this.setData(this.page.getAllGridRows());
    }

    @When("The user sorts the Telerik Kendo Grid by {string} column")
    public void theUserSortsTheTelerikKendoGridByColumn(String columnName) {
        String columNameSelector ="";
        if (columnName.equals("Product Name")) {
            columNameSelector ="table thead th[aria-colindex=\"2\"]";
        }
        this.page.sortBy(columNameSelector);
    }

    @Then("Now the rows are sorted by the {string} column")
    public void nowTheRowsAreSortedByTheColumn(String columnName) {
        Collator collator = Collator.getInstance(Locale.FRENCH);

        if (columnName.equals("Product Name")) {
            //I use the sublist in this case because of a strange way of sorting the data on the "Telerik side" with product names like "Sirop d'érable"
            List<Product> listPreviousProductNames = this.getData().stream().map(this::mapElementToProduct).sorted((item1,item2)->collator.compare(item1.ProductName(),item2.ProductName())).toList().
                    subList(0, 50);

            List<Product> listOfSortedProductNames = this.page.getAllGridRows().stream().map(this::mapElementToProduct).toList().
                    subList(0, 50);

            assertThat(listPreviousProductNames, is(equalTo(listOfSortedProductNames)));
        }
    }

    private Product mapElementToProduct(Row item) {
        return new Product(
            item.getTheElementAsInteger(RowPlace.First),
            item.getTheElementAsString(RowPlace.Second),
            item.getTheElementAsFloat(RowPlace.Third));
    }
}
