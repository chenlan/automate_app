package page;

import org.openqa.selenium.By;

import java.util.HashMap;

public class SearchPage extends BasePage {

    public SearchPage() {
        initializeData();
    }

    public SearchPage search(String keyword) {
        HashMap<String, Object> paramters = new HashMap<>();
        paramters.put("keyword", keyword);
        parseSteps(paramters);
        return this;
    }


    public void cancel() {
        //click(cancelBtn);
        parseSteps(null);
    }

    public Float getCurrentPrice() {
        return Float.valueOf(parseSteps(null).get("price").toString());
    }
}
