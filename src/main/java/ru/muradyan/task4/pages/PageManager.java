package ru.muradyan.task4.pages;


public class PageManager {

    private static PageManager INSTANCE = null;
    private StartPage startPage;
    private HomePage homePage;
    private SettingsPage settingsPage;
    private NewsPage newsPage;
    private SchedulePage schedulePage;

    private PageManager() {
    }

    public static PageManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PageManager();
        }
        return INSTANCE;
    }

    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public SettingsPage getSettingsPage() {
        if (settingsPage == null) {
            settingsPage = new SettingsPage();
        }
        return settingsPage;
    }

    public NewsPage getNewsPage() {
        if (newsPage == null) {
            newsPage = new NewsPage();
        }
        return newsPage;
    }

    public SchedulePage getSchedulePage() {
        if (schedulePage == null) {
            schedulePage = new SchedulePage();
        }
        return schedulePage;
    }

}

