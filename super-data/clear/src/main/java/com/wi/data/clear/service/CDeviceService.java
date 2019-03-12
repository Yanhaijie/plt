package com.wi.data.clear.service;
import com.wi.data.clear.entity.CDevice;
import com.wi.data.clear.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/06/22.
 */
public interface CDeviceService extends Service<CDevice> {

    /**
     * 保存设备信息与用户设备关联信息
     * @param list
     */
    public void saveDeviceAndUserApp(List<Map<String,Object>> list);

    /**
     * 保存设备相关信息，如：地理位置，通信录，通话记录，短信，所安装的app
     * @param list
     */
    public void saveDeviceMixture(List<Map<String,Object>> list);

}
