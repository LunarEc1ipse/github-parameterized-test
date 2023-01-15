package com.simbirsoft;

import com.simbirsoft.domain.MenuItem;
import com.simbirsoft.page.GithubMainPage;
import com.simbirsoft.page.GithubRepoPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.currentFrameUrl;
import static com.simbirsoft.page.GithubMainPage.URL;

public class GithubParameterizedTest {

    private final String baseUrl = "https://github.com/junit-team/junit5";
    private GithubMainPage mainPage = new GithubMainPage();
    private GithubRepoPage repoPage = new GithubRepoPage();

    static Stream<Arguments> checkRepositoryPageTest() {
        return Stream.of(
                Arguments.of(
                        "junit5", "Sam Brannen", 127, 8
                ),
                Arguments.of(
                        "selenide", "Andrei Solntsev", 27, 11
                )
        );
    }

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

    @MethodSource("checkRepositoryPageTest")
    @ParameterizedTest(name = "Check github page of repository {0}, where best contributor {1},issues count {2}" +
            " pool request count {3}")
    void checkRepositoryPageTest(String searchQuery, String contributor, int issuesCount, int poolRequestCount) {
        open(URL);
        mainPage.doSearch(searchQuery)
                .openSearchRepo()
                .getBestContributor(contributor);
        repoPage.checkIssuesCount(issuesCount);
        repoPage.checkPoolRequestCount(poolRequestCount);

    }
}
