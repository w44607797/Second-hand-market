package com.mar.bean.dto;

import lombok.Getter;

/**
 * @author guokaifeng
 * @createDate: 2022/4/25
 **/
@Getter
public class PhotoTypeDTO {

    private String url;
    private String extendsion;

    PhotoTypeDTO(String url,String extendsion){
        this.url = url;
        this.extendsion = extendsion;
    }


}
