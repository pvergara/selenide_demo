package org.ecos.logic.selenide_demo.grid.kendo.sort;

import org.ecos.logic.selenide_demo.grid.control.row.Row;
import org.ecos.logic.selenide_demo.grid.control.mapping.RowMapper;
import org.ecos.logic.selenide_demo.grid.control.row.RowPlace;

public class Mapper implements RowMapper<Product> {
    @Override
    public Product fromRowToCustom(Row row) {
        return new Product(
            row.getTheElementAsInteger(RowPlace.First),
            row.getTheElementAsString(RowPlace.Second),
            row.getTheElementAsFloat(RowPlace.Third));
    }
}
