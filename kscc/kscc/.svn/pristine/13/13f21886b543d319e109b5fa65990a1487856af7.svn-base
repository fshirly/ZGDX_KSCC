package com.fable.kscc.bussiness.controller.fileupload;

import com.fable.kscc.api.exception.BusinessException;
import com.fable.kscc.api.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by jk on 2017/8/9.
 * 文件上传表单提交
 * 根据文件名下载
 */
@Controller
@RequestMapping("/fileUpload")
public class FileUpload {

    private final Logger logger = LoggerFactory.getLogger(FileUpload.class);

    //test测试
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void fileUpload(@RequestParam(value = "fileUpload") MultipartFile fileUpload, HttpServletRequest request) {
        Map<String,Object> map = FileUtil.upload(fileUpload);
        System.out.println(map.get("fileName"));

    }

    /**
     * 文件下载
     *
     * @param
     */
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public void fileDown(HttpServletResponse response, HttpServletRequest req) throws UnsupportedEncodingException {
        String fileName = req.getParameter("fileName");
        String fileUrl = req.getParameter("fileUrl");
        if (StringUtils.isEmpty(fileUrl)) {
            throw new BusinessException("下载文件名不能为空");
        }
            String url = System.getProperty("user.dir");
            String path = url.substring(0, url.lastIndexOf(File.separator)) + File.separator + "user" + File.separator + "uploadFile";
            File file = new File(path, fileUrl);

            if (!file.exists()) {
                throw new BusinessException("上传文件丢失");
            }
            response.setCharacterEncoding("UTF-8");
            if(req.getHeader("User-Agent").toUpperCase().indexOf("MSIE")>0){
                try {
                    fileName = URLEncoder.encode(fileName, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            fileName=fileName.replaceAll("\\+","%20");    //处理空格转为加号的问题
            response.setHeader("Content-Disposition", "attachment;fileName=\"" + fileName +"\"");

            OutputStream os = null;
            FileInputStream fis = null;
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                byte[] b = new byte[100];
                int c;
                while((c=fis.read(b))>0){
                    os.write(b,0,c);
                }
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                if(null!=fis){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(null!=os){
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
    }

    /**
     * s删除文件
     *
     * @param fileUrl 文件路径
     * @return 是否删除成功
     */
    @RequestMapping(value = "/deleteFile", method = RequestMethod.GET)
    public boolean deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            throw new BusinessException("删除文件路径不能为空");
        }

        String name = fileUrl.substring(0, fileUrl.indexOf("."));
        String url = System.getProperty("user.dir");
        String path = url.substring(0, url.lastIndexOf(File.separator)) + File.separator + "user" + File.separator + "uploadFile";
        File file = new File(path, name);
        if (!file.exists()) {
            throw new BusinessException("删除文件不存在");
        }
        try {
            return file.delete();
        } catch (Exception e) {
            logger.error("删除文件异常：{}" , e);
        }
        return false;
    }


    public static void main(String[] args) {
        String property = System.getProperty("user.dir");
        System.out.println(property);
    }


}
