package com.supermoney.open.platform.service;

import com.supermoney.open.platform.utils.Result;
import org.springframework.web.multipart.MultipartFile;

public interface FileServer {
    /**
     * 上传文件
     * @param file
     * @param type
     * @return
     */
    public Result fileUpdate(MultipartFile file, String type);
    /**
     * 上传base64img
     * @param baseStr
     * @return
     */
    public Result imgUpdate(String baseStr);
}
