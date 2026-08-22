# Selenium Frames / iFrames

## 1. What is an iFrame?

An iframe is a webpage/document embedded inside another webpage.

Selenium can interact only with elements in the current browsing context.

If an element is inside an iframe, we must switch into that iframe first.

Example:

Main Page
   ↓
Frame 1
   ↓
Element


---

## 2. Switch to an iframe

### Using WebElement

Preferred when the frame can be located reliably.

```java
WebElement frame = driver.findElement(By.id("paymentFrame"));

driver.switchTo().frame(frame);