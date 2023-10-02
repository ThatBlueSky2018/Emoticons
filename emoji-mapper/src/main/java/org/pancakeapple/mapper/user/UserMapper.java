package org.pancakeapple.mapper.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.pancakeapple.entity.user.User;

@Mapper
public interface UserMapper {

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户信息
     */
    @Select("select * from tb_user where username=#{username}")
    User findUserByUsername(String username);

    /**
     * 新增用户
     * @param user 新增用户的信息
     */
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into tb_user(username, password) values(#{username}, #{password});")
    void addUser(User user);
}
