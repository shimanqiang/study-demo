package me.shimanqiang.pdfbox;

import org.apache.fontbox.ttf.TrueTypeCollection;
import org.apache.fontbox.ttf.TrueTypeFont;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 使用方式上没错误，输出有问题。可能是字体的问题？
 *
 * @author shimanqiang
 * @since 2020/1/15 14:28
 */
public class TrueTypeCollectionFontTest {

    public static void main(String[] args) {
        Supplier<TrueTypeCollectionFontTest> supplier = TrueTypeCollectionFontTest::new;
        supplier.get().createFDF();
    }

    public void createFDF() {
        String TTC_FILE = "./src/main/resources/fonts/NotoSansCJK-Regular.ttc";
        PDDocument doc = new PDDocument();
        Map<String, PDFont> fontMap = new HashMap<>();
        try (InputStream inputStream = new FileInputStream(new File(TTC_FILE))) {
            TrueTypeCollection ttc = new TrueTypeCollection(inputStream);
            ttc.processAllFonts(new TrueTypeCollection.TrueTypeFontProcessor() {
                @Override
                public void process(TrueTypeFont ttf) throws IOException {
                    PDFont font = PDType0Font.load(doc, ttf, false);
                    fontMap.put(font.getName(), font);
                }
            });
            PDFont ttcFont = fontMap.get("NotoSansCJKjp-Regular");
            System.out.println(ttcFont.getName());
            PDPage page = new PDPage();
            PDPageContentStream stream = new PDPageContentStream(doc, page);
            stream.beginText();
            stream.setFont(ttcFont, 10f);
            stream.newLineAtOffset(100f, 600f);
            //stream.showText("二ろほス反2化みた大第リきやね景手ハニエ者性ルヤリウ円脱");
            stream.showText("hello");
            stream.endText();
            stream.close();
            doc.addPage(page);
            doc.save("target/test.pdf");
        } catch (Exception iox) {
            iox.printStackTrace();
        }
    }
}
