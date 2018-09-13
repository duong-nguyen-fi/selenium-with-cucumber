package steps;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import Base.BaseUtil;
import Guru99TakeScreenshot.Guru99TakeScreenshot;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Created by Karthik on 10/17/2016.
 */
public class Hook extends BaseUtil{


    private BaseUtil base;

    public Hook(BaseUtil base) {
        this.base = base;
    }

    @Before
    public void InitializeTest() throws MalformedURLException {

        System.out.println("Opening the browser : Chrome");
        DesiredCapabilities caps = DesiredCapabilities.firefox();
        caps.setCapability("platform", Platform.LINUX);
        /*System.setProperty("webdriver.firefox.marionette", "D:\\Libs\\geckodriver.exe");
        base.Driver = new FirefoxDriver();*/
        
        //Chrome driver
        //System.setProperty("webdriver.chrome.driver", "C:\\Libs\\chromedriver.exe");
        base.Driver = new RemoteWebDriver(new URL("http://localhost:3044/wd/hub"),caps);
    }


    @After
    public void TearDownTest(Scenario scenario) {
        if (scenario.isFailed()) {
            //Take screenshot logic goes here
        	try {
				Guru99TakeScreenshot.takeSnapShot(base.Driver, "c://test.png");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
            System.out.println(scenario.getName());
        }
        System.out.println("Closing the browser : MOCK");
    }

}
