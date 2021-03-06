package steps;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;
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
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName(BrowserType.CHROME);
        caps.setPlatform(Platform.LINUX);
        caps.setVersion("69.0.3497.81");
        
        //Chrome driver
        //System.setProperty("webdriver.chrome.driver", "C:\\Libs\\chromedriver.exe");
        base.Driver = new RemoteWebDriver(new URL("http://localhost:3044/wd/hub"),caps);
    }


    @After
    public void TearDownTest(Scenario scenario) {
        if (scenario.isFailed()) {
            //Take screenshot logic goes here
        	try {
				Guru99TakeScreenshot.takeSnapShot(base.Driver, "test.png");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
            System.out.println(scenario.getName());
        }
        System.out.println("Closing the browser : MOCK");
        base.Driver.quit();
    }

}
