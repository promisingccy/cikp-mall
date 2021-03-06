package com.cikp.mall.dao;

import com.cikp.mall.mybatisFile.model.UmsPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UmsAdminRoleRelationDao
 * @Description 后台用户与角色管理自定义Dao  实际上这个mapper的作用，需加入扫描
 *          MapperScan({"com.cikp.mall.mybatisFile.mapper", "com.cikp.mall.dao"})
 * @Author ccy
 * @Date 2020/12/15 20:20
 * @Version 1.0
 **/
public interface UmsAdminRoleRelationDao {

    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}