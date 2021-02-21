package com.skytower.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

import static com.skytower.util.AccessControlAllowOrigin.checkOriginWhiteList;

@RestController
public class ImageUploadController {

    @RequestMapping(value = "/image_upload", method = RequestMethod.POST)
    public String imageUpload (
            @RequestParam(value = "file",required = false) MultipartFile file,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        JSONObject respData = new JSONObject();

        boolean fileIsEmpty = file.isEmpty();

        String statusMessage = fileIsEmpty ? "file is empty" : "success";
        String fileName = "avatar_" + new Date().getTime() + ".png";
        String filePath = "images/";

        try {

            File path = new File(ResourceUtils.getURL("file:").getPath());

            File dest = new File(path.getAbsolutePath(), filePath + fileName);

            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            file.transferTo(dest);

            respData.put("status", statusMessage);
            respData.put("url", "http://101.200.197.197:8765/skytower/files/" + filePath + fileName);
        } catch (FileNotFoundException e) {
            return e.toString();
        } catch (JSONException e) {
            return e.toString();
        } catch (IOException e) {
            System.out.println(e);
            return e.toString();
        }
        checkOriginWhiteList(request, response);
        return respData.toString();
    }
}