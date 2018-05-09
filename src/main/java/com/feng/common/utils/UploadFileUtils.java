package com.feng.common.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class UploadFileUtils {

    /**
     * 保存上传的文件
     */
    public static boolean saveFile(MultipartHttpServletRequest files) {

        String path = System.getProperty("user.dir") + "/uploadFile";
        Iterator<String> iter = files.getFileNames();
        while (iter.hasNext()) {
            //取得一个input里面所有的上传文件
            List<MultipartFile> list = files.getFiles(iter.next());

            for (MultipartFile file : list) {
                if (file != null) {
                    //取得当前上传文件的文件名称
                    String fileName = file.getOriginalFilename();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (fileName.trim() != null) {
                        File dest = new File(path + "/" + fileName);
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
        return false;
    }
}





