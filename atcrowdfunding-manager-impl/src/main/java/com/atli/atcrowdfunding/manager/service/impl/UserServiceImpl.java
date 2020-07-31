package com.atli.atcrowdfunding.manager.service.impl;

import com.atli.atcrowdfunding.bean.Permission;
import com.atli.atcrowdfunding.bean.Role;
import com.atli.atcrowdfunding.bean.User;
import com.atli.atcrowdfunding.exception.LoginFailException;
import com.atli.atcrowdfunding.manager.dao.UserMapper;
import com.atli.atcrowdfunding.manager.service.UserService;
import com.atli.atcrowdfunding.util.Const;
import com.atli.atcrowdfunding.util.MD5Util;
import com.atli.atcrowdfunding.util.Page;
import com.atli.atcrowdfunding.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserlogin(Map<String, Object> paramMap) {
        User user = userMapper.queryUserlogin(paramMap);

        if (user == null) {
            throw new LoginFailException("用户名账号或密码不正确！");
        }

        return user;
    }

    @Override
    public Page queryPage(Map<String, Object> paramMap) {
        Page page = new Page((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = page.getStartIndex();
        paramMap.put("startIndex", startIndex);
        List<User> datas = userMapper.queryList(paramMap);
        page.setDatas(datas);

        Integer totalsize = userMapper.queryCount(paramMap);
        page.setTotalsize(totalsize);

        return page;
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteBatchUser(Integer[] ids) {
        int totalCount = 0;
        for (Integer id : ids) {
            int count = userMapper.deleteByPrimaryKey(id);
            totalCount += count;
        }
        if (totalCount != ids.length) {
            throw new RuntimeException("批量删除失败");
        }
        return totalCount;
    }

    /*@Override
    public int deleteBatchUserByVo(Data data) {
        return userMapper.deleteBatchUserByVo(data);
    }*/

    @Override
    public int deleteBatchUserByVo(Data data) {
        return userMapper.deleteBatchUserByVo(data.getDatas());
    }

    @Override
    public List<Role> queryAllRole() {
        return userMapper.queryAllRole();
    }

    @Override
    public List<Integer> queryRoleByUserid(Integer id) {
        return userMapper.queryRoleByUserid(id);
    }

    @Override
    public int saveUserRoleRelationship(Integer userid, Data data) {
        return userMapper.saveUserRoleRelationship(userid, data);
    }

    @Override
    public int deleteUserRoleRelationship(Integer userid, Data data) {
        return userMapper.deleteUserRoleRelationship(userid, data);
    }

    @Override
    public List<Permission> queryPermissionByUserid(Integer id) {
        return userMapper.queryPermissionByUserid(id);
    }

    /*@Override
    public Page queryPage(Integer pageno, Integer pagesize) {
        Page page = new Page(pageno, pagesize);

        Integer startIndex = page.getStartIndex();
        List<User> datas = userMapper.queryList(startIndex, pagesize);
        page.setDatas(datas);

        Integer totalsize = userMapper.queryCount();
        page.setTotalsize(totalsize);

        return page;
    }*/

    @Override
    public int saveUser(User user) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String createtime = sdf.format(date);
        user.setCreatetime(createtime);
        user.setUserpswd(MD5Util.digest(Const.PASSWORD));
        return userMapper.insert(user);
    }

}
