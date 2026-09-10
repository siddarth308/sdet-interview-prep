StaleElementReferenceException — Selenium Revision Notes

1. What is StaleElementReferenceException?

StaleElementReferenceException occurs when Selenium has a reference to a WebElement, but that element is no longer attached to the current DOM.

In simple words:

Selenium found the element earlier, but the page changed and the old element reference is no longer valid.

Mental Model

findElement()
     ↓
WebElement reference
     ↓
DOM changes / element recreated
     ↓
Old WebElement reference
     ↓
❌ STALE
     ↓
StaleElementReferenceException

2. Why Does It Occur?

A WebElement is a reference to a specific DOM element at a specific point in time.

If the application refreshes, re-renders, removes, or replaces that element, Selenium's old reference can become invalid.

Common causes:

Page refresh

Navigation to another page

AJAX/API response updating the DOM

React/Angular/Vue re-rendering

Table/list refresh

Element being removed and recreated

Switching between pages/views

Dynamic UI updates

JavaScript modifying the DOM

3. Basic Example

WebElement button = driver.findElement(By.id("submit"));

// DOM changes here
driver.navigate().refresh();

// Old reference may now be stale
button.click();

The problem is:

button
  ↓
Old DOM element ❌

After refresh:

New DOM
  ↓
New button

The variable button still points to the old element.

4. The Correct Mental Model

Bad assumption

WebElement = permanent reference to the element

Correct understanding

By locator
    ↓
findElement()
    ↓
WebElement
    ↓
specific DOM element

If that DOM element is replaced:

Old WebElement → ❌ stale

The locator itself is still usable:

By locator
    ↓
findElement()
    ↓
New WebElement

5. Common Scenario — Page Refresh

WebElement username =
        driver.findElement(By.id("username"));

driver.navigate().refresh();

username.sendKeys("Siddarth");

The old username reference can become stale after the refresh.

Better approach

By username = By.id("username");

driver.navigate().refresh();

driver.findElement(username)
      .sendKeys("Siddarth");

We locate the element again after the DOM is recreated.

6. Common Scenario — React/Angular Re-render

Modern web applications frequently rebuild parts of the DOM.

Example:

WebElement submit =
        driver.findElement(By.id("submit"));

// Some action causes UI re-render
driver.findElement(By.id("refresh")).click();

// React/Angular may replace the submit element
submit.click();

Possible result:

StaleElementReferenceException

Why?

Old Submit Element
       ↓
React re-render
       ↓
Old element removed
       ↓
New Submit Element created
       ↓
submit → old element ❌

7. Common Scenario — Dynamic Table

WebElement row =
        driver.findElement(By.xpath("//table//tr[1]"));

// Change filter
driver.findElement(By.id("filter")).click();

// Table gets refreshed

row.click();

The table may have been rebuilt.

Therefore, row may refer to an element that no longer exists in the current DOM.

8. How to Handle StaleElementReferenceException

There are four important approaches:

Re-locate the element

Use explicit waits

Avoid storing WebElement references for too long

Use controlled retries for genuinely unstable elements

9. Solution 1 — Re-locate the Element

Bad

WebElement button =
        driver.findElement(By.id("submit"));

doSomethingThatChangesDOM();

button.click();

Better

By submitButton = By.id("submit");

doSomethingThatChangesDOM();

driver.findElement(submitButton).click();

The second approach gets a fresh element reference.

10. Store Locators Instead of Long-Lived WebElements

Less reliable on highly dynamic pages

WebElement loginButton =
        driver.findElement(By.id("login"));

Then much later:

loginButton.click();

If the DOM has changed, this can become stale.

Better

By loginButton = By.id("login");

Then:

driver.findElement(loginButton).click();

The locator can be used to find the current element.

11. Solution 2 — Explicit Wait

Use WebDriverWait to synchronize with the UI.

WebDriverWait wait =
        new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

By submitButton = By.id("submit");

WebElement button = wait.until(
        ExpectedConditions.elementToBeClickable(
                submitButton
        )
);

button.click();

Why explicit waits help

Dynamic applications may need time to:

finish rendering

receive API responses

update the DOM

enable controls

remove overlays

display the final version of an element

12. Presence vs Visibility vs Clickability

These conditions are different.

Expected Condition

Meaning

