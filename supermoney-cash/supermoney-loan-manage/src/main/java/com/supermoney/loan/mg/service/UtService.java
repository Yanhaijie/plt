package com.supermoney.loan.mg.service;

import com.supermoney.loan.mg.utils.Result;
import org.springframework.web.multipart.MultipartFile;

public interface UtService {
    /**
     * 导入文件处理
     * @param file
     * @return
     */
    public Result importNumbers(MultipartFile file);
    /**
     * 推送营销
     * @param numbers
     * @param content
     * @return
     */
    public  Result pushMsg(String[] numbers,String content);
}
