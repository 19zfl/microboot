package com.yootk.action;
import com.yootk.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
@RestController
@RequestMapping("/pages/book/")
public class BookAction {
    @Autowired
    private IBookService bookService;
    @RequestMapping("add")
    public Object add(String data) {
        return Map.of("record_size", this.bookService.add(data));
    }
    @GetMapping("/download/{year}")
    public Object downloadXsltZip(@PathVariable int year) {
        String zipFileName = "book_" + year + ".zip"; // 压缩文件名
        try {// 创建ZipOutputStream
            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            // 遍历目录下的文件，找到符合条件的文件并写入到zip文件中
            File directory = new File(IBookService.DIRECTORY);
            for (File file : directory.listFiles()) {
                if (file.getName().startsWith(year + "-") && file.getName().endsWith(".xlsx")) {
                    addToZip(file, zos);
                }
            }
            zos.close(); // 关闭ZipOutputStream
            fos.close();
        } catch (IOException e) { // 处理异常
            e.printStackTrace();
        }
        // 读取zip文件并返回给用户
        File zipFile = new File(zipFileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/zip"));
        headers.setContentDispositionFormData("attachment", zipFile.getName());
        headers.setContentLength(zipFile.length());
        try {
            byte[] zipBytes = new byte[(int) zipFile.length()];
            FileInputStream fis = new FileInputStream(zipFile);
            fis.read(zipBytes);
            fis.close();
            return new ResponseEntity<>(zipBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace(); // 处理异常
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private void addToZip(File file, ZipOutputStream zos) throws IOException {
        ZipEntry zipEntry = new ZipEntry(file.getName());
        zos.putNextEntry(zipEntry);
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) > 0) {
            zos.write(buffer, 0, len);
        }
        zos.closeEntry();
        fis.close();
    }
}