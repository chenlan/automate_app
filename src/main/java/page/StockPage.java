package page;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StockPage extends BasePage {

    private By addSelfBtn = By.id("subscribe");
    private By stockList = By.id("portfolio_stockName");

    public StockPage deleteAll() {
        click(By.id("com.xueqiu.android:id/edit_group"));
        click(By.id("com.xueqiu.android:id/check_all"));
        click(By.id("com.xueqiu.android:id/cancel_follow"));
        click(By.id("com.xueqiu.android:id/tv_right"));
        click(By.id("com.xueqiu.android:id/action_close"));
        return this;
    }


    public StockPage addSelect() {
        click(addSelfBtn);
        return this;
    }

    public List<String> getStockList() {
        //handleAlert();
        List<String> stocks = new ArrayList<>();
        findElements(stockList).forEach(x -> {
            stocks.add(x.getText());
        });
        return stocks;
    }





    public void webview_web() throws InterruptedException {
        driver.findElement(By.xpath("//*[@text='交易']")).click();
        for (int i = 0; i < 2; i++) {
            driver.getContextHandles().forEach(context -> System.out.println(context.toString()));
            Thread.sleep(500);
        }
        driver.context(driver.getContextHandles().toArray()[1].toString());

        driver.getWindowHandles().forEach(window -> {
            System.out.println(window);
            System.out.println(driver.getTitle());
            driver.switchTo().window(window);
            System.out.println(driver.getPageSource());
        });

//        driver.getWindowHandles().stream().filter(win->{
//            driver.switchTo().window(win);
//            return driver.getTitle().contains("实盘交易");
//        });

        Object[] array = driver.getWindowHandles().toArray();
        driver.switchTo().window(array[array.length - 1].toString());

        driver.findElement(By.cssSelector(".trade_home_info_3aI")).click();

    }


    public void testView() throws InterruptedException {
        click(By.xpath("//*[@text='港美股开户']"));
        //click(By.xpath("//*[contains(@text,'安全开户')]"));
       // new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(by));
        Thread.sleep(10000);
        for (int i = 0; i < 2; i++) {
            driver.getContextHandles().forEach(context -> System.out.println(context.toString()));
            Thread.sleep(500);
        }
        driver.context(driver.getContextHandles().toArray()[1].toString());
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        driver.getPageSource();
//        driver.getWindowHandles().forEach(window -> {
//            System.out.println(window);
//            System.out.println(driver.getTitle());
//            driver.switchTo().window(window);
//            System.out.println(driver.getPageSource());
//        });

        //WebElement element = driver.findElementByCssSelector(".open_form-submit_1Ms");


        WebElement element1 = driver.findElementByCssSelector("input[placeholder=请输入手机号]");
        //element1.sendKeys("18818692505");
        sendKey(By.cssSelector("input[placeholder=请输入手机号]"),"18818692505");
        //element.click();
        click(By.cssSelector(".open_form-submit_1Ms"));
        //滑动
        Dimension size = driver.manage().window().getSize();
        new TouchAction<>(driver)
                .press(
                        PointOption.point(size.width / 2, (size.height / 10 )* 9))
                .moveTo(PointOption.point(size.width / 2, size.height / 2))
                .release()
                .perform();
    }


}
