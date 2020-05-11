package UITests;

import UITests.screens.MainPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MainPageTest extends BaseUITest {
    private MainPage mainPage;

    @BeforeClass
    public void setUpMainPage(){
        mainPage = new MainPage(webDriver);
    }

    @DataProvider
    private Object[][] searchOptionsNonCaseSensitive(){
        return new Object[][]{
                {"1", "Id"},
                {"2", "Id"},
                {"3", "Id"},
                {"alabaster","Name"},
                {"postimex","Name"},
                {"bondir", "Name"},
                {"melbourne", "City"},
                {"carthage", "City"},
                {"belfast", "City"},
                {"office@alabaster.com", "Email"},
                {"conatact@postimex.pl", "Email"},
                {"info@bond.ir", "Email"},
        };
    }
    @Test(dataProvider = "searchOptionsNonCaseSensitive" )
    public void search_engine_founds_customer_not_case_sensitive(String[] args){
        mainPage.enterSearchText(args[0])
                .clickDropdownElement(args[1]);

        Assert.assertNotEquals(mainPage.getNumberOfCustomersDisplayed(),0);

        mainPage.clearFilters();
    }

    @DataProvider
    private Object[][] searchOptionsCaseSensitive(){
        return new Object[][]{
                {"1", "Id"},
                {"2", "Id"},
                {"3", "Id"},
                {"Alabaster", "Name"},
                {"Postimex", "Name"},
                {"Bondir", "Name"},
                {"Melbourne", "City"},
                {"Carthage", "City"},
                {"Belfast", "City"},
                {"office@alabaster.com", "Email"},
                {"conatact@postimex.pl", "Email"},
                {"info@bond.ir", "Email"},
        };
    }
    @Test(dataProvider = "searchOptionsCaseSensitive" )
    public void search_engine_founds_customer_case_sensitive(String[] args){
        mainPage.clickMatchCaseCheckbox()
                .enterSearchText(args[0])
                .clickDropdownElement(args[1]);

        Assert.assertNotEquals(mainPage.getNumberOfCustomersDisplayed(),0);

        mainPage.clickMatchCaseCheckbox()
                .clearFilters();
    }

    @DataProvider
    private Object[] emails() {
        return new Object[]{
                "office@alabaster.com",
                "conatact@postimex.pl",
                "info@bond.ir",
        };
    }
    @Test(dataProvider = "emails")
    public void anchor_link_with_correct_email(String email) {
        String expected = mainPage.getAnchorLinkOf(email);

        Assert.assertEquals(expected,"mailto:" + email );
    }
}
