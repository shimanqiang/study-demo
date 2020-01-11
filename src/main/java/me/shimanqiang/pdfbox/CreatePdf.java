package me.shimanqiang.pdfbox;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDSimpleFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.apache.pdfbox.util.Charsets;
import org.apache.pdfbox.util.Matrix;

import java.awt.*;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Created by Administrator on 2020/1/4.
 * https://iowiki.com/pdfbox/pdfbox_quick_guide.html
 * https://zhuanlan.zhihu.com/p/52863069
 * https://programtalk.com/java-api-usage-examples/org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject/
 * <p>
 * float realWidth = FONT_SIZE * FONT.getStringWidth(tmpText) / 1000;
 * <p>
 * <p>
 * http://www.voidcn.com/article/p-remetdme-bsx.html
 * <p>
 * https://github.com/dongdongdeng/iPDFBox
 * https://github.com/vandeseer/easytable
 */
public class CreatePdf {
    private static final String FONT_PATH = "fonts/SIMFANG.TTF";
    //private static final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(FONT_PATH);
    //PDFont font = PDType0Font.load(document, inputStream);

    public static void main(String[] args) throws Exception {
        CreatePdf createPdf = new CreatePdf();
        createPdf.createEmptyPdf();

    }

    private void createEmptyPdf() throws Exception {
        //创建文档
        PDDocument document = new PDDocument();
        COSDocument cosDocument = document.getDocument();
        //设置PDF文档属性
        PDDocumentInformation documentInformation = document.getDocumentInformation();
        documentInformation.setAuthor("meiying");
        documentInformation.setTitle("study pdfbox");

        String text = "This is an example of adding text to a page in the pdf document. " +
                "we can add as many lines as we want like " +
                "this using the <b class=\"notranslate\">showText()</b> method of the ContentStream class";
        //添加页
        for (int i = 0; i < 2; i++) {
            PDRectangle pageSize = PDRectangle.A4;
            float pageSizeHeight = pageSize.getHeight();
            float pageSizeWidth = pageSize.getWidth();
            System.out.println(pageSizeHeight);
            System.out.println(pageSizeWidth);
            PDPage page = new PDPage(pageSize);
            document.addPage(page);
            //准备内容流
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
//            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.PREPEND, false);
            /**
             * 开始写数据
             * 为了向PDF添加多行，您需要使用setLeading()方法设置前导，并在完成每一行后使用newline()方法切换到新行。
             */
            float fontSize = 12f;
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 500);
            contentStream.setFont(PDType1Font.TIMES_ROMAN, fontSize);
            //使用setLeading()方法设置文本引导（就是行间距）
            contentStream.setLeading(14.5f);
            //内容
            contentStream.showText("This is an example of adding text to a page in the pdf document. ");
            //换行操作
            contentStream.newLine();
            //内容2
            contentStream.showText("This is an example of adding text to a page in the pdf document. ");
            contentStream.endText();

            /**
             * 插入图片
             */
            PDImageXObject pdImage = PDImageXObject.createFromFile("pic1.jpg", document);
            contentStream.drawImage(pdImage, 25, 250, 200.5f, 200.5f);

            /**
             * 添加表格
             */
            int rows = 3;
            int cols = 5;
            float x = 15f;
            float y = 600f;
            float cellWidth = 60f;
            float cellHeight = 20f;
            float tableWidth = cellWidth * cols;
            float tableHeight = cellHeight * rows;

            for (int row = 0; row <= rows; row++) {
                if (row == 0) {
                    //保存图形状态
                    contentStream.saveGraphicsState();
                    PDExtendedGraphicsState r0 = new PDExtendedGraphicsState();
                    r0.setNonStrokingAlphaConstant(0.3f);
                    r0.setAlphaSourceFlag(true);
                    contentStream.setGraphicsStateParameters(r0);
                    contentStream.setNonStrokingColor(Color.BLACK);
                    contentStream.addRect(x, y - cellHeight, tableWidth, cellHeight);
                    contentStream.fill();
                    //复原图形状态
                    contentStream.restoreGraphicsState();
                }
                float ytmp = y - row * cellHeight;
                contentStream.moveTo(x, ytmp);
                contentStream.lineTo(x + tableWidth, ytmp);
            }

            for (int col = 0; col <= cols; col++) {
                float xtmp = x + col * cellWidth;
                contentStream.moveTo(xtmp, y);
                contentStream.lineTo(xtmp, y - tableHeight);
            }

//            PDDocumentCatalog documentCatalog = document.getDocumentCatalog();
//            PDAcroForm form = new PDAcroForm(document);
//            PDField field = new PDTextField(form);
//            field.setValue("aaaaaaaaaaaaaaaaa");
//            form.setFields(Arrays.asList(field));
//            documentCatalog.setAcroForm(form);


            float fontHeight = PDType1Font.TIMES_ROMAN.getBoundingBox().getHeight() / 1000;
            float textx = x + 5;
            float texty = y - cellHeight / 2 - fontSize / 2;
            texty = y - cellHeight+ (cellHeight-fontSize)/2 ;
            for (int row = 0; row < rows; row++) {
                float textytmp = texty - row * cellHeight;
                for (int col = 0; col < cols; col++) {
                    float textxtmp = textx + col * cellWidth;
                    contentStream.beginText();
                    contentStream.newLineAtOffset(textxtmp, textytmp);
                    contentStream.showText("text-" + row + "-" + col);
                    contentStream.endText();
                }
            }

            /**
             * 添加矩形
             */
            contentStream.setNonStrokingColor(Color.BLACK);
            contentStream.addRect(200, 650, 100, 100);
            contentStream.fill();

            /**
             * 添加水印
             */
            String ts = "Test";
            //保存图形状态
            contentStream.saveGraphicsState();
            PDExtendedGraphicsState r0 = new PDExtendedGraphicsState();
            // 透明度
            r0.setNonStrokingAlphaConstant(0.3f);
            r0.setAlphaSourceFlag(true);
            contentStream.setGraphicsStateParameters(r0);
            contentStream.setNonStrokingColor(Color.BLUE);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 80.0f);
            // 获取旋转实例
            contentStream.setTextMatrix(Matrix.getRotateInstance(45, 320f, 150f));
            contentStream.showText(ts);
            contentStream.endText();
            //复原图形状态
            contentStream.restoreGraphicsState();


            /**
             * 关闭内容流
             */
            contentStream.close();
        }

//        //加密文档
//        AccessPermission ap = new AccessPermission();
//        StandardProtectionPolicy spp = new StandardProtectionPolicy("1234", "1234", ap);
//        spp.setEncryptionKeyLength(128);
//        spp.setPermissions(ap);
//        document.protect(spp);

        //保存PDF文件
        document.save("target/empty.pdf");
        document.close();

        System.out.println("PDF created");
    }
}
