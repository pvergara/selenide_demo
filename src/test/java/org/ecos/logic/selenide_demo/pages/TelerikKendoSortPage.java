package org.ecos.logic.selenide_demo.pages;

import com.codeborne.selenide.SelenideElement;
import org.ecos.logic.selenide_demo.grid.control.Row;
import org.ecos.logic.selenide_demo.grid.control.RowSingleImpl;
import org.springframework.boot.test.context.TestComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;
import static org.ecos.logic.selenide_demo.grid.control.RowPlace.*;

@TestComponent
public class TelerikKendoSortPage {

    public void openTelerikSortingPage() {
        open("https://demos.telerik.com/kendo-react-ui/grid/sorting/func?theme=default-main-dark");
    }

    public List<Row> getAllGridRows() {
        List<Row> result = new ArrayList<>();
        String selectorKey = "tr[data-grid-row-index='%s'] td[aria-colindex='%d']";
        int rowCount = Integer.parseInt(Objects.requireNonNull($(".k-grid.k-grid-md.k-grid-virtual > .k-grid-aria-root").getAttribute("aria-rowcount")));
        for (int i = 0; i < rowCount; i++) {
            Row row = new RowSingleImpl(3);

            SelenideElement firstColumn = $(format(selectorKey, i, 1));
            SelenideElement secondColumn = $(format(selectorKey, i, 2));
            SelenideElement thirdColumn = $(format(selectorKey, i, 3));

            firstColumn.click();
            row.setThe(First).elementAs(Integer.valueOf(firstColumn.getText()));
            row.setThe(Second).elementAs(secondColumn.getText());
            row.setThe(Last).elementAs(Float.valueOf(thirdColumn.getText()));

            result.add(row);
        }
        return result;
    }


    public void sortBy(String columNameSelector) {
        $(columNameSelector).click();
    }
}
