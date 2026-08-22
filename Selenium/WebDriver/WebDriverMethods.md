Selenium WebDriver Methods --- Revision Notes

1. Browser Navigation

get()

Opens a URL.

driver.get("https://example.com");

navigate().to()

Navigates to a URL.

driver.navigate().to("https://example.com");

back()

Navigates to the previous page.

driver.navigate().back();

forward()

Navigates to the next page.

driver.navigate().forward();

refresh()

Refreshes the current page.

driver.navigate().refresh();

2. Browser Information

getTitle()

Returns the title of the current page.

String title = driver.getTitle();

getCurrentUrl()

Returns the current URL.

String url = driver.getCurrentUrl();

getPageSource()

Returns the HTML source of the current page.

String source = driver.getPageSource();

3. Browser Window

maximize()

driver.manage().window().maximize();

minimize()

driver.manage().window().minimize();

fullscreen()

driver.manage().window().fullscreen();

getSize()

Dimension size = driver.manage().window().getSize();

setSize()

driver.manage().window().setSize(
    new Dimension(1920, 1080)
);

4. Closing Browser

close()

Closes the current browser window/tab.

driver.close();

quit()

Closes all browser windows and terminates the WebDriver session.

driver.quit();

Interview Difference

close()                      quit()

Closes current window/tab      Closes all windows
Session may remain             Terminates WebDriver session
Useful with multiple windows   Usually used in test cleanup

5. Cookies

Add Cookie

driver.manage().addCookie(
    new Cookie("username", "Siddarth")
);

Get Cookie

Cookie cookie =
    driver.manage().getCookieNamed("username");

Get All Cookies

Set<Cookie> cookies =
    driver.manage().getCookies();

Delete Cookie

driver.manage().deleteCookieNamed("username");

Delete All Cookies

driver.manage().deleteAllCookies();

6. Timeouts

Implicit Wait

driver.manage()
      .timeouts()
      .implicitlyWait(Duration.ofSeconds(10));

For detailed waits, see Waits.md.

WebDriver vs WebElement

WebDriver

Controls the browser.

WebDriver driver = new ChromeDriver();

WebElement

Represents an element on the webpage.

WebElement element =
    driver.findElement(By.id("username"));

Common WebElement Methods

element.click();

element.sendKeys("admin");

element.clear();

element.getText();

element.getAttribute("value");

element.isDisplayed();

element.isEnabled();

element.isSelected();

Quick Revision

get()                 → Open URL
navigate().to()       → Navigate to URL
back()                → Previous page
forward()             → Next page
refresh()             → Refresh page

getTitle()            → Page title
getCurrentUrl()       → Current URL
getPageSource()       → HTML source

maximize()            → Maximize window
minimize()            → Minimize window
fullscreen()          → Fullscreen

close()               → Close current window
quit()                → End entire WebDriver session

addCookie()           → Add cookie
getCookieNamed()      → Get cookie
getCookies()          → Get all cookies
deleteCookieNamed()   → Delete cookie
deleteAllCookies()    → Delete all cookies

Interview Questions

1. close() vs quit()?

close() closes the current browser window.

quit() closes all browser windows and terminates the WebDriver
session.

2. get() vs navigate().to()?

Both navigate to a URL.

navigate() additionally provides:

back();
forward();
refresh();

3. WebDriver vs WebElement?

WebDriver controls the browser.

WebElement represents an element inside the webpage.

4. How do you maximize the browser?

driver.manage().window().maximize();

5. How do you get the current URL?

driver.getCurrentUrl();

6. How do you get the page title?

driver.getTitle();