package com.wi.data.clear.dao;

import com.wi.data.clear.entity.CAppUser;
import com.wi.data.clear.entity.CDevice;
import com.wi.data.clear.utils.Mapper;

import java.util.List;

public interface CAppUserMapper extends Mapper<CAppUser> {
    public void saveOrUpdateAppUser(List<CAppUser> list);
}