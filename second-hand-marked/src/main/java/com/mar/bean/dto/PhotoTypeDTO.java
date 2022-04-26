package com.mar.bean.dto;

import lombok.Getter;

/**
 * @author guokaifeng
 * @createDate: 2022/4/25
 **/
@Getter
public enum PhotoTypeDTO {
    HEADSHOT_PNG("png");



    private String extendsion;

    PhotoTypeDTO(String extendsion){
        this.extendsion = extendsion;
    }

    public void checkValid(String extendsion){

    }


}
