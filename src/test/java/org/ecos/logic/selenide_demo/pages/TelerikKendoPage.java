package org.ecos.logic.selenide_demo.pages;

import com.codeborne.selenide.SelenideElement;
import org.springframework.boot.test.context.TestComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@TestComponent
public class TelerikKendoPage {
    private final SelenideElement pagerInfo= $("span[class='k-pager-info']");
    private final SelenideElement nameFilteringField = $("input[aria-label=\"ProductName Filter\"]");
    private final SelenideElement categoryFilteringField = $("input[aria-label=\"Category.CategoryName Filter\"]");
    private final SelenideElement isDiscontinuedFilterField = $("input[aria-label='Discontinued Filter']");
    private final SelenideElement priceFilterField = $("input[aria-label='UnitPrice Filter']");

    public void openTelerikPage(){
        open("https://demos.telerik.com/kendo-react-ui/grid/get-started-upd/func");
    }

    public int getTheNumberOfRows() {
        Pattern pattern = Pattern.compile("of ([0-9]+) items");
        Matcher matcher = pattern.matcher(pagerInfo.text());
        if(matcher.find()){
            String numberOfItemsAsString = matcher.group(1);
            return Integer.parseInt(numberOfItemsAsString);
        }
        return 0;
    }

    public void typeTheFilterValueOnNameFilteringField(String filterValue) {
        this.nameFilteringField.type(filterValue);
    }

    public void typeTheFilterValueOnCategoryFilteringField(String filterValue) {
        this.categoryFilteringField.type(filterValue);
    }

    public void typeTheFilterValueOnIsDiscontinuedField(String filterValue) {
        this.isDiscontinuedFilterField.type(filterValue);
    }

    public void typeTheFilterValueOnPriceField(String filterValue) {
        this.priceFilterField.type(filterValue);
    }
}
