package ru.muradyan.ui.task3.pages;


public class PageManager {

    private static PageManager INSTANCE = null;
    private StartPage startPage;
    private LaptopPage laptopPage;

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

    public LaptopPage getLaptopPage() {
        if (laptopPage == null) {
            laptopPage = new LaptopPage();
        }
        return laptopPage;
    }

}

