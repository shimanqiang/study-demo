package me.shimanqiang.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Supplier;

/**
 * @author shimanqiang
 * @since 2020/1/15 14:28
 */
public class TrueTypeFontTest {

    public static void main(String[] args) {
        Supplier<TrueTypeFontTest> supplier = TrueTypeFontTest::new;
        supplier.get().createFDF();
    }

    public void createFDF() {
        String TTF_FILE = "./src/main/resources/fonts/NotoSansCJKtc-Regular.ttf";
        PDDocument doc = new PDDocument();
        try (InputStream inputStream = new FileInputStream(new File(TTF_FILE))) {
            PDFont font = PDType0Font.load(doc, inputStream, true);
            System.out.println(font.getName());
            PDPage page = new PDPage();
            PDPageContentStream stream = new PDPageContentStream(doc, page);
            stream.setFont(font, 10f);
            stream.beginText();
            stream.newLineAtOffset(100f, 600f);
            stream.showText("二ろほス反2化みた大第リきやね景手ハニエ者性ルヤリウ円脱");
            stream.endText();
            stream.close();
            doc.addPage(page);
            doc.save("target/test.pdf");
        } catch (IOException iox) {
            // failed
        }
    }
}
