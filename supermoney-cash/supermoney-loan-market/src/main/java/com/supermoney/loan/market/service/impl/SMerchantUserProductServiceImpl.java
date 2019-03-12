package com.supermoney.loan.market.service.impl;

import com.supermoney.loan.market.dao.SMerchantUserProductMapper;
import com.supermoney.loan.market.entity.SMerchantUserProduct;
import com.supermoney.loan.market.service.SMerchantUserProductService;
import com.supermoney.loan.market.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by bear on 2018/10/22.
 */
@Service
@Transactional
public class SMerchantUserProductServiceImpl extends AbstractService<SMerchantUserProduct> implements SMerchantUserProductService {
    @Resource
    private SMerchantUserProductMapper sMerchantUserProductMapper;

    /**
     * 获取商户产品
     * @param merchantId
     * @param productCode
     * @return
     */
    @Override
    public SMerchantUserProduct getMerchantProduct(String merchantId,String productCode){
        SMerchantUserProduct param=new SMerchantUserProduct();
        param.setMerchantId(merchantId);
        param.setProductCode(productCode);
        return  sMerchantUserProductMapper.selectOne(param);
    }

}
