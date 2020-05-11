package UITests.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class MainPage {
    private WebDriver webDriver;
    private final Select searchParameterDropdown;
    private final WebElement searchInputBox;
    private final WebElement clearFilters;
    private final WebElement matchCaseCheckbox;

    public MainPage(WebDriver webDriver){
        searchInputBox = webDriver.findElement(By.id("search-input"));
        searchParameterDropdown = new Select (webDriver.findElement(By.id("search-column")));
        clearFilters = webDriver.findElement(By.id("clear-button"));
        matchCaseCheckbox = webDriver.findElement(By.id("match-case"));

        try {
            this.webDriver = webDriver;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public MainPage enterSearchText(String value){
        searchInputBox.sendKeys(value);

        return new MainPage(webDriver);
    }

    public void clickDropdownElement(String parameter)
    {
        searchParameterDropdown.selectByVisibleText(parameter);
        new MainPage(webDriver);
    }

    public void clearFilters(){
        clearFilters.click();
        new MainPage(webDriver);
    }

    public int getNumberOfCustomersDisplayed(){
        List<WebElement> customers = webDriver.findElements(By.xpath("/html/body/div/div/table/tbody/tr"));
        return customers.size();
    }

    public MainPage clickMatchCaseCheckbox(){

        matchCaseCheckbox.click();
        return new MainPage(webDriver);
    }

    public String getAnchorLinkOf(String email){
        WebElement anchor = webDriver.findElement(By.linkText(email));
        return anchor.getAttribute("href");
    }

}
