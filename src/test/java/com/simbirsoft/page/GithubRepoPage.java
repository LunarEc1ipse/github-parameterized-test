package com.simbirsoft.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.simbirsoft.domain.MenuItem;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GithubRepoPage {


    private SelenideElement pageHeader = $("#repository-container-header");

    public void checkRepoName(String repo) {
        pageHeader.shouldHave(Condition.text(repo));
    }

    public void checkPoolRequestCount(int poolRequestCount) {
        $("#pull-requests-repo-tab-count").shouldHave(Condition.text(String.valueOf(poolRequestCount)));
    }

    public void checkIssuesCount(int issuesCount) {
        $("#issues-repo-tab-count").shouldHave(Condition.text(String.valueOf(issuesCount)));
    }

    public void getBestContributor(String contributor) {
        $(".BorderGrid").$(byText("Contributors")).closest("div")
                .$("ul li").hover();
        $$(".Popover-message").filterBy(visible).first()
                .shouldHave(text(contributor));
    }

    public GithubRepoPage getMenuItem(MenuItem menuItem) {
        $$(".UnderlineNav-body li").
                find(Condition.text(menuItem.getMenuItem())).click();
        return this;
    }
}