presenceOfElementLocated()

Element exists in the DOM

visibilityOfElementLocated()

Element exists and is visible

elementToBeClickable()

Element is visible and enabled enough to click

invisibilityOfElementLocated()

Element is invisible or absent

Example

wait.until(
    ExpectedConditions.presenceOfElementLocated(locator)
);

This does not necessarily mean:

Element is clickable

It only means:

Element exists in DOM

13. Important: elementToBeClickable() Is Not a Magic Fix

This:

WebElement button =
        driver.findElement(By.id("submit"));

wait.until(
        ExpectedConditions.elementToBeClickable(button)
);

button.click();

can still have problems if the DOM changes after the wait returns.

A better pattern for dynamic elements is:

wait.until(
        ExpectedConditions.elementToBeClickable(
                By.id("submit")
        )
).click();

Using the locator lets the wait work with the current element.

14. Solution 3 — Retry

For genuinely unstable elements, a controlled retry can be useful.

By submitButton = By.id("submit");

WebDriverWait wait =
        new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

for (int attempt = 0; attempt < 3; attempt++) {

    try {

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(
                        submitButton
                )
        );

        button.click();
        break;

    } catch (StaleElementReferenceException e) {

        if (attempt == 2) {
            throw e;
        }
    }
}

Flow

Locate
   ↓
Wait
   ↓
Get fresh element
   ↓
Click
   ↓
Stale?
   ├── No → Done
   └── Yes
         ↓
      Re-locate
         ↓
       Retry

Do not use unlimited retries. A retry should address a known transient DOM race, not hide a real application problem.

15. StaleElementReferenceException vs NoSuchElementException

These are commonly confused.

NoSuchElementException

Selenium cannot find the element.

driver.findElement(By.id("login"));

Possible reason:

Element does not exist

StaleElementReferenceException

Selenium previously found the element, but that specific element is no longer attached to the current DOM.

Found element
     ↓
DOM changed
     ↓
Old reference invalid
     ↓
StaleElementReferenceException

Easy way to remember

NoSuchElementException
= "I can't find it."

StaleElementReferenceException
= "I found it earlier, but that element is no longer valid."

16. Why Long-Lived WebElement References Are Risky

Consider:

WebElement button =
        driver.findElement(By.id("submit"));

Then many operations happen:

clickSomething();
changeFilter();
waitForApi();
openModal();
closeModal();

Finally:

button.click();

The DOM may have changed several times.

Therefore:

Old WebElement
       ↓
Potentially stale

Better approach

Keep the locator:

By button = By.id("submit");

and locate close to the time of interaction:

driver.findElement(button).click();

17. Page Object Model — Recommended Pattern

Avoid excessive long-lived elements

public class LoginPage {

    WebElement username =
            driver.findElement(By.id("username"));

    WebElement password =
            driver.findElement(By.id("password"));

    WebElement login =
            driver.findElement(By.id("login"));
}

Prefer locators

public class LoginPage {

    private By username = By.id("username");
    private By password = By.id("password");
    private By login = By.id("login");
}

Then:

public void login(String user, String pass) {

    driver.findElement(username)
          .sendKeys(user);

    driver.findElement(password)
          .sendKeys(pass);

    driver.findElement(login)
          .click();
}

This makes it easier to obtain fresh element references.

18. scrollIntoView() and Stale Elements

Sometimes you need to scroll an element into view.

JavascriptExecutor js =
        (JavascriptExecutor) driver;

WebElement element =
        driver.findElement(By.id("submit"));

js.executeScript(
        "arguments[0].scrollIntoView(true);",
        element
);

However, remember:

scrollIntoView()

does not solve a stale reference.

If the element becomes stale, you still need to locate it again.

19. Selenium Click vs JavaScript Click

Selenium click

element.click();

Preferred.

It uses WebDriver interaction semantics and performs normal interactability checks.

JavaScript click

JavascriptExecutor js =
        (JavascriptExecutor) driver;

js.executeScript(
        "arguments[0].click();",
        element
);

This directly invokes the DOM element's JavaScript click() method.

Recommendation

Normal Selenium click
        ↓
Preferred

JavaScript click
        ↓
Fallback for a specific UI/browser limitation

Do not use JavaScript click just to make a failing test pass.

20. When Should JavaScriptExecutor Be Used?

