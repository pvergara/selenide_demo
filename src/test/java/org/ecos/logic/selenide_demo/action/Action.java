package org.ecos.logic.selenide_demo.action;

public interface Action {
    boolean match(String filterName);

    void execute(String filterValue);
}