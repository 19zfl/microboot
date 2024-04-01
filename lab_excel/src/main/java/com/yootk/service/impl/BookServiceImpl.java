package com.yootk.service.impl;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.export.ExcelExportService;
import com.yootk.service.IBookService;
import com.yootk.vo.Book;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class BookServiceImpl implements IBookService {
    @Override
    public int add(String data) {
        // 图书名称,图书作者,图书印刷量,图书销售量,图书破损量
        String regex = "^[\\u4e00-\\u9fa5a-zA-Z]+,[\\u4e00-\\u9fa5a-zA-Z]+,\\d+,\\d+,\\d+$";
        if (data.matches(regex)) { // 正则匹配
            // 要输出的Excel文件名称
            String fileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".xlsx";
            File file = new File(DIRECTORY, fileName);
            List<Book> bookList = null;
            if (file.exists()) { // 文件存在，读取数据
                ImportParams importParams = new ImportParams();
                importParams.setSheetNum(1);
                bookList = ExcelImportUtil.importExcel(file, Book.class, importParams);
            } else { // 新建数据
                if (!file.getParentFile().exists()) { // 父目录不存在
                    file.getParentFile().mkdirs(); // 创建目录
                }
                bookList = new ArrayList<>(); // 创建新集合
            }
            String [] array = data.split(","); // 数据拆分
            Book book = new Book(); // 实例化Model对象
            book.setName(array[0]); // 设置图书名称
            book.setAuthor(array[1]); // 设置图书作者
            book.setPrint(Integer.parseInt(array[2])); // 设置图书印刷量
            book.setSale(Integer.parseInt(array[3])); // 设置图书销售量
            book.setBroker(Integer.parseInt(array[4])); // 设置图书破损量
            book.setInventory(book.getPrint() - book.getSale() - book.getBroker()); // 设置库存量
            bookList.add(book); // 添加数据
            int size = bookList.size();
            ExportParams exportParams = new ExportParams(); // 导出配置
            Workbook workbook = new XSSFWorkbook();    // 创建工作薄
            new ExcelExportService().createSheet(workbook, exportParams,
                    Book.class, bookList); // 创建表格
            try {
                workbook.write(new FileOutputStream(file));     // 数据导出
            } catch (IOException e) {
                e.printStackTrace();
            }
            return size;
        }
        return 0;
    }
}