package org.pancakeapple.mapper.user;

import org.apache.ibatis.annotations.*;
import org.pancakeapple.annotation.AutoFill;
import org.pancakeapple.entity.user.User;
import org.pancakeapple.enumeration.OperationType;
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
    @Insert("insert into tb_user(username, password,create_time) values(#{username}, #{password},#{createTime});")
    void addUser(User user);

    /**
     * 根据id查询用户信息
     * @param id 主键id
     * @return 用户信息
     */
    @Select("select id,email,username,gender,profile_photo,signature,is_official,create_time from tb_user where id=#{id}")
    UserInfoVO getById(Long id);

    /**
     * 修改用户信息
     * @param user 用户信息
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(User user);

    /**
     * 获取收藏夹是否公开的信息
     * @param id 用户id
     * @return 1:公开 2:私密
     */
    @Select("select public_favorite from tb_user where id=#{id}")
    Integer getFavoritesStatus(Long id);

    /**
     * 设置收藏夹公开状态
     * @param id 用户id
     * @param isOpen 是否公开
     */
    @Update("update tb_user set public_favorite=#{isOpen} where id = #{id}")
    void setFavoritesStatus(Long id,Integer isOpen);
}
