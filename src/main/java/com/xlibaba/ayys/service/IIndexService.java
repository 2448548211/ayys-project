package com.xlibaba.ayys.service;

import com.xlibaba.ayys.service.entity.IndexPage;

/**
 * @author ChenWang
 * @interfaceName IIndexService
 * @date 2020/10/19 20:54
 * @since JDK 1.8
 */
public interface IIndexService {

    IndexPage getIndexPage(String username);
}
