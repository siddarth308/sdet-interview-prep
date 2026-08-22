````md
# Selenium Actions Class — Revision Notes

## 1. What is the Actions Class?

Selenium's `Actions` class is used to perform advanced mouse and keyboard interactions.

It is useful when we need to simulate user interactions such as:

- Mouse hover
- Click
- Double click
- Right click
- Drag and drop
- Keyboard shortcuts
- Click and hold
- Move and release

### Import

```java
import org.openqa.selenium.interactions.Actions;
````

### Create Actions object

```java
Actions actions = new Actions(driver);
```

---

# 2. Mouse Actions

## Mouse Hover

```java
actions.moveToElement(element).perform();
```

### Example

```java
WebElement menu = driver.findElement(By.id("products"));

Actions actions = new Actions(driver);

actions.moveToElement(menu).perform();
```

### Use case

Use `moveToElement()` when an element/menu appears after hovering over another element.

---

## Click

```java
actions.click(element).perform();
```

Example:

```java
WebElement login = driver.findElement(By.id("login"));

actions.click(login).perform();
```

---

## Double Click

```java
actions.doubleClick(element).perform();
```

Example:

```java
actions.doubleClick(element).perform();
```

---

## Right Click

Selenium calls right-click `contextClick()`.

```java
actions.contextClick(element).perform();
```

Example:

```java
WebElement file = driver.findElement(By.id("file"));

actions.contextClick(file).perform();
```

---

## Drag and Drop

```java
actions.dragAndDrop(source, target).perform();
```

Example:

```java
WebElement source = driver.findElement(By.id("source"));
WebElement target = driver.findElement(By.id("target"));

actions.dragAndDrop(source, target).perform();
```

---

# 3. Alternative Drag and Drop Approach

Some JavaScript-heavy applications may not work reliably with:

```java
actions.dragAndDrop(source, target).perform();
```

In such cases, use lower-level Actions:

```java
actions
    .clickAndHold(source)
    .moveToElement(target)
    .release()
    .perform();
```

Flow:

```text
clickAndHold()
      ↓
moveToElement()
      ↓
release()
```

---

# 4. Keyboard Actions

Import:

```java
import org.openqa.selenium.Keys;
```

## Ctrl + A

```java
actions
    .keyDown(Keys.CONTROL)
    .sendKeys("a")
    .keyUp(Keys.CONTROL)
    .perform();
```

This simulates:

```text
CTRL + A
```

---

## Ctrl + C

```java
actions
    .keyDown(Keys.CONTROL)
    .sendKeys("c")
    .keyUp(Keys.CONTROL)
    .perform();
```

---

## Ctrl + V

```java
actions
    .keyDown(Keys.CONTROL)
    .sendKeys("v")
    .keyUp(Keys.CONTROL)
    .perform();
```

---

## Enter

```java
actions.sendKeys(Keys.ENTER).perform();
```

---

## Escape

```java
actions.sendKeys(Keys.ESCAPE).perform();
```

---

# 5. Chaining Actions

One of the advantages of `Actions` is that multiple interactions can be chained together.

Example:

```java
actions
    .moveToElement(element)
    .click()
    .sendKeys("Hello")
    .perform();
```

Another example:

```java
actions
    .clickAndHold(source)
    .moveToElement(target)
    .release()
    .perform();
```

`perform()` executes the built action sequence.

---

# 6. `element.click()` vs `actions.click(element)`

## Normal click

```java
element.click();
```

Use this for a normal, straightforward element click.

Example:

```java
driver.findElement(By.id("login")).click();
```

## Actions click

```java
actions.click(element).perform();
```

Use this when the click is part of a more complex mouse interaction or when you want to chain it with other Actions.

### Interview rule

Prefer:

```java
element.click();
```

for a simple click.

Use:

```java
actions.click(element).perform();
```

when performing mouse interactions or chaining actions.

### Interview Answer

> I prefer `element.click()` for a normal element click because it is simpler and directly expresses the intent. I use `Actions.click()` when I need to simulate a mouse interaction or combine the click with other mouse or keyboard actions.

---

# 7. Why do we need `perform()`?

Most Actions methods build an action sequence.

Example:

```java
actions.moveToElement(element);
```

The interaction is not executed until:

```java
.perform();
```

Therefore:

```java
actions.moveToElement(element).perform();
```

is the common pattern.

---

# 8. Common Actions Methods

| Action       | Selenium Code                                   |
| ------------ | ----------------------------------------------- |
| Hover        | `actions.moveToElement(element).perform()`      |
| Click        | `actions.click(element).perform()`              |
| Double click | `actions.doubleClick(element).perform()`        |
| Right click  | `actions.contextClick(element).perform()`       |
| Drag & drop  | `actions.dragAndDrop(source, target).perform()` |
| Click & hold | `actions.clickAndHold(element).perform()`       |
| Release      | `actions.release().perform()`                   |
| Enter        | `actions.sendKeys(Keys.ENTER).perform()`        |
| Escape       | `actions.sendKeys(Keys.ESCAPE).perform()`       |
| Ctrl+A       | `keyDown()` + `sendKeys()` + `keyUp()`          |

---

# 9. Important Imports

Typical imports:

```java
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
```

---

# 10. Complete Actions Example

```java
WebElement menu = driver.findElement(By.id("menu"));
WebElement source = driver.findElement(By.id("source"));
WebElement target = driver.findElement(By.id("target"));

