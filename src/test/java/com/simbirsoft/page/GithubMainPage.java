package com.simbirsoft.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class GithubMainPage {

    public static String URL = "https://github.com/";

    private SelenideElement searchInput = $(".header-search-input");

    public GithubResultsPage doSearch(String searchQuery) {
        searchInput.setValue(searchQuery).pressEnter();
        return new GithubResultsPage();
    }
}
