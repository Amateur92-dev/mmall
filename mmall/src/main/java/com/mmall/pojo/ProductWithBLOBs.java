package com.mmall.pojo;

import java.io.Serializable;

public class ProductWithBLOBs extends Product implements Serializable {
    private String subImages;//��ͼ

    private String detail;//��Ʒ����

    private static final long serialVersionUID = 1L;

    public String getSubImages() {
        return subImages;
    }

    public void setSubImages(String subImages) {
        this.subImages = subImages == null ? null : subImages.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}