Actions actions = new Actions(driver);

// Hover
actions.moveToElement(menu).perform();

// Double click
actions.doubleClick(menu).perform();

// Right click
actions.contextClick(menu).perform();

// Drag and drop
actions.dragAndDrop(source, target).perform();

// Ctrl + A
actions
    .keyDown(Keys.CONTROL)
    .sendKeys("a")
    .keyUp(Keys.CONTROL)
    .perform();
```

---

# 11. Interview Questions & Answers

## Q1. What is the Actions class in Selenium?

### Answer

The `Actions` class is used to perform advanced mouse and keyboard interactions in Selenium.

Examples include:

* Mouse hover
* Double click
* Right click
* Drag and drop
* Keyboard shortcuts
* Click and hold

Example:

```java
Actions actions = new Actions(driver);

actions.moveToElement(element).perform();
```

---

## Q2. How do you perform mouse hover?

### Answer

Use:

```java
actions.moveToElement(element).perform();
```

Example:

```java
Actions actions = new Actions(driver);

actions.moveToElement(menu).perform();
```

---

## Q3. How do you perform a double click?

### Answer

```java
actions.doubleClick(element).perform();
```

---

## Q4. How do you perform a right click?

### Answer

Selenium calls right-click `contextClick()`.

```java
actions.contextClick(element).perform();
```

---

## Q5. How do you perform drag and drop?

### Answer

```java
actions.dragAndDrop(source, target).perform();
```

If that doesn't work reliably, use:

```java
actions
    .clickAndHold(source)
    .moveToElement(target)
    .release()
    .perform();
```

---

## Q6. How do you perform Ctrl+A using Actions?

### Answer

```java
actions
    .keyDown(Keys.CONTROL)
    .sendKeys("a")
    .keyUp(Keys.CONTROL)
    .perform();
```

---

## Q7. What is the difference between `element.click()` and `actions.click(element)`?

### Answer

`element.click()` is preferred for a normal element click.

```java
element.click();
```

`actions.click(element).perform()` is useful when the click is part of a mouse interaction sequence or needs to be chained with other Actions.

```java
actions.click(element).perform();
```

---

## Q8. Is Actions class always better than `element.click()`?

### Answer

No.

For a normal click, prefer:

```java
element.click();
```

Use Actions when you actually need advanced mouse/keyboard interactions.

---

## Q9. Why is `perform()` used?

### Answer

`perform()` executes the built Actions sequence.

Example:

```java
actions
    .moveToElement(element)
    .click()
    .perform();
```

Without executing the action sequence, the intended interaction is not performed.

---

## Q10. Can Actions perform keyboard operations?

### Answer

Yes.

For example:

```java
actions.sendKeys(Keys.ENTER).perform();
```

For combinations:

```java
actions
    .keyDown(Keys.CONTROL)
    .sendKeys("a")
    .keyUp(Keys.CONTROL)
    .perform();
```

---

## Q11. What is the difference between `keyDown()` and `keyUp()`?

### Answer

`keyDown()` presses and holds a key.

```java
actions.keyDown(Keys.CONTROL);
```

`keyUp()` releases the key.

```java
actions.keyUp(Keys.CONTROL);
```

Example:

```java
actions
    .keyDown(Keys.CONTROL)
    .sendKeys("a")
    .keyUp(Keys.CONTROL)
    .perform();
```

This simulates:

```text
Press CTRL
   ↓
Press A
   ↓
