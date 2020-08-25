package framework.model;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import page.BasePage;

import java.util.HashMap;

public class PageObjectModel {
    public HashMap<String, PageObjectElement> elements;
    public HashMap<String, PageObjectMethod> methods;


    public HashMap<String, Object> runSteps(String method, HashMap<String, Object> params) {
        HashMap<String, Object> result = new HashMap<>();
        methods.get(method).steps.forEach(step -> {
            //定位元素
            By by = getBy(step);
            //元素事件
            elementAction(params, result, step, by);
        });
        return result;
    }

    private void elementAction(HashMap<String, Object> params, HashMap<String, Object> result, HashMap<String, String> step, By by) {
        if (step.containsKey("send")) {
            String sendValue = step.get("send");
            if (params != null) {
                for (String paramKey : params.keySet()) {
                    String matcher = "${" + paramKey + "}";
                    if (sendValue.contains(matcher)) {
                        sendValue = sendValue.replace(matcher, params.get(paramKey).toString());
                        break;
                    }
                }
            }
            BasePage.sendKey(by,sendValue);
        } else if (step.containsKey("get")) {
            String attributeValue = BasePage.findElement(by).getAttribute(step.get("get"));
            result.put(step.get("dump"), attributeValue);
        } else {
            BasePage.click(by);
        }
    }

    private By getBy(HashMap<String, String> step) {
        By by = null;
        if (step.containsKey("id")) {
            by = By.id(step.get("id"));
        } else if (step.containsKey("xpath")) {
            by = By.xpath(step.get("xpath"));
        } else if (step.containsKey("aid")) {
            by = MobileBy.AccessibilityId(step.get("aid"));
        } else if (step.containsKey("element")) {
            by = elements.get(step.get("element")).getBy();
        } else {
            System.out.println("cuowu-----");
        }
        return by;
    }
}
