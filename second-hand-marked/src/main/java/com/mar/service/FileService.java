package com.mar.service;

import com.mar.bean.dto.PhotoTypeDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author guokaifeng
 * @createDate: 2022/4/25
 **/


public interface FileService {
    OutputStream outputPhoto(String url) throws IOException;
    String storgePhoto(MultipartFile multipartFile, String url,String extendsion);
}
