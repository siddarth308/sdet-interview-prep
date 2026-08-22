````md
# Selenium Alerts — Revision Notes

## 1. What is a Selenium Alert?

A Selenium Alert is a **browser-level JavaScript dialog** that appears on a webpage.

Selenium cannot interact with the alert like a normal HTML element.

First, switch the WebDriver's focus to the alert:

```java
Alert alert = driver.switchTo().alert();
````

Import:

```java
import org.openqa.selenium.Alert;
```

---

# 2. Types of Browser Alerts

There are 3 common JavaScript dialog types:

### 1. Simple Alert

Contains a message and usually an `OK` button.

```text
Are you sure you want to continue?

[ OK ]
```

Handle with:

```java
Alert alert = driver.switchTo().alert();
alert.accept();
```

---

### 2. Confirmation Alert

Contains `OK` and `Cancel`.

```text
Are you sure you want to delete?

[ OK ] [ Cancel ]
```

Accept:

```java
alert.accept();
```

Dismiss:

```java
alert.dismiss();
```

---

### 3. Prompt Alert

Allows the user to enter text.

```text
Enter your name:

[ __________ ]

[ OK ] [ Cancel ]
```

Handle with:

```java
Alert alert = driver.switchTo().alert();

alert.sendKeys("Siddharth");
alert.accept();
```

---

# 3. Switching to an Alert

Always switch to the alert before interacting with it:

```java
Alert alert = driver.switchTo().alert();
```

Or directly:

```java
driver.switchTo().alert().accept();
```

---

# 4. Accept Alert

Clicks the `OK` button.

```java
Alert alert = driver.switchTo().alert();
alert.accept();
```

Example:

```java
driver.findElement(By.id("delete")).click();

Alert alert = driver.switchTo().alert();
alert.accept();
```

---

# 5. Dismiss Alert

Clicks the `Cancel` button on a confirmation dialog.

```java
Alert alert = driver.switchTo().alert();
alert.dismiss();
```

Example:

```java
driver.findElement(By.id("delete")).click();

Alert alert = driver.switchTo().alert();
alert.dismiss();
```

---

# 6. Get Alert Text

Use `getText()` to retrieve the message displayed in the alert.

```java
Alert alert = driver.switchTo().alert();

String message = alert.getText();

System.out.println(message);
```

Example:

```java
String message = alert.getText();

Assert.assertEquals(message, "Are you sure?");
```

---

# 7. Enter Text in a Prompt

Use `sendKeys()`.

```java
Alert alert = driver.switchTo().alert();

alert.sendKeys("Siddharth");
alert.accept();
```

Important:

`sendKeys()` is generally relevant to **prompt alerts**, not simple alerts.

---

# 8. Complete Alert Methods

The main Selenium `Alert` methods to remember:

```java
alert.accept();
alert.dismiss();
alert.getText();
alert.sendKeys("text");
```

| Method       | Purpose                 |
| ------------ | ----------------------- |
| `accept()`   | Clicks OK               |
| `dismiss()`  | Clicks Cancel           |
| `getText()`  | Gets alert message      |
| `sendKeys()` | Enters text into prompt |

---

# 9. Handling Asynchronous Alerts

Sometimes an alert does not appear immediately.

Example:

```java
driver.findElement(By.id("submit")).click();
```

The alert appears after 2–3 seconds.

Don't immediately do:

```java
driver.switchTo().alert();
```

Instead, use an explicit wait:

```java
WebDriverWait wait =
        new WebDriverWait(driver, Duration.ofSeconds(10));

wait.until(ExpectedConditions.alertIsPresent());

Alert alert = driver.switchTo().alert();

alert.accept();
```

Imports:

```java
import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
```

### Interview Answer

> I handle an asynchronously appearing alert using an explicit wait with `ExpectedConditions.alertIsPresent()`. Once the alert is present, I switch to it using `driver.switchTo().alert()` and then use `accept()`, `dismiss()`, `getText()`, or `sendKeys()` as required.

---

# 10. Why Use `alertIsPresent()`?

If you immediately execute:

```java
driver.switchTo().alert();
```

before the alert appears, Selenium can throw:

```text
NoAlertPresentException
```

Using:

```java
wait.until(ExpectedConditions.alertIsPresent());
```

makes Selenium wait until the alert is actually present.

---

# 11. Alert vs HTML Modal

This is a very important interview distinction.

## Browser Alert

Example:

```text
Are you sure?

[ OK ]
```

This is a browser-level JavaScript alert.

Handle it with:

```java
driver.switchTo().alert();
```

---

## HTML/CSS Modal

Example:

```html
<div class="modal">
    <button>Close</button>
</div>
```

This is part of the webpage DOM.

Handle it like a normal web element:

```java
driver.findElement(By.id("close")).click();
```

### Key Rule

> `driver.switchTo().alert()` is only for browser-level JavaScript alerts, not HTML/CSS modals.

---

# 12. Alert Handling Flow

Remember this simple flow:

```text
Action triggers alert
        ↓
Wait for alert if necessary
        ↓
Switch to alert
        ↓
Read / enter text
        ↓
Accept or dismiss
```

Typical code:

```java
driver.findElement(By.id("submit")).click();

WebDriverWait wait =
        new WebDriverWait(driver, Duration.ofSeconds(10));

wait.until(ExpectedConditions.alertIsPresent());

