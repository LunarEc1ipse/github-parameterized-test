package com.simbirsoft;

import com.simbirsoft.domain.MenuItem;
import com.simbirsoft.page.GithubMainPage;
import com.simbirsoft.page.GithubRepoPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.currentFrameUrl;
import static com.simbirsoft.page.GithubMainPage.URL;

public class GithubParameterizedTest {

    private final String baseUrl = "https://github.com/junit-team/junit5";
    private GithubMainPage mainPage = new GithubMainPage();
    private GithubRepoPage repoPage = new GithubRepoPage();

    @ValueSource(strings = {
            "junit5",
            "selenide",
            "allure"
    })
    @ParameterizedTest(name = "Open {0} repository")
    void repositorySearchTest(String searchQuery) {
        open(URL);
        mainPage.doSearch(searchQuery)
                .openSearchRepo()
                .checkRepoName(searchQuery);
    }

    @CsvSource(value = {
            "junit5, Sam Brannen",
            "selenide, Andrei Solntsev",
            "allure, Dmitry Baev"
    })
    @ParameterizedTest(name = "Check best contributor {1} for repository {0}")
    void checkRepositoryBestContributor(String searchQuery, String contributor) {
        open(URL);
        mainPage.doSearch(searchQuery)
                .openSearchRepo()
                .getBestContributor(contributor);
    }

    @EnumSource(value = MenuItem.class)
    @ParameterizedTest
    void severalMenuTest(MenuItem menuItem) {
        open(baseUrl);
        repoPage.getMenuItem(menuItem);
        webdriver().shouldHave(currentFrameUrl(baseUrl + menuItem.getUrlName()));
    }
}
