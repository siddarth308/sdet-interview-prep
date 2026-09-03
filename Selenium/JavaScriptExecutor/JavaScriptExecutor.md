Selenium JavaScriptExecutor

1. What is JavaScriptExecutor?

JavascriptExecutor allows Selenium to execute JavaScript inside the browser.

Import:

import org.openqa.selenium.JavascriptExecutor;

Create the object:

JavascriptExecutor js =
        (JavascriptExecutor) driver;

Basic syntax

js.executeScript("JavaScript code");

2. executeScript()

executeScript() executes JavaScript synchronously in the current browser context.

Example:

js.executeScript("return document.title;");

It can also accept Selenium elements or other arguments:

js.executeScript(
    "arguments[0].click();",
    element
);

3. arguments[0]

arguments[0] represents the first argument passed to the JavaScript.

Example:

js.executeScript(
    "arguments[0].click();",
    element
);

Mapping:

element → arguments[0]

Multiple arguments:

js.executeScript(
    "arguments[0].click(); arguments[1].click();",
    element1,
    element2
);

Mapping:

element1 → arguments[0]
element2 → arguments[1]

4. Scroll the Page

Scroll vertically by 500 pixels:

js.executeScript(
    "window.scrollBy(0,500)"
);

Format:

window.scrollBy(horizontal, vertical)

Therefore:

0   → horizontal scroll
500 → vertical scroll

5. Scroll to an Element

js.executeScript(
    "arguments[0].scrollIntoView(true);",
    element
);

Useful when an element is outside the current viewport.

Alternative:

js.executeScript(
    "arguments[0].scrollIntoView({block: 'center'});",
    element
);

6. JavaScript Click

js.executeScript(
    "arguments[0].click();",
    element
);

Important

Do NOT use JavaScript click as the default solution.

Prefer normal Selenium:

element.click();

If normal click fails, first investigate why.

Possible reasons:

Element is not visible

Element is not interactable

Another element/overlay is covering it

Animation is in progress

Incorrect locator

Page is not ready

Element is in the wrong frame

Element state is not ready

Use appropriate waits and fix the underlying problem first.

7. Why JS Click Can Be Dangerous

Suppose an overlay is covering a button:

        Overlay
    ┌───────────────┐
    │               │
    │               │
    └───────────────┘

       Submit

A real user cannot click Submit.

Normal Selenium:

element.click();

may fail, which can reveal a genuine UI problem.

But:

js.executeScript(
    "arguments[0].click();",
    element
);

may force the interaction.

The test could pass even though the real user cannot perform the action.

SDET-2 takeaway

JavaScript click can bypass normal user interaction behavior and may hide genuine UI defects. Use it only as a deliberate workaround when there is a valid reason.

8. Get Page Title

JavaScript:

document.title

Selenium:

String title = (String) js.executeScript(
    "return document.title;"
);

System.out.println(title);

Why (String)?

executeScript() returns an Object.

If the JavaScript returns a String, cast it:

(String)

9. Get Current URL

String url = (String) js.executeScript(
    "return window.location.href;"
);

System.out.println(url);

10. Highlight an Element

Useful for debugging.

js.executeScript(
    "arguments[0].style.border='3px solid red';",
    element
);

You can also change the background:

js.executeScript(
    "arguments[0].style.backgroundColor='yellow';",
    element
);

Note

Highlighting is mainly useful for debugging/visual investigation, not normal test functionality.

11. Read an Element Property

JavaScript can be used to inspect DOM properties.

Example:

String value = (String) js.executeScript(
    "return arguments[0].value;",
    element
);

Another example:

String text = (String) js.executeScript(
    "return arguments[0].innerText;",
    element
);

Prefer Selenium methods such as getText() or getAttribute() when they provide what you need. Use JavaScript when there is a specific reason.

12. Modify an Element

JavaScript can modify DOM properties.

Example:

js.executeScript(
    "arguments[0].style.border='2px solid red';",
    element
);

Another example:

js.executeScript(
    "arguments[0].value='Selenium';",
    element
);

Important

Do not use DOM manipulation to artificially make a test pass.

The test should represent realistic application behavior whenever possible.

13. Return Values from JavaScript

Example:

Object result = js.executeScript(
    "return document.title;"
);

Or directly:

String title = (String) js.executeScript(
    "return document.title;"
);

The return type depends on what the JavaScript returns.

14. executeScript() vs executeAsyncScript()

executeScript()

Used for synchronous JavaScript.

js.executeScript(
    "return document.title;"
);

The script completes before Selenium continues.

executeAsyncScript()

Used for asynchronous JavaScript.

js.executeAsyncScript(
    "var callback = arguments[arguments.length - 1];" +
    "setTimeout(function() {" +
    "callback('Done');" +
    "}, 1000);"
);

The script uses a callback to tell Selenium that execution has completed.

Remember

executeScript()
    → synchronous

executeAsyncScript()
    → asynchronous + callback

15. When Should You Use JavaScriptExecutor?

Use it selectively when normal WebDriver APIs are not sufficient.

Good use cases:

Certain scrolling operations

Reading browser/DOM properties

DOM-related operations not conveniently exposed through WebDriver

