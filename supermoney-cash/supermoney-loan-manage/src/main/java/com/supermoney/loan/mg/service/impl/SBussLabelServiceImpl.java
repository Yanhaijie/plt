package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SBussLabelMapper;
import com.supermoney.loan.mg.entity.SBussLabel;
import com.supermoney.loan.mg.entity.vo.BussBannerVo;
import com.supermoney.loan.mg.service.SBussLabelService;
import com.supermoney.loan.mg.utils.AbstractService;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/02/11.
 */
@Service
@Transactional
public class SBussLabelServiceImpl extends AbstractService<SBussLabel> implements SBussLabelService {
    @Resource
    private SBussLabelMapper sBussLabelMapper;

    /**
     * 保存
     * @param sBussLabel
     * @return
     */
    public Result saveBussLable(SBussLabel sBussLabel){

        if(sBussLabel.getBussName().isEmpty()){
            return  ResultGenerator.genFailResult("标签名称不能为空!");
        }

        if(sBussLabel.getId() != null && sBussLabel.getId().compareTo(0)>0){
            //edit
            sBussLabelMapper.updateByPrimaryKeySelective(sBussLabel);
        }else {
            //add
            if(getByName(sBussLabel.getBussName())!=null){
                return ResultGenerator.genFailResult("标签已存在!");
            }
            sBussLabelMapper.insert(sBussLabel);

        }

        return ResultGenerator.genSuccessResult("保存成功!");
    }

    /**
     * 根据名称获取
     * @param name
     * @return
     */
    public SBussLabel getByName(String name){
         SBussLabel label=new SBussLabel();
         label.setBussName(name);
        return sBussLabelMapper.selectOne(label);
    }
    /**
     * 获取数据
     * @param labelName
     * @return
     */
    public  SBussLabel getByLabelName(String labelName){
        SBussLabel bussLabel=new SBussLabel();
        bussLabel.setBussName(labelName);
        return  sBussLabelMapper.selectOne(bussLabel);
    }

    /**
     * 获取banner
     * @return
     */
    public  Result getBanner(){
        SBussLabel label=getByName("banner");
        List<BussBannerVo> list=new ArrayList<>();
        if(label==null){
            for (int i=1; i<=3;i++){
                BussBannerVo banner=new BussBannerVo();
                banner.setId(Integer.valueOf(i));
                list.add(banner);
            }
        }else {
            String[] ary=label.getBussVal().split(",");
            List<Map> result = new ArrayList<Map>();
            for(int i = 0;i<ary.length;i++){
                String item = ary[i].replace("|",",");
                String[] jry=item.split(",");
                Map<String,Object> banner=new HashMap<>();
                if(jry.length>4){
                    banner.put("id",i+1);
                    banner.put("img","null".equals(jry[0])?"":jry[0]);
                    banner.put("type","null".equals(jry[1])?"":jry[1]);
                    banner.put("content","null".equals(jry[2])?"":jry[2]);
                    banner.put("title","null".equals(jry[3])?"":jry[3]);
                    banner.put("needLogin","null".equals(jry[4])?"":jry[4]);
                    result.add(banner);
                }
            }
            return  ResultGenerator.genSuccessResult(result);
        }
        return  ResultGenerator.genSuccessResult(list);
    }


}