Release CTRL
```

---

## Q12. When would you use Actions instead of normal WebElement methods?

### Answer

Use Actions when the interaction requires user-like mouse or keyboard behavior, such as:

```text
Hover
Double click
Right click
Drag and drop
Click and hold
Keyboard shortcuts
Chained interactions
```

For simple operations, use normal WebElement methods.

---

# 12. SDET2 Interview Scenario

### Question

A menu opens only when you hover over it. How would you automate it?

### Answer

```java
WebElement menu = driver.findElement(By.id("menu"));

Actions actions = new Actions(driver);

actions.moveToElement(menu).perform();
```

Then locate and click the submenu item.

---

# 13. SDET2 Interview Scenario

### Question

How would you automate a drag-and-drop operation?

### Answer

First try:

```java
actions.dragAndDrop(source, target).perform();
```

If the application doesn't respond correctly, use:

```java
actions
    .clickAndHold(source)
    .moveToElement(target)
    .release()
    .perform();
```

---

# 14. SDET2 Interview Scenario

### Question

How would you select all text from an input field and replace it?

### Answer

```java
actions
    .click(input)
    .keyDown(Keys.CONTROL)
    .sendKeys("a")
    .keyUp(Keys.CONTROL)
    .sendKeys("New Text")
    .perform();
```

Alternatively, for a straightforward input field:

```java
input.clear();
input.sendKeys("New Text");
```

The second approach is usually simpler when Ctrl+A is not specifically required.

---

# 15. Quick Revision Cheat Sheet

```java
Actions actions = new Actions(driver);

// Hover
actions.moveToElement(element).perform();

// Click
actions.click(element).perform();

// Double click
actions.doubleClick(element).perform();

// Right click
actions.contextClick(element).perform();

// Drag and drop
actions.dragAndDrop(source, target).perform();

// Click and hold
actions.clickAndHold(element).perform();

// Release
actions.release().perform();

// Enter
actions.sendKeys(Keys.ENTER).perform();

// Escape
actions.sendKeys(Keys.ESCAPE).perform();

// Ctrl + A
actions
    .keyDown(Keys.CONTROL)
    .sendKeys("a")
    .keyUp(Keys.CONTROL)
    .perform();
```

---

# 16. Things to Remember for Interviews

1. `Actions` is for advanced mouse and keyboard interactions.
2. Always remember the `perform()` at the end of an Actions chain.
3. `moveToElement()` → hover.
4. `doubleClick()` → double click.
5. `contextClick()` → right click.
6. `dragAndDrop()` → drag and drop.
7. `keyDown()` → press/hold key.
8. `keyUp()` → release key.
9. Use `element.click()` for a normal click.
10. Use `Actions` when interaction needs mouse/keyboard behavior or chaining.
11. For complex drag-and-drop, `clickAndHold()` + `moveToElement()` + `release()` can be more reliable.
12. Don't use `switchTo().alert()` for HTML/CSS modals.

---

# 17. Practice Tasks

Before moving to the next Selenium topic, practice these without looking at the notes:

* [ ] Hover over a menu and click a submenu
* [ ] Perform a double click
* [ ] Perform a right click
* [ ] Drag an element to another element
* [ ] Perform Ctrl+A using Actions
* [ ] Perform Ctrl+C and Ctrl+V
* [ ] Press Enter using Actions
* [ ] Explain `element.click()` vs `actions.click()`
* [ ] Explain why `perform()` is required
* [ ] Explain when Actions should be used instead of normal WebElement methods

---

# 18. One-Minute Interview Revision

If the interviewer asks:

> "Tell me about Selenium Actions."

Answer:

> "`Actions` is a Selenium class used for advanced user interactions such as mouse hover, double click, right click, drag and drop, and keyboard combinations. I create an `Actions` object using `new Actions(driver)` and execute interactions using `perform()`. For example, I use `moveToElement()` for hover and `keyDown()`/`keyUp()` for keyboard combinations. For a simple click, I generally prefer `WebElement.click()`, while Actions is useful when the interaction involves more complex mouse or keyboard behavior."

---

# 19. Must-Know Code

```java
Actions actions = new Actions(driver);

actions.moveToElement(element).perform();

actions.click(element).perform();

actions.doubleClick(element).perform();

actions.contextClick(element).perform();

actions.dragAndDrop(source, target).perform();

actions
    .keyDown(Keys.CONTROL)
    .sendKeys("a")
    .keyUp(Keys.CONTROL)
    .perform();
```

**Core idea:**

```text
Simple interaction
       ↓
WebElement methods

Advanced mouse/keyboard interaction
       ↓
Actions class
```

:::

