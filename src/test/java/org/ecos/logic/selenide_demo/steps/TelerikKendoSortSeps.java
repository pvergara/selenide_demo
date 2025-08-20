package org.ecos.logic.selenide_demo.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
    private List<Product> data;

    private void setData(List<Product> data) {
        this.data = data;
    }

    private List<Product> getData() {
        return data;
    }

    @Given("Open browser with Telerik Kendo Grid Sorting example")
    public void anOpenBrowserWithTelerikKendoGridSortingExample() {
        this.page.openTelerikSortingPage();
    }

    @Given("The rows are unsorted")
    public void theRowsAreUnsorted() {
        //By default, the grid is sorted by Product Name Desc (the "reverse" order) so we sort it by Product Name
        // to have the data grid unsorted
        this.letTheGridUnsorted();
        this.setData(this.page.getAllGridRows());
    }

    private void letTheGridUnsorted() {
        theUserSortsTheTelerikKendoGridByColumn("Product Name");
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
            List<Product> listPreviousProductNames = this.getData().stream().
                    sorted((item1, item2)->collator.compare(item1.ProductName(),item2.ProductName())).toList().
                    subList(0, 50);

            List<Product> listOfSortedProductNames = this.page.getAllGridRows().stream().
                    toList().subList(0, 50);

            assertThat(listPreviousProductNames, is(equalTo(listOfSortedProductNames)));
        }
    }
}
