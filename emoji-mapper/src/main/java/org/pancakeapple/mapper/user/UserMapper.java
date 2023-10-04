package org.pancakeapple.mapper.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.pancakeapple.entity.user.User;
import org.pancakeapple.vo.user.UserInfoVO;
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
    @Insert("insert into tb_user(username, password,register_time) values(#{username}, #{password},#{registerTime});")
    void addUser(User user);

    /**
     * 根据id查询用户信息
     * @param id 主键id
     * @return 用户信息
     */
    @Select("select id,username,profile_photo,register_time from tb_user where id=#{id}")
    UserInfoVO getById(Long id);

    /**
     * 修改用户信息
     * @param user 用户信息
     */
    void update(User user);
}