Good use cases include:

Scroll

js.executeScript(
        "arguments[0].scrollIntoView(true);",
        element
);

Read browser-side properties

String value = (String) js.executeScript(
        "return arguments[0].value;",
        element
);

Specific browser/UI workarounds

Use it when normal WebDriver APIs cannot reliably perform the required operation.

Avoid

Using:

js.executeScript(
        "arguments[0].click();",
        element
);

as the default replacement for:

element.click();

21. Stale Element — Complete Example

By submitButton = By.id("submit");

WebDriverWait wait =
        new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

// Some action causes the page to update
driver.findElement(By.id("refresh")).click();

// Wait for the current submit button
WebElement button = wait.until(
        ExpectedConditions.elementToBeClickable(
                submitButton
        )
);

// Click fresh reference
button.click();

22. Even Better — Keep Interaction Close to the Locator

By submitButton = By.id("submit");

wait.until(
        ExpectedConditions.elementToBeClickable(
                submitButton
        )
).click();

This is concise and reduces the lifetime of the WebElement reference.

23. Interview Answer — What Is StaleElementReferenceException?

Strong SDET answer

StaleElementReferenceException occurs when Selenium has a reference to a WebElement that is no longer attached to the current DOM. It commonly happens after page refreshes, AJAX operations, or React/Angular re-renders where the original element is removed and replaced. I handle it by re-locating the element using its By locator and synchronizing with an explicit wait. For genuinely unstable elements, I can also use a controlled retry mechanism.

24. Interview Answer — How Do You Handle It?

First, I identify what is causing the DOM to change. Then I avoid reusing the stale WebElement reference. I re-locate the element using a stable locator and use an explicit wait such as elementToBeClickable or visibilityOfElementLocated. If the element is known to be transiently unstable, I use a limited retry rather than an unlimited loop.

25. Interview Answer — Why Store By Instead of WebElement?

A By represents a locator, while a WebElement represents a specific element reference from the DOM. If the DOM is dynamically updated, the original WebElement can become stale. Keeping the locator allows me to find the current version of the element when I need to interact with it.

26. Interview Scenario

Interviewer:

Your Selenium test finds a button successfully. Five seconds later, clicking the same button throws StaleElementReferenceException. What happened?

Answer:

The element was likely removed or replaced during a DOM update. Selenium's WebElement reference points to the old DOM element, which is no longer attached to the current DOM. I would re-locate the button using its locator and synchronize with the updated DOM using an explicit wait.

27. Interview Scenario

Interviewer:

Would you solve every StaleElementReferenceException with a retry?

Answer:

No. First I would identify why the element is becoming stale. If the DOM is being legitimately re-rendered, I would re-locate the element and synchronize properly. A retry is useful only for a known transient race condition. Blind retries can hide real synchronization or application problems.

28. Interview Scenario

Interviewer:

Why not simply use JavaScript click when Selenium click fails?

Answer:

JavaScript click can bypass some of Selenium's normal interaction checks. If an overlay is blocking the element, for example, JavaScript may trigger the click even though a real user couldn't. I therefore prefer to fix the underlying synchronization or interactability issue and use JavaScript only when there is a justified UI-specific reason.

29. Quick Revision Cheat Sheet

StaleElementReferenceException
        ↓
Old WebElement reference
        ↓
DOM changed
        ↓
Element removed/replaced
        ↓
Old reference invalid

Causes

✓ Page refresh
✓ Navigation
✓ AJAX updates
✓ React re-render
✓ Angular/Vue re-render
✓ Dynamic tables/lists
✓ DOM manipulation
✓ Element replaced

Solutions

✓ Re-locate element
✓ Store By locator
✓ Explicit waits
✓ Get element close to interaction
✓ Controlled retry when appropriate

Avoid

✗ Long-lived WebElement references
✗ Thread.sleep() as primary synchronization
✗ Blind/unlimited retries
✗ JavaScript click as default
✗ Using JS to hide real UI problems

30. One-Line Memory Trick

DOM changed → old WebElement became invalid → re-locate using By + wait for the new element.

31. Must-Know Code

By locator = By.id("submit");

WebDriverWait wait =
        new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

wait.until(
        ExpectedConditions.elementToBeClickable(locator)
).click();

This pattern should become second nature for SDET interviews.