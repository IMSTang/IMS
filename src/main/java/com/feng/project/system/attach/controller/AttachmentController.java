package com.feng.project.system.attach.controller;

import java.io.*;
import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.feng.framework.aspectj.lang.annotation.Log;
import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.domain.JSON;
import com.feng.project.system.attach.domain.Attachment;
import com.feng.project.system.attach.service.IAttachmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统访问记录
 * 
 * @author feng
 */
@Controller
@RequestMapping("/system/attach")
public class AttachmentController extends BaseController
{
    private String prefix = "system/attach";

    @Autowired
    private IAttachmentService attachmentService;

	
	@GetMapping("/search/{mainType}/{mainSn}")
    @ResponseBody
    public List<Attachment> selectAttachmentList(@PathVariable("mainType") String mainType, @PathVariable("mainSn") int mainSn)
    {
        List<Attachment> aList = attachmentService.selectAttachmentList(mainType, mainSn);
        return aList;
    }

//    @RequiresPermissions("system:attach:remove")
    @Log(title = "System", action = "Attachment - remove")
    @GetMapping("/remove/{attachmentId}")
    @ResponseBody
    public JSON deleteAttachmentById(@RequestParam("attachmentId") int attachmentId)
    {
        int rows = attachmentService.deleteAttachmentById(attachmentId);
        if (rows > 0)
        {
            return JSON.ok();
        }else{
			return JSON.error(1, "Delete ERROR");
		}
    }

    /*
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    ResponseEntity<InputStreamResource> downloadFile(@PathVariable("attachmentId") int attachmentId)
            throws IOException {

        Attachment att1 = attachmentService.selectAttachmentById(attachmentId);

        String fileOriginalName = att1.getAttachmentName();// 设置文件名
        String fileUuidName = att1.getAttachmentUuid();// 设置文件名


        String filePath = System.getProperty("user.dir") + "/uploadFile" + fileUuidName;
        FileSystemResource file = new FileSystemResource(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }

*/
    /*
    @Log(title = "System", action = "Attachment - download")
    @RequestMapping(value="/download/{attachmentId}", method=RequestMethod.GET)
    public void downloadFile(@PathVariable("attachmentId") int attachmentId, HttpServletRequest request, HttpServletResponse response) {

        Attachment att1 = attachmentService.selectAttachmentById(attachmentId);

        String fileOriginalName = att1.getAttachmentName();// 设置文件名
        String fileUuidName = att1.getAttachmentUuid();// 设置文件名

        if (fileUuidName != null) {
            //设置文件路径
            //String realPath = "c://Temp//";
            String realPath = System.getProperty("user.dir") + "/uploadFile";
            File file = new File(realPath , fileUuidName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", fileOriginalName));
                        //"attachment;fileName=\"" + fileOriginalName +"\"");// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    response.flushBuffer();
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }*/

    //response 已经到了前台页面。 为什么不提示保存文件？？
    @RequestMapping("/download/{attachmentId}")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "4d4371f9a2a34611842c437cd38c3294.txt";// 设置文件名，根据业务需要替换成要下载的文件名
        if (fileName != null) {
            //设置文件路径
            String realPath = System.getProperty("user.dir") + "/uploadFile";
            File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
//        return "success";
    }

}
