package org.ecos.logic.selenide_demo.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.ecos.logic.selenide_demo.grid.control.row.RowAs;
import org.ecos.logic.selenide_demo.grid.control.row.RowSingleImpl;
import org.ecos.logic.selenide_demo.grid.kendo.sort.Mapper;
import org.ecos.logic.selenide_demo.grid.kendo.sort.Product;
import org.springframework.boot.test.context.TestComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;
import static org.ecos.logic.selenide_demo.grid.control.row.RowPlace.First;
import static org.ecos.logic.selenide_demo.grid.control.row.RowPlace.Next;

@TestComponent
public class TelerikKendoSortPage {

    public static final String TELERIK_SORT_GRID_ROW_STRING_PARTIAL_SELECTOR = "tr[data-grid-row-index='%s'] td[aria-colindex='%d']";
    public static final String ROW_COUNTER_ATTRIBUTE = "aria-rowcount";
    public static final int NUMBER_OF_COLUMNS = 3;
    private final Mapper mapper = new Mapper();

    public final SelenideElement telerik_sort_grid_row_counter = Selenide.$(".k-grid.k-grid-md.k-grid-virtual > .k-grid-aria-root");

    public void openTelerikSortingPage() {
        open("https://demos.telerik.com/kendo-react-ui/grid/sorting/func?theme=default-main-dark");
    }

    public List<Product> getAllGridRows() {
        List<RowAs<Product>> result = new ArrayList<>();
        String selectorKey = TELERIK_SORT_GRID_ROW_STRING_PARTIAL_SELECTOR;
        int rowCount = Integer.parseInt(Objects.requireNonNull(this.telerik_sort_grid_row_counter.getAttribute(ROW_COUNTER_ATTRIBUTE)));

        for (int i = 0; i < rowCount; i++) {
            RowAs<Product> row = new RowSingleImpl<>(NUMBER_OF_COLUMNS);

            SelenideElement firstColumn = $(format(selectorKey, i, 1));
            SelenideElement secondColumn = $(format(selectorKey, i, 2));
            SelenideElement thirdColumn = $(format(selectorKey, i, 3));

            firstColumn.click();

            row.setThe(First).elementAs(() -> Integer.valueOf(firstColumn.getText()));
            row.setThe(Next).elementAs(secondColumn::getText);
            row.setThe(Next).elementAs(()-> Float.valueOf(thirdColumn.getText()));

            result.add(row);
        }

        return result.stream().map(item->item.getAllElementsAs(this.mapper)).toList();
    }


    public void sortBy(String columNameSelector) {
        $(columNameSelector).click();
    }
}
