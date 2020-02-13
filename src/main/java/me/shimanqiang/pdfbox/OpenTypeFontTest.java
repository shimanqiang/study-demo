package me.shimanqiang.pdfbox;

import org.apache.fontbox.ttf.OTFParser;
import org.apache.fontbox.ttf.OpenTypeFont;
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
public class OpenTypeFontTest {

    public static void main(String[] args) {
        Supplier<OpenTypeFontTest> supplier = OpenTypeFontTest::new;
        supplier.get().createFDF();
    }

    public void createFDF() {
        String OTF_FILE = "./src/main/resources/fonts/SourceHanSans-Normal.otf";
        PDDocument doc = new PDDocument();
        try (InputStream inputStream = new FileInputStream(new File(OTF_FILE))) {
            /**
             * otf字体目前只能: embedSubset=false
             * 如果设置为embedSubset=true, error: OTF fonts do not have a glyf table
             */
            OpenTypeFont openTypeFont = new OTFParser().parse(inputStream);
            PDFont font = PDType0Font.load(doc, openTypeFont, false);
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
            iox.printStackTrace();
        }
    }
}
