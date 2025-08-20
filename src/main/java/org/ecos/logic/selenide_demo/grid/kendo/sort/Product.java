package org.ecos.logic.selenide_demo.grid.kendo.sort;

public record Product(
        Integer ProductId,
        String ProductName,
        Float UnitPrice)
{
    public Product {
        if (UnitPrice == null) {
            UnitPrice = 0f;
        }
    }
}
