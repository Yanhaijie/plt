package com.supermoney.loan.api.service;
import com.supermoney.loan.api.entity.SBussLabel;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.Service;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by xionghuifeng on 2018/01/20.
 */
public interface SBussLabelService extends Service<SBussLabel> {

    public Result saveLable(String pwd ,  String labelName,  String lableVal);

    public  Result getByName(String lableName);
    /**
     * 获取Banner数据
     * @return
     */
    public  Result getByBanner();

    /**
     * 获取业务标签
     * @param labelName
     * @return
     */
    public  Result getByNameNotSplit(String labelName);
    /**
     *
     * @param labelName
     * @return
     */
    public  Result getByLabelToJson(String labelName);
    /**
     * 获取数据
     * @param labelName
     * @return
     */
    public  SBussLabel getByLabelName(String labelName);

    /**
     * app版本信息
     * @param packageName
     * @return
     */
    public  Result appVersion(String packageName);
    /**
     * APP模块功能开关
     * @return
     */
    public  Result appModules();

}
