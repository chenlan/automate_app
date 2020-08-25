package page;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class App extends BasePage {
    private static App app;

    public static App getInstance() {
        if (app == null) {
            app = new App();
            start();
        }
        return app;
    }

    private static void start() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        //desiredCapabilities.setCapability("deviceName", "emulator-5554");
        //desiredCapabilities.setCapability("app","");
        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
       // desiredCapabilities.setCapability("autoGrantPermissions", true);
        desiredCapabilities.setCapability("udid", System.getenv("UDID"));
        desiredCapabilities.setCapability("noReset",true);
        desiredCapabilities.setCapability("unicodeKeyboard", "true");
        desiredCapabilities.setCapability("resetKeyboard", "true");
       // desiredCapabilities.setCapability("chromedriverExecutable", " /cl/tmp/chromedriver/75/chromedriver");
        desiredCapabilities.setCapability("chromedriverExecutableDir", "/cl/tmp/chromedriver");



////        简单粗暴的解决方案
//        desiredCapabilities.setCapability("chromedriverExecutable", "/Users/seveniruby/projects/chromedriver/chromedrivers/chromedriver_78.0.3904.11");
////        desiredCapabilities.setCapability("chromedriverExecutable", "/Users/seveniruby/projects/chromedriver/chromedrivers/chromedriver_2.23");
//
//
//        //完善的版本选择方案，不过会优先找android webview默认实现
////        desiredCapabilities.setCapability("chromedriverExecutableDir", "/Users/seveniruby/projects/chromedriver/chromedrivers");
////        desiredCapabilities.setCapability("chromedriverChromeMappingFile", "/Users/seveniruby/projects/Java3/src/main/resources/mapping.json");
//        //打印更多chromedriver的log方便定位问题
//        desiredCapabilities.setCapability("showChromedriverLog", true);

        URL remoteUrl = null;
        try {
            remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        wait = new WebDriverWait(driver,3);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        new WebDriverWait(driver,10).until(x->{
            System.out.println("......");
           return ExpectedConditions.visibilityOfElementLocated(By.id("home_search"));
        });
//        new WebDriverWait(driver,50).until(x->{
        handleAlert();
//            String xml = driver.getPageSource();
//            Boolean find = xml.contains("home_search") || xml.contains("image_cancel");
//            System.out.println("首页："+find);
//            return find;
//        });
        System.out.println("======================");
    }

    public void end() {
        driver.close();
    }

    public SearchPage toSearch() {
        //MobileElement el1 = (MobileElement) findElement(By.id("home_search"));
        //el1.click();
        click(By.id("home_search"));
        return new SearchPage();
    }

    public StockPage toStockPage() {
        click(By.xpath("//*[@text='交易']"));
        return new StockPage();
    }

}
