package com.atli.atcrowdfunding.manager.dao;

import com.atli.atcrowdfunding.bean.UserRole;

import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    List<UserRole> selectAll();

    int updateByPrimaryKey(UserRole record);
}