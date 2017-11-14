package com.summit.common.fileUpload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@RequestMapping("filesolve")
@Controller
public class FileUploadController {

//    /**
//     * 单文件上传
//     * @param request
//     * @param file
//     * @return
//     */
//    @RequestMapping(method = RequestMethod.POST, value = "upload")
//    public String upload(MultipartHttpServletRequest request, @RequestParam("file") CommonsMultipartFile file) {
//
//        long startTime = System.currentTimeMillis();
//        System.out.println("fileName：" + file.getOriginalFilename());
//        String filename = file.getOriginalFilename();
//        String path = "E:/upload_" + filename; //上传的目标位置
//
//        File newFile = new File(path);
//
//        try {
//            //通过CommonsMultipartFile的方法直接写文件
//            file.transferTo(newFile);
//            long endTime = System.currentTimeMillis();
//            System.out.println("文件上传花费的时间：" + String.valueOf(endTime - startTime) + "ms");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "error";
//        }
//
//        return "success";
//    }

    /**
     * 多文件上传
     *
     * @param multipartRequest
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String handleImport(DefaultMultipartHttpServletRequest multipartRequest) {
        if (multipartRequest != null) {
            Iterator<String> iterator = multipartRequest.getFileNames();
            while (iterator.hasNext()) {

//                //单文件上传 。
//                MultipartFile file = multipartRequest.getFile(iterator.next());//一次传一个文件
//                if (StringUtils.hasText(file.getOriginalFilename())) {
//                    file.transferTo(new File("E:/upload_" + file.getOriginalFilename()));
//                }

                //多文件上传
                try {
                    List<MultipartFile> fileList = multipartRequest.getFiles(iterator.next()); //一次选多个文件上传
                    for (MultipartFile file : fileList) {
                        if (StringUtils.hasText(file.getOriginalFilename())) {
                            file.transferTo(new File("E:/upload_" + file.getOriginalFilename()));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return "fail";
                }
            }
        }
        return "success";
    }
}
