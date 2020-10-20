package com.xlibaba.ayys.service.impl;

import com.xlibaba.ayys.dao.InformationDao;
import com.xlibaba.ayys.dao.impl.InformationDaoImpl;
import com.xlibaba.ayys.entity.Information;
import com.xlibaba.ayys.entity.InformationData;
import com.xlibaba.ayys.service.InformationService;

import java.util.List;

public class InformationServiceImpl implements InformationService {
    private InformationDao informationDao = new InformationDaoImpl();

    @Override
    public InformationData getInformation() {
        InformationData data = new InformationData();
        List<Information> information = informationDao.selectInformation();
        data.setInformation(information);
        return data;
    }
}
