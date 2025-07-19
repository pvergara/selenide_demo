package org.ecos.logic.selenide_demo.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;
import org.ecos.logic.selenide_demo.pages.TelerikKendoPage;


public class MyStepdefs {

    private final TelerikKendoPage page;

    public MyStepdefs(TelerikKendoPage page) {
        this.page = page;
    }

    @Given("an open browser with duckduckgo.com")
    public void anOpenBrowserWithDuckduckgoCom() {
        this.page.openTelerikPage();

    }
}
