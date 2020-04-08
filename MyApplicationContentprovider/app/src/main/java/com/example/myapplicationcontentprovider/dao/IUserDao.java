package com.example.myapplicationcontentprovider.dao;

import com.example.myapplicationcontentprovider.pojo.User;

import java.util.List;

/**
 * 數據庫接口層
 */

public interface IUserDao {

    /**
     * 添加用戶
     *
     * @param user
     */
    long addUser(User user);

    /**
     * 刪除用戶
     * @param id
     */
    int delUserById(int id);

    /**
     * 更新用戶訊息
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 查詢用戶紀錄
     * @param id
     * @return
     */
    User getUserById(int id);

    /**
     * 獲取所有用戶的紀錄
     * @return
     */
    List<User> listAllUser();

}
