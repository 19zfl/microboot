package com.yootk.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName:Book
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/4/18 21:28
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Excel(name="图书名称", orderNum = "1", width = 50)
    private String name; // 图书名称
    @Excel(name="图书作者", orderNum = "2", width = 20)
    private String author; // 图书作者
    @Excel(name="印刷量", orderNum = "3", width = 30)
    private Integer print; // 图书印刷量
    @Excel(name="销售量", orderNum = "4", width = 30)
    private Integer sale; // 图书销售量
    @Excel(name="破损量", orderNum = "5", width = 30)
    private Integer broke; // 图书破损量
    @Excel(name="库存量", orderNum = "6", width = 30)
    private Integer inventory; // 图书库存量
}
