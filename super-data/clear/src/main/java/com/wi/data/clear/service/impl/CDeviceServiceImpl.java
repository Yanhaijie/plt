package com.wi.data.clear.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.wi.data.clear.dao.*;
import com.wi.data.clear.entity.*;
import com.wi.data.clear.service.CDeviceService;
import com.wi.data.clear.utils.AbstractService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by CodeGenerator on 2018/06/22.
 */
@Service
@Transactional
public class CDeviceServiceImpl extends AbstractService<CDevice> implements CDeviceService {
    @Resource
    private CDeviceMapper cDeviceMapper;
    @Resource
    private CAppUserMapper cAppUserMapper;
    @Resource
    private CDeviceAppMapper cDeviceAppMapper;
    @Resource
    private CDeviceLocationMapper cDeviceLocationMapper;
    @Resource
    private CDeviceCallrecordsMapper cDeviceCallrecordsMapper;
    @Resource
    private CDeviceMsgMapper cDeviceMsgMapper;
    @Resource
    private CMobileMapper cMobileMapper;


    /**
     * 保存设备信息与用户设备关联信息
     * @param list
     */
    public void saveDeviceAndUserApp(List<Map<String,Object>> list){
        if(list == null || list.size() == 0){
            return;
        }
        List<CDevice> deviceList = Lists.newArrayList();
        List<CAppUser> appUserList = Lists.newArrayList();
        for(Map<String,Object> map : list){
            //设备信息
            CDevice device = new CDevice();
            device.setImei((String)map.get("imei"));
            device.setModel((String)map.get("model"));
            device.setImsi((String)map.get("imsi"));
            device.setUniqueId((String)map.get("androidID"));
            device.setManufacturer((String)map.get("manufacturer"));
            device.setSystem("android");
            if(StringUtils.isNotEmpty(device.getUniqueId())){
                deviceList.add(device);
            }
            //用户设备信息
            CAppUser appUser = new CAppUser();
            appUser.setAppName("supermoneyApp");
            appUser.setChannel((String)map.get("channel"));
            appUser.setUniqueId((String)map.get("androidID"));
            appUser.setUsername((String)map.get("username"));
            if(StringUtils.isNotEmpty(appUser.getUniqueId()) && StringUtils.isNotEmpty(appUser.getUsername())){
                appUserList.add(appUser);
            }
        }
        cDeviceMapper.saveOrUpdateDevice(deviceList);
        cAppUserMapper.saveOrUpdateAppUser(appUserList);
    }


