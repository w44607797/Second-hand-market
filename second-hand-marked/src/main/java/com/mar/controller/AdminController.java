package com.mar.controller;

import com.mar.bean.vo.ResponseResult;
import com.mar.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;


    @GetMapping("/list")
    public ResponseResult getSkuList(){
        return adminService.getAll();
    }

    @DeleteMapping("/delete")
    public ResponseResult deleteSku(Long skuId){
        return adminService.deleteOne(skuId);
    }

    @RequestMapping("/update")
    public ResponseResult passSku(Long skuId){
        return adminService.passOne(skuId);
    }
}
