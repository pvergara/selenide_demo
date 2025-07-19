package org.ecos.logic.selenide_demo.pages;

import org.springframework.boot.test.context.TestComponent;

import static com.codeborne.selenide.Selenide.open;

@TestComponent
public class TelerikKendoPage {
    public void openTelerikPage(){
        open("https://demos.telerik.com/kendo-react-ui/grid/get-started-upd/func?theme=default-ocean-blue-a11y");
    }

}
