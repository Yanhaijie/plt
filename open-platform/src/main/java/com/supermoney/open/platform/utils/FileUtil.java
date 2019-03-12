package com.supermoney.open.platform.utils;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    /**
     * 将输入流转byte[]
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readFileByFileStream(InputStream inputStream) throws IOException {
        if (inputStream == null) return null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream(inputStream.available());
        BufferedInputStream in = null;

        try {
            in = new BufferedInputStream(inputStream);
            short bufSize = 1024;
            byte[] buffer = new byte[bufSize];
            int len1;
            while (-1 != (len1 = in.read(buffer, 0, bufSize))) {
                bos.write(buffer, 0, len1);
            }

            byte[] var7 = bos.toByteArray();
            return var7;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException var14) {
                var14.printStackTrace();
            }

            bos.close();
        }
    }

    /**
     *
     * 根据文件路径获取文件流
     * @param fileUrl
     * @return
     */
    public static InputStream getInputStreamByFileUrl(String fileUrl){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        List list = new ArrayList<>();
        list.add(MediaType.valueOf("application/octet-stream"));
        headers.setAccept(list);

        //获取图片
        ResponseEntity<byte[]> response = restTemplate.exchange(fileUrl,
                HttpMethod.GET,
                new HttpEntity<byte[]>(headers),
                byte[].class);
        if (response.getStatusCode() == HttpStatus.OK){
            byte[] responseData = response.getBody();
            InputStream inputStream = new ByteArrayInputStream(responseData);
            return inputStream;
        }
        else {
            return null;
        }
    }
}
