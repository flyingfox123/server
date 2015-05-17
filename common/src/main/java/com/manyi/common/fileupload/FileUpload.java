package com.manyi.common.fileupload;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.manyi.common.fileupload.bean.FileBean;
import com.manyi.common.fileupload.bean.FileConstBean;
import com.manyi.common.fileupload.bean.FileTypeBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

 
public class FileUpload {
     private static String uploadPath = null;// 上传文件的目录
    // private static int sizeThreshold = 1024;
     //private static int sizeMax = 4194304;

    /**
     * 上传文件
     * @param request
     * @return true 上传成功 false上传失败
     */
    public static boolean upload(HttpServletRequest request,FileConstBean fileConstBean,FileTypeBean fileTypeBean){
        boolean flag = true;
        //检查输入请求是否为multipart表单数据。
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        //若果是的话
        if(isMultipart){
            /**为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。**/
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(Integer.parseInt(fileConstBean.getValue("sizeThreshold"))); // 设置缓冲区大小，这里是4kb
                String tempPath = fileConstBean.getValue("tempPath");
                File tempPathFile = new File(tempPath);
                if (!tempPathFile.exists()) {
                    tempPathFile.mkdirs();
                }
                factory.setRepository(tempPathFile);// 设置缓冲区目录
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setHeaderEncoding("UTF-8");//解决文件乱码问题
                upload.setSizeMax(Integer.parseInt(fileConstBean.getValue("sizeMax")));// 设置最大文件尺寸
                List<FileItem> items = upload.parseRequest(request);
                // 检查是否符合上传的类型
                if(!ReadUploadFileType.checkFileType(items,fileTypeBean.getProperties())) return false;
                Iterator<FileItem> itr = items.iterator();//所有的表单项

                String uploadPath = fileConstBean.getValue("uploadPath");
                File uploadFile = new File(uploadPath);
                if (!uploadFile.exists()) {
                    uploadFile.mkdirs();
                }
                //保存文件
                while (itr.hasNext()){
                     FileItem item = (FileItem) itr.next();//循环获得每个表单项
                     if (!item.isFormField()){//如果是文件类型
                             String name = item.getName();//获得文件名 包括路径
                             if(name!=null){
                                 File fullFile=new File(item.getName());
                                 File savedFile=new File(uploadPath,fullFile.getName());
                                 item.write(savedFile);
                             }
                     }
                }
            } catch (FileUploadException e) {
                flag = false;
                e.printStackTrace();
            }catch (Exception e) {
                flag = false;
                e.printStackTrace();
            }
        }else{
            flag = false;
            System.out.println("the enctype must be multipart/form-data");
        }
        return flag;
    }

    /**
     * 上传文件
     * @param request
     * @return true 上传成功 false上传失败
     */
    public static List uploadTemp(HttpServletRequest request,FileConstBean fileConstBean,FileTypeBean fileTypeBean){
        boolean flag = true;
        //检查输入请求是否为multipart表单数据。
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        List fileList = (List)request.getSession().getAttribute("fileList");
        if (fileList==null){
            fileList=new ArrayList();
            request.getSession().setAttribute("fileList",fileList);
        }
        //若果是的话
        if(isMultipart){
            /**为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。**/
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(Integer.parseInt(fileConstBean.getValue("sizeThreshold"))); // 设置缓冲区大小，这里是4kb
                String tempPath = fileConstBean.getValue("tempPath");
                File tempPathFile = new File(tempPath);
                if (!tempPathFile.exists()) {
                    tempPathFile.mkdirs();
                }
                factory.setRepository(tempPathFile);// 设置缓冲区目录
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setHeaderEncoding("UTF-8");//解决文件乱码问题
                upload.setSizeMax(Integer.parseInt(fileConstBean.getValue("sizeMax")));// 设置最大文件尺寸
                List<FileItem> items = upload.parseRequest(request);
                // 检查是否符合上传的类型
                if(!ReadUploadFileType.checkFileType(items,fileTypeBean.getProperties())) return null;
                Iterator<FileItem> itr = items.iterator();//所有的表单项

                String uploadPath = fileConstBean.getValue("uploadPath");
                File uploadFile = new File(uploadPath);
                if (!uploadFile.exists()) {
                    uploadFile.mkdirs();
                }

                String paperType="1";
                //保存文件
                while (itr.hasNext()){
                    FileBean carrierPaper = new FileBean();
                    FileItem item = (FileItem) itr.next();//循环获得每个表单项
                    if (item.isFormField()){
                        paperType=item.getFieldName();
                    }
                    if (!item.isFormField()){//如果是文件类型
                        String name = item.getName();//获得文件名 包括路径
                        if(name!=null){

                            //fileList.add(item);
                            File fullFile=new File(item.getName());
                            File savedFile=new File(uploadPath,fullFile.getName());
                            carrierPaper.setFileUrl(uploadPath);
                            carrierPaper.setFileName(item.getName());
                            carrierPaper.setItem(item);
                            if (paperType!=null){
                                carrierPaper.setPaperType(paperType);
                            }
                        }
                        fileList.add(carrierPaper);
                    }

                }
                return fileList;

            } catch (FileUploadException e) {
                flag = false;
                e.printStackTrace();
            }catch (Exception e) {
                flag = false;
                e.printStackTrace();
            }
        }else{
            flag = false;
            System.out.println("the enctype must be multipart/form-data");
        }
        return fileList;
    }
     
    /**
     * 删除一组文件
     * @param filePath 文件路径数组
     */
    public static void deleteFile(String [] filePath){
        if(filePath!=null && filePath.length>0){
            for(String path:filePath){
                String realPath = uploadPath+path;
                File delfile = new File(realPath);
                if(delfile.exists()){
                    delfile.delete();
                }
            }
             
        }
    }
     
    /**
     * 删除单个文件
     * @param filePath 单个文件路径
     */
    public static void deleteFile(String filePath){
        if(filePath!=null && !"".equals(filePath)){
            String [] path = new String []{filePath};
            deleteFile(path);
        }
    }
     

}