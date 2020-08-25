package framework.model;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.List;

public class PageObjectElement {
    public List<HashMap<String, String>> element;

    public By getBy() {
        HashMap<String, String> findElementVersion = findValue();
        if (findElementVersion != null) {
            return getBy(findElementVersion);
        }
        System.out.println("元素定位数据格式错误！");
        return null;
    }

    private HashMap<String, String> findValue() {
        float curVersion = 0;
        float tapVersion = 0;
        HashMap<String, String> findMap = null;
        for (HashMap<String, String> map : element) {
            if (map.containsKey("version")) {
                float version = Float.valueOf(map.get("version"));
                if (curVersion == version) {
                    return map;
                }
                if (curVersion > version && version > tapVersion) {
                    tapVersion = version;
                    findMap = map;
                }
            } else {
                return map;
            }
        }
        return findMap;
    }

    private By getBy(HashMap<String, String> map) {
        if (map.containsKey("id")) {
            return By.id(map.get("id"));
        } else if (map.containsKey("xpath")) {
            return By.xpath(map.get("xpath"));
        } else if (map.containsKey("aid")) {
            return MobileBy.AccessibilityId(map.get("aid"));
        } else {
            return null;
        }
    }
}
