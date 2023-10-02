package org.pancakeapple.mapper.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.pancakeapple.entity.user.Role;

@Mapper
public interface RoleMapper {
    /**
     * 根据角色名称查找角色
     * @param roleName 角色名称
     * @return 角色信息
     */
    @Select("select * from tb_role where role_name=#{roleName}")
    Role findRoleByRoleName(String roleName);

    /**
     * 根据角色id查找角色
     * @param id 角色id
     * @return 角色信息
     */
    @Select("select * from tb_role where id=#{id};")
    Role findRoleById(Long id);
}