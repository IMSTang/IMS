package com.feng.common.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.*;

public class UploadFileUtils {



    /**
     * 保存上传的文件
     */
    public static Map saveFile(MultipartHttpServletRequest files, String filepath) {

        Map names = new HashMap();
        String path ;

        // linux 的文件是以 "/" 为开头的  filepath.startsWith("/")  这个条件去掉
        if(filepath == null || filepath == "" || filepath.startsWith("/")){
            path = System.getProperty("user.dir") + "/uploadFile";
        }else {
            path = filepath;
        }

        Iterator<String> iter = files.getFileNames();
        while (iter.hasNext()) {
            //取得一个input里面所有的上传文件
            List<MultipartFile> list = files.getFiles(iter.next());

            for (MultipartFile file : list) {
                if (file != null) {
                    //取得当前上传文件的文件名称
                    String fileName = file.getOriginalFilename();
                    if(fileName.equals("")){
                        return null;
                    }
                    String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
                    String newName= UUIDUtils.getUUID()+ "." + prefix;
                    names.put(newName,fileName);
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (fileName.trim() != null) {
                        File dest = new File(path + "/" + newName);
                        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                            dest.getParentFile().mkdir();
                        }
                        try {
                            file.transferTo(dest); //保存文件

                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
        return names;
    }
}





