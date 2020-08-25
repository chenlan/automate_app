package page;

import framework.DataFileManager;
import framework.PageManager;
import framework.model.PageObjectModel;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;

public class BasePage {
    public static AndroidDriver driver;
    public static WebDriverWait wait;
    private PageObjectModel model;

    /**
     * 设置PageObjce数据
     */
    public void initializeData() {
        String pageName =  Thread.currentThread().getStackTrace()[2].getFileName();
        model = PageManager.getPageModel(pageName);
    }

    /**
     * 执行功能步骤
     *
     * @param params 参数
     * @return
     */
    public HashMap<String, Object> parseSteps(HashMap<String, Object> params) {
        String method = Thread.currentThread().getStackTrace()[2].getMethodName();
        return model.runSteps(method, params);
    }

    public HashMap<String, Object> parseSteps() {
        String method = Thread.currentThread().getStackTrace()[2].getMethodName();
        return model.runSteps(method, null);
    }

    /**
     * 根据by 查找定位元素
     *
     * @param by
     * @return
     */
    public static WebElement findElement(By by) {
        System.out.println("查找："+by);
        return findElement(by,0);
    }

    private static WebElement findElement(By by,int dp) {
        if(dp<3){
            try {
                return driver.findElement(by);
            } catch (Exception ex) {
                dp=dp+1;
                handleAlert();
                return findElement(by,dp);
            }
        }
        System.out.println("查询超出最大次数！");
        return null;
    }

    /**
     * 根据by 查找符合条件的所有元素
     *
     * @param by
     * @return
     */
    public static List<WebElement> findElements(By by) {
        return findElements(by,0);
    }

    private static List<WebElement> findElements(By by,int dp) {
        if(dp<3){
            try{
                return driver.findElements(by);
            } catch (Exception ex){
                dp=dp+1;
                handleAlert();
                return findElements(by,dp);
            }
        }
        System.out.println("超过最大查询次数。");
        return null;
    }

    /**
     * 点击元素
     *
     * @param by
     */
    public static void click(By by) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
        }catch (Exception ex){
            System.out.println("找元素超时了，可能有弹框。");
        }finally {
            findElement(by).click();
        }



    }

    public static void sendKey(By by,String content){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }catch (Exception ex){
            System.out.println("找元素超时了，可能有弹框。");
        }
        finally {
            findElement(by).sendKeys(content);
        }


    }

    public static void handleAlert() {
        HashMap<String,By> alertBoxt = new HashMap<String,By>();//todo 初始化配置文件读取黑名单
        alertBoxt.put("tv_agree",By.id("tv_agree"));

        String xml = driver.getPageSource();
        alertBoxt.keySet().forEach(alert->{
            if(xml.contains(alert)){
                driver.findElement(alertBoxt.get(alert)).click();
            }
        });
//        //todo 黑名单列表
//        List<By> alertlist = new ArrayList<By>();
//        alertlist.add(By.id("tv_agree"));
//
//        alertlist.forEach(alertBy -> {
//            List<WebElement> elements = driver.findElements(alertBy);
//            if (elements.size() >= 1) {
//                elements.get(0).click();
//            }
//        });
    }


}
