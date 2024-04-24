package com.yootk.action;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.yootk.service.IBookService;
import com.yootk.vo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:BookAction
 * @Description:
 * @Author:zfl19
 * @CreateDate:2024/4/18 21:35
 */

@RestController
@RequestMapping("/pages/book/")
public class BookAction {

    @Autowired
    private IBookService bookService;
    @RequestMapping("upload")
    public Object upload(MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        result.put("record", 0);
        if (file != null && file.getSize() > 0) {
            try {
                // 读取上传的 excel 文件
                InputStream inputStream = file.getInputStream();
                ImportParams importParams = new ImportParams();
                importParams.setSheetNum(1);
                List<Book> bookList = ExcelImportUtil.importExcel(inputStream,
                        Book.class, importParams);
                result.put("record", this.bookService.add(bookList));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
