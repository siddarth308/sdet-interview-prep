# XPath

## What is XPath?

XPath (XML Path Language) is used to navigate through elements in an HTML/XML DOM and locate elements.

In Selenium:

```java
driver.findElement(By.xpath("//input[@id='username']"));