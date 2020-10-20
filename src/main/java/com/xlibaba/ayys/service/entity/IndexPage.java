package com.xlibaba.ayys.service.entity;

import java.util.Map;

/**
 * @author ChenWang
 * @className IndexPage
 * @date 2020/10/19 21:32
 * @since JDK 1.8
 */
public class IndexPage {
    private Integer loginCount;
    private Map<String,IndexStatistic> map;
    private IndexServer is;
    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }
    public void setMap(Map<String, IndexStatistic> map) {
        this.map = map;
    }

    public void setIs(IndexServer is) {
        this.is = is;
    }
}
