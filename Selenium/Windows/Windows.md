Selenium Multiple Windows & Tabs

1. What is a Window Handle?

A window handle is a unique String identifier assigned to eachbrowser window or tab.

Selenium uses window handles to switch between tabs/windows.

String parentWindow = driver.getWindowHandle();

2. getWindowHandle()

Returns the handle of the currently active window/tab.

String parentWindow = driver.getWindowHandle();

Remember: getWindowHandle() = ONE/current window.

3. getWindowHandles()

Returns the handles of all currently open windows/tabs.

Set<String> windows = driver.getWindowHandles();

Return type:

Set<String>

Remember: getWindowHandles() = ALL open windows.

4. getWindowHandle() vs getWindowHandles()

Method                 Returns         Purpose

getWindowHandle()    String        Current window/tabgetWindowHandles()   Set<String>   All open windows/tabs

5. Switch to Another Window / Tab

driver.switchTo().window(windowHandle);

After switching, Selenium commands operate in that window/tab.

6. Standard Parent → Child → Parent Flow

// Save parent window
String parentWindow = driver.getWindowHandle();

// Action that opens a new tab/window
driver.findElement(By.id("openTab")).click();

// Get all open windows
Set<String> windows = driver.getWindowHandles();

// Find and switch to child
for (String window : windows) {
    if (!window.equals(parentWindow)) {
        driver.switchTo().window(window);
        break;
    }
}

// Work in child window
driver.findElement(By.id("username")).sendKeys("testuser");

// Return to parent
driver.switchTo().window(parentWindow);

Flow

Save parent handle
        ↓
Open new tab/window
        ↓
Get all handles
        ↓
Find handle != parent
        ↓
Switch to child
        ↓
Perform actions
        ↓
Switch back to parent

7. Why Save the Parent Window?

Always save the original window before opening/switching to another one:

String parentWindow = driver.getWindowHandle();

Then return later:

driver.switchTo().window(parentWindow);

Selenium does not automatically return to the parent.

8. Handling Multiple Windows

Set<String> windows = driver.getWindowHandles();

for (String window : windows) {
    driver.switchTo().window(window);
    System.out.println(driver.getTitle());
}

Do not rely on the order of handles.

9. Switch to a Window by Title

Useful when multiple child windows exist:

for (String window : driver.getWindowHandles()) {

    driver.switchTo().window(window);

    if (driver.getTitle().equals("Payment Page")) {
        break;
    }
}

10. Switch to a Window by URL

for (String window : driver.getWindowHandles()) {

    driver.switchTo().window(window);

    if (driver.getCurrentUrl().contains("payment")) {
        break;
    }
}

11. Closing a Child Window

close() closes the currently active window/tab.

driver.switchTo().window(childWindow);

driver.close();

driver.switchTo().window(parentWindow);

close() vs quit()

Method                              Meaning

driver.close()                    Closes current window/tab

After closing a child window, explicitly switch back to a validremaining window.

12. Window / Tab vs iFrame

Window / Tab

A separate top-level browsing context.

driver.switchTo().window(handle);

iFrame

An embedded browsing context inside a page.

driver.switchTo().frame(...);

Return from frames using:

driver.switchTo().parentFrame();
driver.switchTo().defaultContent();

Interview Answer

An iframe is an embedded browsing context inside a webpage, whereas abrowser tab or window is a separate top-level browsing context. Foriframes I use switchTo().frame(), while for tabs/windows I usewindow handles with switchTo().window().

13. Common Mistakes

Mistake 1: Forgetting to save the parent

Prefer:

String parentWindow = driver.getWindowHandle();

driver.findElement(By.id("open")).click();

Mistake 2: Assuming handle order

Do not assume the first or second item in getWindowHandles() is aparticular window.

Identify the required window by comparing handles, title, URL, oranother reliable property.

Mistake 3: Finding child elements before switching

Wrong context:

driver.findElement(By.id("payment")).click();

Correct:

driver.switchTo().window(childWindow);
driver.findElement(By.id("payment")).click();

Mistake 4: Forgetting to switch back

driver.switchTo().window(parentWindow);

14. Important Interview Questions

Q1. What is a window handle?

A unique String identifier for a browser window/tab.

Q2. What does getWindowHandle() return?

The handle of the currently active window/tab.

String handle = driver.getWindowHandle();

Q3. What does getWindowHandles() return?

A Set<String> containing handles of all currently open windows/tabs.

Set<String> handles = driver.getWindowHandles();

Q4. How do you switch to another window?

driver.switchTo().window(handle);

Q5. How do you return to the parent?

driver.switchTo().window(parentWindow);

Q6. How do you handle multiple windows?

I save the parent handle, trigger the action that opens the newwindow, retrieve all handles with getWindowHandles(), identify therequired window, switch using switchTo().window(handle), perform therequired actions, and switch back to the saved parent handle.

Q7. Difference between close() and quit()?

close() closes the current window/tab.

quit() ends the entire WebDriver session and closes all associatedwindows.

15. Quick Revision Cheat Sheet

// Current window
String parent = driver.getWindowHandle();

// All windows
Set<String> windows = driver.getWindowHandles();

// Switch
driver.switchTo().window(handle);

// Close current window
driver.close();

// End entire WebDriver session
driver.quit();

// Return to parent
driver.switchTo().window(parent);

Standard Pattern

String parent = driver.getWindowHandle();

driver.findElement(By.id("openTab")).click();

Set<String> windows = driver.getWindowHandles();

for (String window : windows) {
    if (!window.equals(parent)) {
        driver.switchTo().window(window);
        break;
    }
}

// Work in child

driver.switchTo().window(parent);

16. Memory Trick

getWindowHandle()
→ ONE / current window

getWindowHandles()
→ ALL windows

switchTo().window(handle)
→ SWITCH window/tab

close()
→ CLOSE current window/tab

quit()
→ END entire WebDriver session

Key Takeaway

For Selenium window handling, remember this sequence:

Save parent → Open child → Get handles → Identify child → Switch →Work → Switch back.