package com.xlibaba.ayys.service.impl;

import com.xlibaba.ayys.dao.FilmDao;
import com.xlibaba.ayys.dao.impl.FilmDaoImpl;
import com.xlibaba.ayys.service.ProductService;
import com.xlibaba.ayys.utils.ProductData;

public class ProductServiceImpl implements ProductService {

    private final FilmDao filmDao = new FilmDaoImpl();

    /**
     * 获取所有数据
     * @return 数据集合
     */
    @Override
    public ProductData getList() {
        ProductData data = new ProductData();
        data.setList(filmDao.selectList());
        return data;
    }
}
