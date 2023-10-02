package org.pancakeapple.mapper.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.pancakeapple.entity.user.UserRole;

import java.util.List;

@Mapper
public interface UserRoleMapper {

    /**
     * 查找某个用户的角色
     * @param id 用户id
     * @return 用户--角色 列表
     */
    @Select("select * from user_role where user_id=#{id};")
    List<UserRole> findRolesByUser(Long id);

    /**
     * 为用户绑定角色
     * @param userId 用户id
     * @param roleId 角色id
     */
    @Insert("insert into user_role(user_id, role_id) values (#{userId},#{roleId})")
    void insert(Long userId, Long roleId);
}
