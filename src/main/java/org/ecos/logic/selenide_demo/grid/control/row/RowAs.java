package org.ecos.logic.selenide_demo.grid.control.row;

import org.ecos.logic.selenide_demo.grid.control.mapping.RowMapper;
import org.jspecify.annotations.NonNull;

public interface RowAs<T> extends RowAndParse {
    RowAs<T> setThe(@NonNull RowPlace rowPlace);

    T getAllElementsAs(RowMapper<T> mapper);
}
