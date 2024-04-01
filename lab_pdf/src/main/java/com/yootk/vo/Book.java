package com.yootk.vo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
@Data
public class Book {

    @Data
    public static class BookSummary { // 图书统计数据
        private String name; // 图书名称
        private int printSum; // 印刷总量
        private int saleSum; // 销售总量
        private int brokeSum; // 破损总量

        public BookSummary() {
        }

        public BookSummary(String name, int printSum, int saleSum, int brokeSum) {
            this.name = name;
            this.printSum = printSum;
            this.saleSum = saleSum;
            this.brokeSum = brokeSum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrintSum() {
            return printSum;
        }

        public void setPrintSum(int printSum) {
            this.printSum = printSum;
        }

        public int getSaleSum() {
            return saleSum;
        }

        public void setSaleSum(int saleSum) {
            this.saleSum = saleSum;
        }

        public int getBrokeSum() {
            return brokeSum;
        }

        public void setBrokeSum(int brokeSum) {
            this.brokeSum = brokeSum;
        }
    }
    private Long bid;
    private String name;
    private Integer sale;
    private Integer broke;
    private Integer inventory;
    private Date indate;
}