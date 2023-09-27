package org.pancakeapple.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.pancakeapple.entity.user.UserRole;

import java.util.List;

@Mapper
public interface UserRoleMapper {

    @Select("select * from user_role where user_id=#{id};")
    List<UserRole> findRolesByUser(Long id);

    @Insert("insert into user_role values (#{userId},#{roleId})")
    void insert(Long userId, Long roleId);
}
