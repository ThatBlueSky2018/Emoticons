package org.pancakeapple.dto.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pancakeapple.enumeration.BehaviorType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateDocumentDTO {
    private Long id; //要更新的文档id
    private BehaviorType behaviorType; //根据不同的操作类型，选择不同的字段进行更新
}
