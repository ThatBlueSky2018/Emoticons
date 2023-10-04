package org.pancakeapple.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean implements Serializable {
    private long total;  //总数
    private List records;  //记录

}
