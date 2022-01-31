package com.fischer.service;

import com.fischer.pojo.Brand;
import com.fischer.pojo.PageBean;

import java.util.List;

public interface BrandService {
    //查询所有
    List<Brand> selectAll();
    void add(Brand brand);
    void deleteByIds(int[] ids);
    //分页查询
    //current：当前页面
    //pageSize：每页展示条数
    PageBean<Brand> selectByPage(int current,int pageSize);

    PageBean<Brand> selectByPageAndCondition(int current,int pageSize,Brand brand);


}
