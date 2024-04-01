package com.yootk.vo;
import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
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
    private Integer broker; // 图书破损量
    @Excel(name="库存量", orderNum = "6", width = 30)
    private Integer inventory; // 图书库存量

    public Book() {
    }

    public Book(String name, String author, Integer print, Integer sale, Integer broker, Integer inventory) {
        this.name = name;
        this.author = author;
        this.print = print;
        this.sale = sale;
        this.broker = broker;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPrint() {
        return print;
    }

    public void setPrint(Integer print) {
        this.print = print;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public Integer getBroker() {
        return broker;
    }

    public void setBroker(Integer broker) {
        this.broker = broker;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }
}