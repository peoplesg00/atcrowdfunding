package com.atli.atcrowdfunding.manager.service;

import com.atli.atcrowdfunding.bean.Permission;
import com.atli.atcrowdfunding.bean.Role;
import com.atli.atcrowdfunding.bean.User;
import com.atli.atcrowdfunding.util.Page;
import com.atli.atcrowdfunding.vo.Data;

import java.util.List;
import java.util.Map;

/**
 * @author LI
 */
public interface UserService {
    User queryUserlogin(Map<String, Object> paramMap);

    //@Deprecated
    //Page queryPage(Integer pageno, Integer pagesize);

    int saveUser(User user);

    Page queryPage(Map<String, Object> paramMap);

    User getUserById(Integer id);

    int updateUser(User user);

    int deleteUser(Integer id);

    int deleteBatchUser(Integer[] ids);

    int deleteBatchUserByVo(Data data);

    List<Role> queryAllRole();

    List<Integer> queryRoleByUserid(Integer id);

    int saveUserRoleRelationship(Integer userid, Data data);

    int deleteUserRoleRelationship(Integer userid, Data data);

    List<Permission> queryPermissionByUserid(Integer id);
}
