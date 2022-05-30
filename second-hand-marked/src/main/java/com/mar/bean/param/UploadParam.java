package com.mar.bean.param;

import lombok.Data;

@Data
public class UploadParam {

    private Long uid;

    private String name;

    private Long price;

    private String brief;

    private String type;

    private String resource;

    private String imageUrl;
}
