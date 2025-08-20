package org.ecos.logic.selenide_demo.grid.control;

import org.jspecify.annotations.NonNull;

public interface Row {
    Row setThe(@NonNull RowPlace text);
    void elementAs(@NonNull Integer aInteger);
    void elementAs(@NonNull String text);
    void elementAs(@NonNull Float aFloat);

    String getTheElementAsString(RowPlace rowPlace);

    Integer getTheElementAsInteger(RowPlace rowPlace);

    Float getTheElementAsFloat(RowPlace rowPlace);
}
