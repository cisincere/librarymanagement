package com.l.library.sysadmin.organization.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.l.library.sysadmin.organization.entity.param.ResourceQueryParam;
import com.l.library.sysadmin.organization.entity.po.Resource;

import java.util.List;

public interface IResourceService{
    /**
     * 获取资源
     */
    Resource get(String id);

    /**
     * 新增资源
     *
     * @param resource
     * @return
     */
    boolean add(Resource resource);

    /**
     * 查询资源 分页
     *
     * @param page
     * @param resourceQueryParam
     * @return
     */
    IPage<Resource> query(Page page, ResourceQueryParam resourceQueryParam);

    /**
     * 查询所有的资源
     *
     * @return
     */
    List<Resource> getAll();
    /**
     * 更具username查看用户拥有的资源
     *
     */
    List<Resource> query(String username);

    /**
     * 更新资源
     */
    boolean update(Resource resource);

    /**
     * 根据id删除资源
     */
    boolean delete(String id);
}
