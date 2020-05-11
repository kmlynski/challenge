package UITests;

import UITests.screens.MainPage;
import org.testng.annotations.BeforeClass;

public class MainPageTest extends BaseUITest {

    @BeforeClass
    public void setUpMainPage(){
        MainPage mainPage = new MainPage(webDriver);
    }

}
