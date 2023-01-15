package com.simbirsoft.domain;

public enum MenuItem {
    CODE("Code", ""),
    ISSUES("Issues", "/issues");

    private final String menuName;
    private final String urlName;

    MenuItem(String menuName, String urlName) {
        this.menuName = menuName;
        this.urlName = urlName;
    }

    public String getMenuItem() {
        return menuName;
    }

    public String getUrlName() {
        return urlName;
    }
}
