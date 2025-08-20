package org.ecos.logic.selenide_demo.grid.control.row;

import org.ecos.logic.selenide_demo.grid.control.parser.ParserFloat;
import org.ecos.logic.selenide_demo.grid.control.parser.ParserInteger;
import org.ecos.logic.selenide_demo.grid.control.parser.ParserString;

public interface RowAndParse extends Row {
    void elementAs(ParserInteger parserInteger);
    void elementAs(ParserFloat parserFloat);
    void elementAs(ParserString parserString);
}
