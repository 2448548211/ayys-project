package com.xlibaba.ayys.service.entity;

/**
 * @author ChenWang
 * @className IndexStatistic
 * @date 2020/10/20 11:23
 * @since JDK 1.8
 */
public class IndexStatistic {
    private Integer infoCount;
    private Integer imgCount;
    private Integer filmCount;
    private Integer userCount;
    private Integer adminCount;


    public void setInfoCount(Integer infoCount) {
        this.infoCount = infoCount;
    }

    public void setImgCount(Integer imgCount) {
        this.imgCount = imgCount;
    }

    public void setFilmCount(Integer productCount) {
        this.filmCount = productCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public void setAdminCount(Integer adminCount) {
        this.adminCount = adminCount;
    }
}
