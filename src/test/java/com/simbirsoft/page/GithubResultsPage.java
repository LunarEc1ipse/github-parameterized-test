package com.simbirsoft.page;

import static com.codeborne.selenide.Selenide.$$;

public class GithubResultsPage {

    public GithubRepoPage openSearchRepo() {
        $$(".repo-list").first().$("em").click();
        return new GithubRepoPage();
    }
}