Alert alert = driver.switchTo().alert();

System.out.println(alert.getText());

alert.accept();
```

---

# 13. Common Exceptions

## NoAlertPresentException

Occurs when you try to switch to an alert that is not currently present.

Example:

```java
driver.switchTo().alert();
```

when no alert exists.

Better:

```java
wait.until(ExpectedConditions.alertIsPresent());
```

---

# 14. Important Interview Questions & Answers

## Q1. How do you handle an alert in Selenium?

### Answer

Use:

```java
Alert alert = driver.switchTo().alert();
```

Then use:

```java
alert.accept();
```

or:

```java
alert.dismiss();
```

depending on the requirement.

---

## Q2. How do you get the text from an alert?

### Answer

Use:

```java
String text = driver.switchTo().alert().getText();
```

---

## Q3. How do you enter text into a prompt alert?

### Answer

Use:

```java
Alert alert = driver.switchTo().alert();

alert.sendKeys("Siddharth");
alert.accept();
```

---

## Q4. How do you click Cancel on a confirmation alert?

### Answer

Use:

```java
driver.switchTo().alert().dismiss();
```

---

## Q5. How do you click OK on an alert?

### Answer

Use:

```java
driver.switchTo().alert().accept();
```

---

## Q6. How do you handle an alert that appears asynchronously?

### Answer

Use an explicit wait:

```java
WebDriverWait wait =
        new WebDriverWait(driver, Duration.ofSeconds(10));

wait.until(ExpectedConditions.alertIsPresent());

Alert alert = driver.switchTo().alert();

alert.accept();
```

### Interview Answer

> I use `WebDriverWait` with `ExpectedConditions.alertIsPresent()` to wait until the asynchronous alert appears, then switch to it and perform the required action.

---

## Q7. What exception occurs if an alert is not present?

### Answer

```text
NoAlertPresentException
```

It can occur when:

```java
driver.switchTo().alert();
```

is executed when no alert is available.

---

## Q8. Can Selenium inspect an alert using normal locators?

### Answer

No.

You cannot normally do:

```java
driver.findElement(By.id("alert"));
```

for a browser-level JavaScript alert.

Instead:

```java
driver.switchTo().alert();
```

---

## Q9. What is the difference between an alert and an HTML modal?

### Answer

A browser alert is a JavaScript/browser-level dialog and is handled using:

```java
driver.switchTo().alert();
```

An HTML modal is part of the webpage DOM and is handled using normal Selenium locators:

```java
driver.findElement(By.id("close")).click();
```

---

## Q10. What are the three common browser dialogs?

### Answer

1. **Alert** — usually only OK
2. **Confirmation** — OK and Cancel
3. **Prompt** — allows text input

---

## Q11. What are the main Selenium `Alert` methods?

### Answer

```java
accept()
dismiss()
getText()
sendKeys()
```

---

## Q12. Can `sendKeys()` be used on a normal alert?

### Answer

No.

`sendKeys()` is intended for alerts that contain an input field, typically a **prompt**.

Example:

```java
Alert alert = driver.switchTo().alert();

alert.sendKeys("Siddharth");
alert.accept();
```

---

# 15. SDET2 Interview Scenario

### Question

A user clicks Submit. The backend takes a few seconds and then a JavaScript alert appears saying:

```text
Report generated successfully
```

How would you automate it?

### Answer

```java
driver.findElement(By.id("submit")).click();

WebDriverWait wait =
        new WebDriverWait(driver, Duration.ofSeconds(10));

wait.until(ExpectedConditions.alertIsPresent());

Alert alert = driver.switchTo().alert();

String message = alert.getText();

Assert.assertEquals(message, "Report generated successfully");

alert.accept();
```

### Why this is good

It:

1. Performs the action.
2. Waits for the alert.
3. Switches to the alert.
4. Gets the message.
5. Validates the message.
6. Accepts the alert.

---

# 16. Quick Revision Cheat Sheet

```java
// Switch to alert
Alert alert = driver.switchTo().alert();

// Accept / OK
alert.accept();

// Cancel
alert.dismiss();

// Get message
String text = alert.getText();

// Enter text into prompt
alert.sendKeys("Siddharth");
```

### Asynchronous alert

```java
WebDriverWait wait =
        new WebDriverWait(driver, Duration.ofSeconds(10));

wait.until(ExpectedConditions.alertIsPresent());

Alert alert = driver.switchTo().alert();
alert.accept();
```

---

# 17. What to Remember for Interviews

### Must Know

```text
driver.switchTo().alert()
alert.accept()
alert.dismiss()
alert.getText()
alert.sendKeys()
ExpectedConditions.alertIsPresent()
NoAlertPresentException
```

### Must Understand

```text
Browser Alert ≠ HTML Modal
```

### Best Interview Rule

```text
Normal HTML element
        ↓
findElement()

Browser JavaScript alert
        ↓
switchTo().alert()
```

### Most Important Interview Question

> How do you handle an alert that appears asynchronously?

Answer:

```java
wait.until(ExpectedConditions.alertIsPresent());

Alert alert = driver.switchTo().alert();
alert.accept();
```

**Core takeaway:** Don't just memorize `accept()` and `dismiss()`. For SDET2 interviews, be able to explain **alert vs HTML modal, synchronous vs asynchronous alerts, explicit waiting, and `NoAlertPresentException`**.

```
```
