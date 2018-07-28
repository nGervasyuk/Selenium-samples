package settings;

public enum Browser {
    FIREFOX("firefox"),
    CHROME_UBUNTU("chrome_ubuntu"),
    CHROME_WIN("chrome_win");

    private String browserName;

    Browser(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserName() {
        return browserName;
    }

    public static Browser getByName(String name) {
        for (Browser browser : values()) {
            if (browser.getBrowserName().equalsIgnoreCase(name)) {
                return browser;
            }
        }
        return null;
    }
}
