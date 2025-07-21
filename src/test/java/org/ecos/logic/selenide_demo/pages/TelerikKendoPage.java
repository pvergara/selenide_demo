package org.ecos.logic.selenide_demo.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.springframework.boot.test.context.TestComponent;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

@TestComponent
public class TelerikKendoPage {
    public static final String FILTER = "input[aria-label='%s']";
    private final SelenideElement pagerInfo= $("span[class='k-pager-info']");
    private final ElementsCollection tbodyRows = $$("tbody tr");

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

    public void typeTheFilterValueOnAFieldCalled(String productNameFilter, String filterValue) {
        $(format(FILTER, productNameFilter)).type(filterValue);
    }

    public List<String> getAllValuesOfProductName() {
        return this.
            tbodyRows.stream().
            map(e->e.$("td[aria-colindex='2']").getText()).toList();
    }
}
