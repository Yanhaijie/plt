package com.supermoney.loan.mg.service;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SExchangeRate;
import com.supermoney.loan.mg.entity.SMessageType;
import com.supermoney.loan.mg.entity.vo.DropVo;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/04/04.
 */
public interface SMessageTypeService extends Service<SMessageType> {

    /**
     * 查询
     * @param param
     * @return
     */
    public List<SMessageType> getList(Map<String,Object> param);
    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param);

    /**
     * 获取消息类型下拉数据
     * @return
     */
    public  List<DropVo> getDrop();

}
