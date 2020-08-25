package framework;

import framework.model.PageObjectModel;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class PageManager {

    public static HashMap<String, PageObjectModel> allPageModels = new HashMap<>();

    public static void loadAllPageModel() {
        String pagePath = "src/main/java/page";
        List<File> files = DataFileManager.findFile(pagePath);
        files.stream().forEach(pageFile->{
            String pageName = pageFile.getName();
            PageObjectModel pageObjectModel = DataFileManager.readValue(pageFile,PageObjectModel.class);
            allPageModels.put(pageName,pageObjectModel);
        });

    }

    public static PageObjectModel getPageModel(String pageName){
        return allPageModels.get(pageName);
    }
}
