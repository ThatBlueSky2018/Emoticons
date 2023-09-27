package org.pancakeapple.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.pancakeapple.entity.user.User;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from tb_user;")
    List<User> userList();

    @Select("select * from tb_user where username=#{username}")
    User findUserByUsername(String username);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into tb_user(username, password) values(#{username}, #{password});")
    void add(User user);
}
