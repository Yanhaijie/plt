package com.supers.p2p.assets.service;
import com.supers.p2p.assets.utils.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by admin on 2018-02-12.
 */
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
