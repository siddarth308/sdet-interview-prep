Selenium Dropdowns

1. Native HTML Dropdown

Selenium's Select class is used only for genuine HTML <select> elements.

Example:

<select id="country">
    <option value="IN">India</option>
    <option value="US">USA</option>
    <option value="FR">France</option>
</select>

Import:

import org.openqa.selenium.support.ui.Select;

Create Select object:

WebElement dropdown = driver.findElement(By.id("country"));

Select select = new Select(dropdown);

2. Selecting Options

selectByVisibleText()

Select based on the text visible to the user.

select.selectByVisibleText("India");

HTML:

<option value="IN">India</option>

Here:

Visible text = India

Value = IN

selectByValue()

Select based on the value attribute.

select.selectByValue("IN");

HTML:

<option value="IN">India</option>

selectByIndex()

Select based on the option's index.

select.selectByIndex(1);

Example:

Index 0 → India
Index 1 → USA
Index 2 → France

Important

Index-based selection can be fragile because adding, removing, or reordering options can change the index.

Prefer:

select.selectByVisibleText("India");

or:

select.selectByValue("IN");

when possible.

3. Get All Options

List<WebElement> options = select.getOptions();

for (WebElement option : options) {
    System.out.println(option.getText());
}

getOptions() returns all <option> elements.

4. Get Selected Options

getFirstSelectedOption()

Returns the first selected option.

WebElement selected =
        select.getFirstSelectedOption();

System.out.println(selected.getText());

Useful for checking the current selection.

getAllSelectedOptions()

Returns all selected options.

List<WebElement> selectedOptions =
        select.getAllSelectedOptions();

for (WebElement option : selectedOptions) {
    System.out.println(option.getText());
}

Especially useful for multi-select dropdowns.

5. Check for Multi-Select

boolean multiple = select.isMultiple();

For:

<select multiple>

returns:

true

For a normal:

<select>

returns:

false

6. Deselect Options

These methods are relevant for multi-select dropdowns.

Deselect by visible text

select.deselectByVisibleText("India");

Deselect by value

select.deselectByValue("IN");

Deselect by index

select.deselectByIndex(1);

Deselect all

select.deselectAll();

7. Native vs Custom Dropdown

Select works only with genuine HTML:

<select>

It does NOT work with custom dropdowns implemented using:

<div>
<ul>
<li>

or framework-specific components.

For custom dropdowns, interact with elements normally using Selenium.

Example:

driver.findElement(By.id("dropdown")).click();

driver.findElement(
    By.xpath("//li[text()='India']")
).click();

Use appropriate explicit waits when necessary:

WebDriverWait wait =
        new WebDriverWait(driver, Duration.ofSeconds(10));

wait.until(
    ExpectedConditions.elementToBeClickable(
        By.id("dropdown")
    )
).click();

wait.until(
    ExpectedConditions.elementToBeClickable(
        By.xpath("//li[text()='India']")
    )
).click();

8. Native vs Custom Dropdown

Dropdown Type

Selenium Approach

<select>

Select class

<select multiple>

Select class

<div> dropdown

Normal Selenium

<ul><li> dropdown

Normal Selenium

React/custom dropdown

Usually normal Selenium

Angular/custom dropdown

Usually normal Selenium

Material UI/custom dropdown

Usually normal Selenium

Autocomplete

Type + locate/select suggestion

9. Complete Example

WebElement countryDropdown =
        driver.findElement(By.id("country"));

Select select = new Select(countryDropdown);

// Select option
select.selectByVisibleText("India");

// Select by value
select.selectByValue("IN");

// Select by index
select.selectByIndex(1);

// Get all options
List<WebElement> options = select.getOptions();

for (WebElement option : options) {
    System.out.println(option.getText());
}

// Get current selection
System.out.println(
    select.getFirstSelectedOption().getText()
);

// Check if multiple
System.out.println(select.isMultiple());

10. Important Exceptions

UnexpectedTagNameException

If you try to use Select on something other than <select>:

Select select = new Select(divElement);

Selenium can throw:

UnexpectedTagNameException

Reason:

Select expects the element to be a <select> tag.

11. Interview Questions

Q1. Can Selenium's Select class handle every dropdown?

No.

Select works only with genuine HTML <select> elements.

It cannot directly handle custom dropdowns built using div, li, etc.

Q2. Difference between selectByVisibleText() and selectByValue()?

select.selectByVisibleText("India");

uses the text displayed to the user.

select.selectByValue("IN");

uses the HTML value attribute.

Example:

<option value="IN">India</option>

Q3. Why can selectByIndex() be fragile?

Because indexes can change when developers add, remove, or reorder options.

Q4. How do you get all dropdown options?

List<WebElement> options = select.getOptions();

Q5. How do you check whether a dropdown supports multiple selections?

select.isMultiple();

Q6. How do you get the currently selected option?

select.getFirstSelectedOption();

Q7. How do you handle a custom dropdown?

First click/open the dropdown, then locate and select the desired option using normal Selenium locators and appropriate waits.

12. Quick Revision

Select select = new Select(element);

// Select
select.selectByVisibleText("India");
select.selectByValue("IN");
select.selectByIndex(1);

// Read options
select.getOptions();
select.getAllSelectedOptions();
select.getFirstSelectedOption();

// Check multiple
select.isMultiple();

// Deselect (multi-select)
select.deselectByVisibleText("India");
select.deselectByValue("IN");
select.deselectByIndex(1);
select.deselectAll();

Remember

<select>              → Select class

<div>/<ul>/<li>       → Normal Selenium interactions

selectByVisibleText() → Visible option text

selectByValue()       → HTML value attribute

selectByIndex()       → Position/index; can be fragile

isMultiple()           → Check multi-select

getOptions()           → All options

getFirstSelectedOption()
                       → Current/first selected option

getAllSelectedOptions()
                       → All selected options

SDET-2 Rule

First identify whether the dropdown is a native HTML <select> or a custom component.

Native <select> → Select class

Custom dropdown → Normal Selenium + appropriate waits