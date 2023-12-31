package org.pancakeapple.mapper.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.pancakeapple.annotation.AutoFill;
import org.pancakeapple.entity.user.UserRole;
import org.pancakeapple.enumeration.OperationType;

import java.util.List;

@Mapper
public interface UserRoleMapper {

    /**
     * 查找某个用户的角色
     * @param id 用户id
     * @return 用户--角色 列表
     */
    @Select("select * from user_role where user_id=#{id};")
    List<UserRole> findRolesByUserId(Long id);

    /**
     * 为用户绑定角色
     * @param userRole 封装userId,
     */
    @Insert("insert into user_role(user_id, role_id) values (#{userId},#{roleId})")
    @AutoFill(value = OperationType.INSERT)
    void insert(UserRole userRole);
}
