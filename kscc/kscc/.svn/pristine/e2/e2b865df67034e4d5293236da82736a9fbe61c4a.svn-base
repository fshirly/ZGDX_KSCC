package com.fable.kscc.api.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by jk on 2017/8/9.
 * 表单提交文件保存
 */
public class FileUtil {

    /**
     * @param file form表单获取的file
     * @return 返回文件名  用于保存到数据库中
     */
    public static Map<String, Object> upload(MultipartFile file) {

        Map<String, Object> map = new HashMap<>();
        String fileName = "";
        String fileUrl = "";

        if (file.isEmpty()) {
            map.put("fileName", fileName);
            map.put("fileUrl", fileUrl);
            return map;
        }


        //String path = request.getSession().getServletContext().getRealPath("upload");
        String url = System.getProperty("user.dir");
        String path = url.substring(0, url.lastIndexOf(File.separator)) + File.separator + "user" + File.separator + "uploadFile";
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        String uuid = UUID.randomUUID().toString();
        fileName = file.getOriginalFilename();
        fileUrl = uuid ;

        File tempFile = new File(path, fileUrl);

        try {
            file.transferTo(tempFile);
            map.put("fileName", fileName);
            map.put("fileUrl", fileUrl);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("fileName", fileName);
            map.put("fileUrl", fileUrl);
            return map;
        }
    }

    // 创建临时文件
    public static File createSampleFile(String content)
            throws IOException
    {
        File file = File.createTempFile("oos-java-sdk-" + System.currentTimeMillis(), ".txt");
        file.deleteOnExit();
        Writer writer = null;
        try
        {
            writer = new OutputStreamWriter(new FileOutputStream(file));
            writer.write(content + System.currentTimeMillis());
        }
        finally
        {
            if (writer != null)
                writer.close();
        }
        return file;
    }

    public static void downloadInputStream(InputStream input, String fileName)
            throws IOException
    {
        int c;
        File file = new File(fileName);
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(file);
            while ((c = input.read()) != -1)
            {
                fos.write(c);
            }
            fos.flush();
        }
        finally
        {
            if (fos != null)
                fos.close();
        }
    }
}
