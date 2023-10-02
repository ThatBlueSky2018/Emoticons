package org.pancakeapple.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色多对多关系实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    private Long id;
    private Long userId;
    private Long roleId;
}
