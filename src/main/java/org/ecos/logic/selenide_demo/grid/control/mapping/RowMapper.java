package org.ecos.logic.selenide_demo.grid.control.mapping;

import org.ecos.logic.selenide_demo.grid.control.row.Row;

public interface RowMapper<T> {
    T fromRowToCustom(Row row);
}
