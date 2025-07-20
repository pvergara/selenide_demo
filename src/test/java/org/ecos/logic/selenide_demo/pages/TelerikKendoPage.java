package org.ecos.logic.selenide_demo.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.springframework.boot.test.context.TestComponent;

import static com.codeborne.selenide.Selenide.*;

@TestComponent
public class TelerikKendoPage {
    private final ElementsCollection tableRows= $$("tbody tr");
    private final SelenideElement nameFilteringField = $("input[aria-label=\"ProductName Filter\"]");
    private final SelenideElement categoryFilteringField = $("input[aria-label=\"Category.CategoryName Filter\"]");

    public void openTelerikPage(){
        open("https://demos.telerik.com/kendo-react-ui/grid/get-started-upd/func");
    }

    public int getTheNumberOfRows() {
        return this.tableRows.size();
    }

    public void typeTheFilterValueOnNameFilteringField(String filterValue) {
        this.nameFilteringField.type(filterValue);
    }

    public void typeTheFilterValueOnCategoryFilteringField(String filterValue) {
        this.categoryFilteringField.type(filterValue);
    }
}
