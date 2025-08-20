package org.ecos.logic.selenide_demo.grid.control.row;

import org.ecos.logic.selenide_demo.grid.control.mapping.RowMapper;
import org.ecos.logic.selenide_demo.grid.control.parser.ParserFloat;
import org.ecos.logic.selenide_demo.grid.control.parser.ParserInteger;
import org.ecos.logic.selenide_demo.grid.control.parser.ParserString;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class RowSingleImpl<T> implements RowAs<T> {
    private final List<String> listOfElements;
    private int index;
    private final int numberOfColumns;

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public RowSingleImpl(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
        this.listOfElements = new ArrayList<>(numberOfColumns);
        for (int i = 0; i < numberOfColumns; i++) {
            listOfElements.add(null);
        }
        this.setThe(RowPlace.First);
    }

    @Override
    public RowAs<T> setThe(@NonNull RowPlace rowPlace) {
        switch (rowPlace) {
            case First -> this.index = 0;
            case Second -> this.index = 1;
            case Third -> this.index = 2;
            case Fourth -> this.index = 3;
            case Fifth -> this.index = 4;
            case Sixth -> this.index = 5;
            case Seventh -> this.index = 6;
            case Eighth -> this.index = 7;
            case Ninth -> this.index = 8;
            case Tenth -> this.index = 9;
            case Eleventh -> this.index = 10;
            case Previous -> {
                if (this.index > 0) this.index--;
            }
            case Next -> {
                if (this.index < this.getNumberOfColumns() - 1) this.index++;
            }
            case Last -> this.index = this.getNumberOfColumns() - 1;
        }
        return this;
    }

    @Override
    public void elementAs(@NonNull Integer element) {
        this.listOfElements.set(this.index, String.valueOf(element));
    }

    @Override
    public void elementAs(ParserInteger parserInteger) {
        elementAs(parserInteger.parse());
    }

    @Override
    public void elementAs(@NonNull String element) {
        this.listOfElements.set(this.index, element);
    }

    @Override
    public void elementAs(@NonNull Float aFloat) {
        this.listOfElements.set(this.index, String.valueOf(aFloat));
    }

    @Override
    public void elementAs(ParserFloat parserFloat) {
        elementAs(parserFloat.parse());
    }

    @Override
    public String getTheElementAsString(RowPlace rowPlace) {
        this.setThe(rowPlace);
        return this.listOfElements.get(this.index);
    }

    @Override
    public void elementAs(ParserString parserString) {
        elementAs(parserString.parse());
    }

    @Override
    public Integer getTheElementAsInteger(RowPlace rowPlace) {
        this.setThe(rowPlace);
        return Integer.valueOf(this.listOfElements.get(this.index));
    }

    @Override
    public Float getTheElementAsFloat(RowPlace rowPlace) {
        this.setThe(rowPlace);
        return Float.valueOf(this.listOfElements.get(this.index));
    }

    @Override
    public T getAllElementsAs(RowMapper<T> mapper) {
        return mapper.fromRowToCustom(this);
    }
}
