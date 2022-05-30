package com.mar.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mar.bean.dao.Category;
import com.mar.bean.dao.Sku;
import com.mar.bean.mapper.SkuMapper;
import com.mar.bean.param.ListParam;
import com.mar.bean.param.UploadParam;
import com.mar.bean.vo.ResponseResult;
import com.mar.exception.TotalException;
import com.mar.service.CategoryService;
import com.mar.service.ItemService;
import com.mar.service.ListService;
import com.mar.service.SkuService;
import com.mar.utils.FileUtil;
import com.mar.utils.ObjectUtil;
import com.mar.utils.QiniuUtils;
import com.mar.utils.StateEnum;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.io.*;
import java.util.List;
import java.util.UUID;

import static com.mar.utils.QiniuUtils.url;

/**
 * 因为/api后的地址都不一样，但是都是与商品有关
 * 将功能3、4、5都写在这里了
 */

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private CategoryService categoryService;
    @Resource
    private ItemService itemService;
    @Resource
    private ListService listService;
    @Resource
    private ObjectUtil objectUtil;
    @Autowired
    Mapper dozerMapper;
    @Resource
    SkuService service;

    @GetMapping("/product/getBaseCategoryList")
    public ResponseResult getBaseCategoryList(){
        List<Category> categoryList = categoryService.categoryList();
        return ResponseResult.success(categoryList);
    }

    @PostMapping(value = "/list")
    public ResponseResult list(ListParam searchParam){
        System.out.println(searchParam);
        //什么都没传入，那就是首页展示
        if(objectUtil.isAllFieldNull(searchParam)){
            System.out.println("!");
            return getBaseCategoryList();
        }
        return listService.list(searchParam);
    }

    @GetMapping("/item/{skuId}")
    public ResponseResult productDetails(@PathVariable String skuId){
        return itemService.getDetailsInfo(skuId);
    }

    @PostMapping("/upload")
    public ResponseResult upload(UploadParam uploadParam){
        /**
         *     private Long uid=userId;
         *     private String name=skuName;
         *     private Long price=price;
         *     private String brief=skudesc;
         *     private String type=category3Id;
         *     private String resource=resource;
         *     private String imageUrl=skuDefaultImg;
         */
        try {
            Sku sku = new Sku();
            dozerMapper.map(uploadParam, sku);
            service.save(sku);
            return ResponseResult.success();
        }catch (Exception e){
            return ResponseResult.failed("0",e.getMessage());
        }
    }

    @PostMapping("/uploadImg")
    public ResponseResult upload(String skuId,MultipartFile file) throws IOException {
        if(file==null){
            return ResponseResult.failed("0","上传的文件为空");
        }
        StringBuilder stringBuilder = new StringBuilder(60);
        String url = "/content/";
        File file1 = new File("/content");
        if(!file1.exists()){
            file1.mkdirs();
        }
        String uuid = FileUtil.getUUID();
        String fileExtension = FileUtil.getFileExtension(file);
        stringBuilder.append(url);
        stringBuilder.append(uuid);
        stringBuilder.append(fileExtension);
        try {
            InputStream inputStream = file.getInputStream();
            File tofile = new File(stringBuilder.toString());
            OutputStream outputStream = new FileOutputStream(tofile);
            int readlen;
            byte[] bytes = new byte[4096];
            while ((readlen = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, readlen);
            }
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String imageUrl = stringBuilder.toString();
        UpdateWrapper<Sku> updateWrapper = new UpdateWrapper();
        updateWrapper.eq("sku_id",skuId);
        updateWrapper.set("imageUrl",imageUrl);
        service.update(updateWrapper);
        return ResponseResult.success();

    }
    @GetMapping("/getphoto")
    public ResponseResult getImg(String skuId, ServletOutputStream outputStream) throws IOException, TotalException {

        Sku sku = service.getById(skuId);
        String skuDefaultImg = sku.getSkuDefaultImg();
        if(skuDefaultImg==null){
            return ResponseResult.success("用户没有上传图片");
        }
        try {
            InputStream fileInputStream = new FileInputStream(skuDefaultImg);
            int ch;
            while ((ch = fileInputStream.read()) != -1) {
                outputStream.write(ch);
            }
        } catch (IOException e) {
            throw new TotalException(StateEnum.FILE_FAILED.getCode(),StateEnum.FILE_FAILED.getMessage(),e.getMessage());
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }

        }
        return ResponseResult.success();
    }
}
