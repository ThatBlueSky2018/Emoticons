package org.pancakeapple.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.pancakeapple.entity.user.Role;

@Mapper
public interface RoleMapper {
    @Select("select * from tb_role where role_name=#{roleName}")
    Role findRoleByRoleName(String roleName);

    @Select("select * from tb_role where id=#{id};")
    Role findRoleById(Long id);
}