Specific application/browser workarounds

Debugging/highlighting elements

Executing browser-side JavaScript when there is a legitimate test requirement

16. When Should You NOT Use JavaScriptExecutor?

Do not use JavaScript as a shortcut for every Selenium problem.

Bad approach:

js.executeScript(
    "arguments[0].click();",
    element
);

simply because:

element.click();

failed.

First investigate:

Correct locator?
      ↓
Element present?
      ↓
Element visible?
      ↓
Element enabled/interactable?
      ↓
Overlay blocking it?
      ↓
Animation still running?
      ↓
Correct frame?
      ↓
Correct window?
      ↓
Appropriate wait?

Then consider JavaScript only if it is genuinely appropriate.

17. Normal Selenium vs JavaScript

Task

Preferred Approach

Find element

findElement()

Click

element.click()

Type

sendKeys()

Clear

clear()

Get text

getText()

Get attribute

getAttribute()

Wait

WebDriverWait

Scroll

Selenium scroll APIs / JS when useful

DOM/browser property

JS can be useful

Force click

JS only as a deliberate workaround

Rule

Normal Selenium API
        ↓
Investigate failure
        ↓
Correct locator / wait / state
        ↓
JavaScriptExecutor if genuinely needed

18. Common JavaScriptExecutor Examples

JavascriptExecutor js =
        (JavascriptExecutor) driver;

// Scroll page
js.executeScript("window.scrollBy(0,500)");

// Scroll to element
js.executeScript(
    "arguments[0].scrollIntoView(true);",
    element
);

// Click element
js.executeScript(
    "arguments[0].click();",
    element
);

// Get page title
String title = (String) js.executeScript(
    "return document.title;"
);

// Get current URL
String url = (String) js.executeScript(
    "return window.location.href;"
);

// Highlight element
js.executeScript(
    "arguments[0].style.border='3px solid red';",
    element
);

19. Important Interview Questions

Q1. What is JavaScriptExecutor?

JavascriptExecutor is a Selenium interface that allows JavaScript to be executed in the browser.

Q2. How do you create a JavaScriptExecutor?

JavascriptExecutor js =
        (JavascriptExecutor) driver;

Q3. How do you scroll using JavaScript?

js.executeScript(
    "window.scrollBy(0,500)"
);

Q4. How do you scroll to an element?

js.executeScript(
    "arguments[0].scrollIntoView(true);",
    element
);

Q5. What does arguments[0] mean?

It represents the first argument passed from Java to the JavaScript code.

js.executeScript(
    "arguments[0].click();",
    element
);

Here:

element → arguments[0]

Q6. How do you click using JavaScript?

js.executeScript(
    "arguments[0].click();",
    element
);

But JS click should not be the default approach.

Q7. Why shouldn't JS click be your default?

Because it can bypass normal WebDriver/user interaction behavior and hide genuine UI problems such as overlays or non-interactable elements.

Q8. When would you use JavaScriptExecutor?

When normal WebDriver APIs are insufficient, for example certain scrolling or DOM/browser property operations, or as a controlled workaround for a known application issue.

Q9. Can JavaScriptExecutor replace Selenium WebDriver?

No.

JavaScriptExecutor is a supporting capability, not a replacement for WebDriver.

Q10. Where does the JavaScript execute?

It executes in the context of the current browser/page.

Q11. Difference between executeScript() and executeAsyncScript()?

executeScript()
    → synchronous

executeAsyncScript()
    → asynchronous
    → uses callback

20. SDET-2 Interview Answer

Question

When should you use JavaScriptExecutor instead of normal Selenium APIs?

Answer

I prefer normal Selenium WebDriver APIs for user interactions because they are closer to actual browser interaction and can expose genuine UI problems. I use JavaScriptExecutor selectively when normal WebDriver APIs aren't sufficient, such as for certain scrolling operations, accessing DOM/browser properties, or specific application-level workarounds. I avoid using JavaScript click as the default because it can bypass normal interaction behavior and hide issues such as overlays or non-interactable elements.

21. Quick Revision

JavascriptExecutor js =
        (JavascriptExecutor) driver;

// Scroll
js.executeScript("window.scrollBy(0,500)");

// Scroll to element
js.executeScript(
    "arguments[0].scrollIntoView(true);",
    element
);

// JS click
js.executeScript(
    "arguments[0].click();",
    element
);

// Page title
String title = (String) js.executeScript(
    "return document.title;"
);

// Current URL
String url = (String) js.executeScript(
    "return window.location.href;"
);

// Highlight
js.executeScript(
    "arguments[0].style.border='3px solid red';",
    element
);

22. One-Minute Memory Map

JavaScriptExecutor
        ↓
Execute JavaScript in browser
        ↓
executeScript()
        ↓
arguments[0] = first Java argument

Common uses:
    ↓
    Scroll
    DOM/browser properties
    Debugging
    Specific workarounds

Default interaction:
    ↓
    Selenium API

element.click()
    ↓
    FIRST CHOICE

JS click
    ↓
    Selective workaround
    NOT default

Golden Rule

Use Selenium to simulate the user's interaction. Use JavaScriptExecutor when you have a specific reason that Selenium's normal APIs are not enough.