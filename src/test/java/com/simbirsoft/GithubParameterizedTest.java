package com.simbirsoft;

import com.simbirsoft.page.GithubMainPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.open;
import static com.simbirsoft.page.GithubMainPage.URL;

public class GithubParameterizedTest {

    private GithubMainPage mainPage = new GithubMainPage();

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
    void checkRepositoryBestContributor(String searchQuery, String contributor){
        open(URL);
        mainPage.doSearch(searchQuery)
                .openSearchRepo()
                .getBestContributor(contributor);
    }
}