    /**
     * 保存设备相关信息，如：地理位置，通信录，通话记录，短信，所安装的app
     * @param list
     */
    public void saveDeviceMixture(List<Map<String,Object>> list){
        if(list == null || list.size() == 0){
            return;
        }
        System.out.println("===================saveDeviceMixture start some"+new Date());
        List<CDeviceCallrecords> deviceCallrecordList = Lists.newArrayList();
        List<CDeviceApp> deviceAppList = Lists.newArrayList();
        List<CDeviceLocation> deviceLocationList = Lists.newArrayList();
        List<CDeviceMsg> deviceMsgList = Lists.newArrayList();
        List<CMobile> mobileList = Lists.newArrayList();
        for(Map<String,Object> map : list){
            String androidId = (String)map.get("androidId");
            if(StringUtils.isEmpty(androidId)){
                continue;
            }
            Map<String, Object> locationMap = JSONObject.toJavaObject((JSONObject)map.get("location"), Map.class);
            JSONArray smsArray = (JSONArray)map.get("smsList");
            JSONArray appArray = (JSONArray)map.get("installedApps");
            JSONArray callRecordsArray = (JSONArray)map.get("callRecords");
            JSONArray contactsArray = (JSONArray)map.get("contacts");
            //安装的APP
            if(appArray != null && appArray.size()>0){
                for(int i=0;i<appArray.size();i++){
                    CDeviceApp deviceApp = new CDeviceApp();
                    deviceApp.setUniqueId(androidId);
                    Map<String,Object> itemMap = (Map<String,Object>)appArray.get(i);
                    deviceApp.setLabel((String)itemMap.get("label"));
                    deviceApp.setPackagename((String)itemMap.get("packageName"));
                    if(StringUtils.isNotEmpty((String)itemMap.get("packageName"))){
                        deviceAppList.add(deviceApp);
                    }
                }
            }
            //通话记录
            if(callRecordsArray != null && callRecordsArray.size()>0){
                for(int i=0;i<callRecordsArray.size();i++){
                    CDeviceCallrecords callrecords = new CDeviceCallrecords();
                    callrecords.setUniqueId(androidId);
                    Map<String,Object> itemMap = (Map<String,Object>)callRecordsArray.get(i);
                    callrecords.setName((String)itemMap.get("name"));
                    callrecords.setTime((String)itemMap.get("time"));
                    callrecords.setNumber((String)itemMap.get("number"));
                    callrecords.setType((String)itemMap.get("type"));
                    if(StringUtils.isNotEmpty((String)itemMap.get("time")) && StringUtils.isNotEmpty((String)itemMap.get("number")) && callrecords.getNumber().length() < 60 && StringUtils.isNotEmpty((String)itemMap.get("name")) && callrecords.getName().length() < 60){
                        deviceCallrecordList.add(callrecords);
                    }
                }
            }
            //地理位置
            if(locationMap != null){
                CDeviceLocation location = new CDeviceLocation();
                location.setUniqueId(androidId);
                location.setLongitude((String)locationMap.get("longitude"));
                location.setLatitude((String)locationMap.get("latitude"));
                location.setNetworkType((String)locationMap.get("networkType"));
                if(StringUtils.isNotEmpty(location.getLatitude()) && StringUtils.isNotEmpty(location.getLongitude())){
                    deviceLocationList.add(location);
                }
            }

            //短信
            if(smsArray != null && smsArray.size()>0){
                for(int i=0;i<smsArray.size();i++){
                    CDeviceMsg msg = new CDeviceMsg();
                    msg.setUniqueId(androidId);
                    Map<String,Object> itemMap = (Map<String,Object>)smsArray.get(i);
                    msg.setNumber((String)itemMap.get("number"));
                    msg.setMessaage((String)itemMap.get("messaage"));
                    msg.setTime((String)itemMap.get("time"));
                    msg.setSeen((String)itemMap.get("seen"));
                    msg.setRead((String)itemMap.get("read"));
                    msg.setDatesent((String)itemMap.get("dateSent"));
                    msg.setPerson((String)itemMap.get("person"));
                    if(StringUtils.isNotEmpty((String)itemMap.get("number")) && msg.getNumber().length() < 60){
                        deviceMsgList.add(msg);
                    }
                }
            }
            //通讯录
            if(contactsArray != null && contactsArray.size()>0){
                for(int i=0;i<contactsArray.size();i++){
                    CMobile mobile = new CMobile();
                    Map<String,Object> itemMap = (Map<String,Object>)contactsArray.get(i);
                    mobile.setUniqueId(androidId);
                    mobile.setNumber((String)itemMap.get("number"));
                    mobile.setName((String)itemMap.get("name"));
                    if(StringUtils.isNotEmpty(mobile.getNumber()) && mobile.getNumber().length() < 60 && StringUtils.isNotEmpty((String)itemMap.get("name")) && mobile.getName().length() < 60){
                        mobileList.add(mobile);
                    }

                }
            }
            if(deviceAppList.size() > 0){
                cDeviceAppMapper.saveOrUpdateDeviceApp(deviceAppList);
            }
            if(deviceLocationList.size() > 0){
                cDeviceLocationMapper.saveOrUpdateDeviceLocation(deviceLocationList);
            }
            if(deviceCallrecordList.size() > 0){
                cDeviceCallrecordsMapper.saveOrUpdateCallrecords(deviceCallrecordList);
            }
            if(deviceMsgList.size() > 0){
                cDeviceMsgMapper.saveOrUpdateDeviceMsg(deviceMsgList);
            }
            if(mobileList.size() > 0){
                cMobileMapper.saveOrUpdateMobile(mobileList);
            }
            System.out.println("===================saveDeviceMixture finish some"+new Date());
        }

    }

}
