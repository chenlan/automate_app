package framework;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataFileManager {

    public static <T> T readValue(String resourcePath, Class<T> t) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        T data = null;
        try {
            data = objectMapper.readValue(DataFileManager.class.getResourceAsStream(resourcePath), t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static <T> T readValue(File file, Class<T> t) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        T data = null;
        try {
            data = objectMapper.readValue(file, t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static List<File> findFile(String projectPath) {
        return  findFile(projectPath,null);
    }

    public static List<File> findFile(String projectPath, String endsWith) {
        List<File> fileList = new ArrayList<>();
        int fileNum = 0, folderNum = 0;
        File file = new File(projectPath);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    list.add(file2);
                    folderNum++;
                } else {
                    if(endsWith!=null && file2.getName().endsWith(endsWith)){
                        fileList.add(file2);
                        fileNum++;
                    }
                }
            }
            File temp_file;
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        list.add(file2);
                        folderNum++;
                    } else {
                        if(endsWith!=null && file2.getName().endsWith(endsWith)){
                            fileList.add(file2);
                            fileNum++;
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("文件夹共有:" + folderNum + ",文件共有:" + fileNum);
        return fileList;
    }

}
