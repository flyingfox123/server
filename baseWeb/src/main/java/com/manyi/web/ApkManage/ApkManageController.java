package com.manyi.web.ApkManage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

/**
 * Created by zhaoyuxin on 2015/6/10 0010.
 */
@Controller
@RequestMapping("/apkManage")
public class ApkManageController {
    private final static Logger logger = LoggerFactory.getLogger(ApkManageController.class);

    public final static String apkpath = "apk";
    /**
     * apk上传
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public ModelAndView apkUpload(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,String version) throws Exception {
        if(file1.isEmpty()){
            logger.error("上传APK为空");
           // return false;
        }
        if(file2.isEmpty()){
            logger.error("上传二维码为空");
            // return false;
        }
        String file1Name=file1.getOriginalFilename();
        String ext1 = file1Name.substring(file1Name.lastIndexOf(".") + 1).toLowerCase();
        if (!"apk".equalsIgnoreCase(ext1)){
           // return false;
        }
        String file2Name=file2.getOriginalFilename();
        String ext2 = file2Name.substring(file2Name.lastIndexOf(".") + 1).toLowerCase();
        if (!("jpg".equalsIgnoreCase(ext2)||"png".equalsIgnoreCase(ext2)||"gif".equalsIgnoreCase(ext2)||"bmp".equalsIgnoreCase(ext2)||"jpeg".equalsIgnoreCase(ext2))){
            // return false;
        }
        //上传文件的物理路径.../baseWeb/pic/hedPic/userId/xxx.jpg
        //String uploadPath=request.getSession().getServletContext().getRealPath(Constant.PICDIR.getValue() + "//" + userId + "//" +Constant.HEADPICDIR.getValue());
//        String uploadPath="F://"+apkpath + "//" + version;
        String uploadPath="//"+apkpath + "//" + version;

        File uploadDir=new File(uploadPath);
        if(!uploadDir.exists()){
            uploadDir.mkdirs();
        }
        File uploadFile1=new File(uploadPath + "/" + file1Name);
        file1.transferTo(uploadFile1);
        File uploadFile2=new File(uploadPath + "/" + file2Name);
        file2.transferTo(uploadFile2);
        logger.debug("version:" + version + "版本上传成功");
        return  new ModelAndView("redirect:/#/main/apkUpload");
    }

}
