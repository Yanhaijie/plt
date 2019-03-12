package com.supermoney.loan.mg.service;
import com.supermoney.loan.mg.entity.CAppUser;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;


/**
 * Created by wenyuhao on 2018/06/27.
 */
public interface CAppUserService extends Service<CAppUser> {
    List<CAppUser> selectList();
}
