Selenium Waits --- Revision Notes

Why Do We Need Waits?

Web applications are dynamic.

An element may:

Take time to appear

Take time to become visible

Take time to become clickable

Be loaded after an API response

Waits provide synchronization between Selenium and the application.

Types of Waits

Implicit Wait

Explicit Wait

Fluent Wait

Thread.sleep() → Java sleep, NOT a Selenium wait

1. Implicit Wait

Applies globally to element-finding operations.

driver.manage()
      .timeouts()
      .implicitlyWait(Duration.ofSeconds(10));

Key Points

Global for the WebDriver session

Applies to element location

Selenium keeps polling until element is found or timeout expires

Simple but less flexible

2. Explicit Wait

Waits for a specific condition.

WebDriverWait wait =
    new WebDriverWait(driver, Duration.ofSeconds(10));

WebElement element =
    wait.until(
        ExpectedConditions.visibilityOfElementLocated(
            By.id("username")
        )
    );

Key Points

Condition-based

Applied to specific operations

Better for dynamic elements

Most commonly preferred in automation frameworks

Common ExpectedConditions

Element Present

Element exists in the DOM.

wait.until(
    ExpectedConditions.presenceOfElementLocated(
        By.id("username")
    )
);

Element Visible

Element exists and is visible.

wait.until(
    ExpectedConditions.visibilityOfElementLocated(
        By.id("username")
    )
);

Element Clickable

Element is visible and enabled for clicking.

wait.until(
    ExpectedConditions.elementToBeClickable(
        By.id("login")
    )
);

Element Invisible

wait.until(
    ExpectedConditions.invisibilityOfElementLocated(
        By.id("loader")
    )
);

Alert Present

wait.until(
    ExpectedConditions.alertIsPresent()
);

URL Contains

wait.until(
    ExpectedConditions.urlContains("dashboard")
);

Title Contains

wait.until(
    ExpectedConditions.titleContains("Dashboard")
);

3. Fluent Wait

Provides more control over:

Timeout

Polling interval

Ignored exceptions

Wait<WebDriver> wait =
    new FluentWait<>(driver)
        .withTimeout(Duration.ofSeconds(30))
        .pollingEvery(Duration.ofSeconds(2))
        .ignoring(NoSuchElementException.class);

Example:

WebElement element =
    wait.until(driver ->
        driver.findElement(By.id("username"))
    );

Key Point

FluentWait = Explicit Wait + Custom Polling + Exception Handling

4. Thread.sleep()

Thread.sleep(5000);

Pauses execution for exactly 5 seconds.

Why Avoid It?

It uses a fixed wait.

If the element appears after 1 second:

Thread.sleep(5000)
       ↓
Waits entire 5 seconds

Explicit wait:

Element appears after 1 second
       ↓
Condition satisfied
       ↓
Continue immediately

Interview Point

Avoid Thread.sleep() in production automation unless there is a
specific reason.

Presence vs Visibility vs Clickability

Condition    Meaning

Presence     Element exists in DOM
Visibility   Element exists + is visible
Clickable    Element is visible + enabled

Presence

presenceOfElementLocated()

Does NOT guarantee the element is visible.

Visibility

visibilityOfElementLocated()

Checks that the element is visible.

Clickability

elementToBeClickable()

Checks that the element is suitable for clicking.

Implicit vs Explicit vs Fluent

Feature             Implicit   Explicit   Fluent

Scope               Global     Specific   Specific
Condition-based     No         Yes        Yes
Custom polling      No         Limited    Yes
Ignore exceptions   No         Limited    Yes
Flexibility         Low        High       Very High

Implicit + Explicit Wait

Technically, Selenium allows both.

However, avoid mixing them unnecessarily.

Combining them can make timeout behavior harder to reason about and may
result in longer-than-expected waits.

Preferred approach

Use explicit waits for dynamic UI synchronization.

Best Practices

Prefer condition-based waits

Bad:

Thread.sleep(5000);

Better:

wait.until(
    ExpectedConditions.visibilityOfElementLocated(
        By.id("result")
    )
);

Don't use huge timeouts everywhere

Avoid blindly using:

Duration.ofSeconds(60)

for every element.

Use reasonable timeout values based on the application.

Wait for the actual condition

Don't wait for an arbitrary amount of time.

Wait for:

Visibility

Presence

Clickability

URL change

Title change

Loader disappearance

Alert

Text/value/state

Quick Revision

Implicit Wait
→ Global
→ Element location
→ Simple

Explicit Wait
→ Specific condition
→ Specific operation
→ Preferred for dynamic elements

Fluent Wait
→ Explicit wait with customization
→ Custom polling
→ Ignore exceptions

Thread.sleep()
→ Fixed delay
→ Not condition-based
→ Generally avoid

Interview Questions

1. Why are waits required in Selenium?

To synchronize test execution with the application's dynamic behavior.

2. Implicit vs Explicit Wait?

Implicit: Global wait for locating elements.

Explicit: Waits for a specific condition.

3. What is FluentWait?

A customizable wait that allows timeout, polling interval, and ignored
exceptions.

4. What is polling?

Repeatedly checking whether a condition has been satisfied.

5. Presence vs Visibility?

Presence: Element exists in DOM.

Visibility: Element exists and is visible.

6. What does elementToBeClickable() check?

The element is visible and enabled so Selenium can click it.

7. Why avoid Thread.sleep()?

It introduces an unnecessary fixed delay and does not react to the
application becoming ready earlier.

8. Which wait do you prefer in automation frameworks?

Explicit waits, because they are condition-based, targeted, and work
well with dynamic applications.

9. Should implicit and explicit waits be mixed?

Generally, avoid mixing them because timeout behavior can become
difficult to predict.

Most Important Interview Takeaway

Dynamic UI
    ↓
Synchronization required
    ↓
Prefer Explicit Wait
    ↓
ExpectedConditions
    ↓
Wait for actual application state