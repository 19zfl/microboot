package com.yootk.action;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.yootk.service.IBookService;
import com.yootk.vo.Book;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.io.File;
import java.util.Locale;
@RestController
@RequestMapping("/pages/book/")
public class BookAction {
    @Autowired
    private IBookService bookService;
    @RequestMapping("stat/{year}")
    public void getBookStat(@PathVariable int year, HttpServletResponse response) throws Exception {
        response.setHeader("content-Type", "application/pdf");    // 响应MIME类型
        response.setHeader("Content-Disposition",
                "attachment;filename=yootk_book_stat_" + year + ".pdf"); // 下载文件名称
        Document document = new Document(PageSize.A4, 10, 10, 50, 20); // 定义PDF文档
        PdfWriter.getInstance(document, response.getOutputStream()); // 获取服务端输出流
        document.open(); // 创建文档
        Resource fontResource = new ClassPathResource(
                "/fonts/Alibaba-PuHuiTi-Bold.ttf");       // 加载本地字体
        BaseFont baseFont = BaseFont.createFont(fontResource.getFile().getAbsolutePath(),
                BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);   // 加载字体资源
        com.itextpdf.text.Font font = new com.itextpdf.text.Font(baseFont, 12, Font.NORMAL);      // 定义字体
        List<Book.BookSummary> result = this.bookService.stat(year);
        PdfPTable table = new PdfPTable(5);
        table.addCell(new Paragraph("图书名称", font));
        table.addCell(new Paragraph("年份", font));
        table.addCell(new Paragraph("印刷总量", font));
        table.addCell(new Paragraph("销售总量", font));
        table.addCell(new Paragraph("破损总量", font));
        for (Book.BookSummary summary : result) {
            table.addCell(new Paragraph(summary.getName(), font));
            table.addCell(String.valueOf(year));
            table.addCell(String.valueOf(summary.getPrintSum()));
            table.addCell(String.valueOf(summary.getSaleSum()));
            table.addCell(String.valueOf(summary.getBrokeSum()));
        }
        document.add(table);
        document.add(new Paragraph("\n\n\n")); // 追加段落
        for (Book.BookSummary summary : result) {
            // 生成统计图
            JFreeChart chart = createChart(summary);
            String chartFilePath = "chart_" + summary.getName() + ".png";
            saveChartAsImage(chart, chartFilePath);
            // 添加统计图到PDF
            com.itextpdf.text.Image chartImage = com.itextpdf.text.Image.getInstance(chartFilePath);
            chartImage.scaleToFit(PageSize.A4.getWidth() * 0.9f, PageSize.A4.getHeight());
            document.add(chartImage);
            // 删除临时文件
            File file = new File(chartFilePath);
            file.delete();
        }
        document.close();
    }
    private JFreeChart createChart(Book.BookSummary summary) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("印刷总量", summary.getPrintSum());
        dataset.setValue("销售总量", summary.getSaleSum());
        dataset.setValue("破损总量", summary.getBrokeSum());
        //创建主题样式
        StandardChartTheme standardChartTheme=new StandardChartTheme("CN");
        //设置标题字体
        standardChartTheme.setExtraLargeFont(new java.awt.Font("隶书",Font.BOLD,20));
        //设置图例的字体
        standardChartTheme.setRegularFont(new java.awt.Font("宋书",Font.NORMAL,15));
        //设置轴向的字体
        standardChartTheme.setLargeFont(new java.awt.Font("宋书",Font.NORMAL,15));
        //应用主题样式
        ChartFactory.setChartTheme(standardChartTheme);
        JFreeChart chart = ChartFactory.createPieChart(
                summary.getName() + "的统计图",
                dataset,
                true,
                true,
                false
        );
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(
                new StandardPieSectionLabelGenerator("{0} = {1} ({2})", Locale.CHINA));
        TextTitle title = new TextTitle("《" + summary.getName() + "》，图书统计图");
        title.setPaint(new Color(51, 51, 51));
        title.setFont(new java.awt.Font("宋体", Font.NORMAL, 18));
        chart.setTitle(title);
        return chart;
    }
    private void saveChartAsImage(JFreeChart chart, String filePath) {
        try {
            BufferedImage image = chart.createBufferedImage(800, 600);
            ImageIO.write(image, "png", new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}