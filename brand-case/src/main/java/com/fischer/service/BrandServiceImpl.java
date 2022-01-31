package com.fischer.service;

import com.fischer.mapper.BrandMapper;
import com.fischer.pojo.Brand;
import com.fischer.pojo.PageBean;
import com.fischer.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService{
    private SqlSessionFactory factory= SqlSessionFactoryUtils.getSqlSessionFactory();
    @Override
    public List<Brand> selectAll() {
        SqlSession sqlsession=factory.openSession();
        BrandMapper mapper=sqlsession.getMapper(BrandMapper.class);
        List<Brand> brands=mapper.selectAll();
        sqlsession.close();
        return brands;
    }

    @Override
    public void add(Brand brand) {
        SqlSession sqlSession=factory.openSession();
        BrandMapper mapper=sqlSession.getMapper(BrandMapper.class);
        mapper.add(brand);
        sqlSession.commit();
        sqlSession.close();
    }
    @Override
    public void deleteByIds(int[] ids){
        SqlSession sqlSession=factory.openSession();
        BrandMapper mapper=sqlSession.getMapper(BrandMapper.class);
        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int current, int pageSize) {
        SqlSession sqlSession=factory.openSession();
        BrandMapper mapper=sqlSession.getMapper(BrandMapper.class);
        //计算开始条数
        int begin=(current-1)*pageSize;
        //计算查询条目数
        int size=pageSize;

        List<Brand> rows=mapper.selectByPage(begin,size);
        int totalCount= mapper.selectTotalCount();
        PageBean<Brand> pageBean=new PageBean<>();

        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        sqlSession.close();
        return pageBean;



    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int current, int pageSize,Brand brand) {
        SqlSession sqlSession=factory.openSession();
        BrandMapper mapper=sqlSession.getMapper(BrandMapper.class);
        //计算开始条数
        int begin=(current-1)*pageSize;
        //计算查询条目数
        int size=pageSize;
        //处理brand模糊表达式

            String brandName=brand.getBrandName();
            if(brandName!=null&&brandName.length()>0)
            {
                brand.setBrandName("%"+brandName+"%");
            }
            String companyName=brand.getCompanyName();
            if(companyName!=null&&companyName.length()>0)
            {
                brand.setCompanyName("%"+companyName+"%");
            }

            List<Brand> rows=mapper.selectByPageAndCondition(begin,size,brand);
            Integer totalCount= mapper.selectTotalCountByCondition(brand);
        System.out.println(totalCount);
            PageBean<Brand> pageBean=new PageBean<>();

            pageBean.setRows(rows);
            pageBean.setTotalCount(totalCount);

            sqlSession.close();
            return pageBean;






    }

}
