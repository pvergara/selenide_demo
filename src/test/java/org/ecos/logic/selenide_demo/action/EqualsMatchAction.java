package org.ecos.logic.selenide_demo.action;

public abstract class EqualsMatchAction implements Action
{
    private final String actionCode;

    public EqualsMatchAction(String actionCode) {
        this.actionCode = actionCode;
    }

    @Override
    public boolean match(String filterName) {
        return this.actionCode.equals(filterName);
    }
}
