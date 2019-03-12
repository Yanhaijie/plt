package com.supermoney.loan.market.service;
import com.supermoney.loan.market.entity.SMerchantUserProduct;
import com.supermoney.loan.market.utils.Service;


/**
 * Created by bear on 2018/10/22.
 */
public interface SMerchantUserProductService extends Service<SMerchantUserProduct> {
    /**
     * 获取商户产品
     * @param merchantId
     * @param productCode
     * @return
     */
    public SMerchantUserProduct getMerchantProduct(String merchantId,String productCode);

}
