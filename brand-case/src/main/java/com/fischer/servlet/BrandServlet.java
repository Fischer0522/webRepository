package com.fischer.servlet;

import com.alibaba.fastjson.JSON;
import com.fischer.pojo.Brand;
import com.fischer.pojo.PageBean;
import com.fischer.service.BrandService;
import com.fischer.service.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService brandService=new BrandServiceImpl();
    public void selectAll (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<Brand> brands=brandService.selectAll();
        String jsonString= JSON.toJSONString(brands);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br=request.getReader();
        String params= br.readLine();
        Brand brand=JSON.parseObject(params,Brand.class);
        brandService.add(brand);
        response.getWriter().write("success");
    }

        /*批量删除*/
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br=request.getReader();
        String params= br.readLine();
        int[] ids=JSON.parseObject(params,int[].class);
        brandService.deleteByIds(ids);
        response.getWriter().write("success");
    }
    public void selectByPage (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String _currentPage=request.getParameter("currentPage");
        String _pageSize=request.getParameter("pageSize");
        int currentPage=Integer.parseInt(_currentPage);
        int pageSize=Integer.parseInt(_pageSize);
        PageBean<Brand> pageBean=brandService.selectByPage(currentPage,pageSize);
        response.setContentType("text/json;charset=utf-8");
        String jsonString=JSON.toJSONString(pageBean);
        response.getWriter().write(jsonString);
    }

    //分页条件查询
    public void selectByPageAndCondition (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String _currentPage=request.getParameter("currentPage");
        String _pageSize=request.getParameter("pageSize");
        int currentPage=Integer.parseInt(_currentPage);
        int pageSize=Integer.parseInt(_pageSize);
        BufferedReader br=request.getReader();
        String params= br.readLine();
        Brand brand=JSON.parseObject(params,Brand.class);
        PageBean<Brand> pageBean=brandService.selectByPageAndCondition(currentPage,pageSize,brand);
        response.setContentType("text/json;charset=utf-8");
        String jsonString=JSON.toJSONString(pageBean);
        response.getWriter().write(jsonString);
    }
}
