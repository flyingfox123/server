package com.manyi.business.carriersign.support;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class UploadImage_Test {
//    public static void main(String args[]) throws Exception {
//
//        MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE,"----------ThIs_Is_tHe_bouNdaRY_$", Charset.defaultCharset());
//        multipartEntity.addPart("phone",new StringBody("136********", Charset.forName("UTF-8")));
//        multipartEntity.addPart("receiver",new StringBody("138***********",Charset.forName("UTF-8")));
//        //multipartEntity.addPart("image",new FileBody(new File("d:/"+"pic/1591985_120_634725266937317500.jpg"),"image/png"));
//        multipartEntity.addPart("image",new FileBody(new File("d:/"+"pic/1591985_120_634725266937317500.jpg")));
//
//        HttpPost request = new HttpPost("http://127.0.0.1:8088/business/carrierqulification/addpic");
//        request.setEntity(multipartEntity);
//        request.addHeader("Content-Type","multipart/form-data; boundary=----------ThIs_Is_tHe_bouNdaRY_$");
//
//        DefaultHttpClient httpClient = new DefaultHttpClient();
//        HttpResponse response =httpClient.execute(request);
//
//        InputStream is = response.getEntity().getContent();
//        BufferedReader in = new BufferedReader(new InputStreamReader(is));
//        StringBuffer buffer = new StringBuffer();
//        String line = "";
//        while ((line = in.readLine()) != null) {
//            buffer.append(line);
//        }
//
//        System.out.println("发送消息收到的返回："+buffer.toString());
//    }
}