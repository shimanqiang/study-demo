package me.anna.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.File;
import java.io.InputStream;
import java.util.*;

/**
 * @author shimanqiang
 * @since 2019/4/28 10:00
 * <p>
 * https://blog.csdn.net/qq_37022150/article/details/79486730
 * <p>
 * <p>
 * https://www.cnblogs.com/tankqiu/articles/4246776.html
 * <p>
 * <p>
 * https://www.yiibai.com/pdfbox/pdfbox_adding_multiple_lines.html
 */
public class ReadPdfInfo {
    private static final String FONT_PATH = "fonts/SIMFANG.TTF";

    public static void main(String[] args) throws Exception {
        initData();


        String baseTarget = "主班教学双周计划_" + "2019年6月_" + "3周_4周_";

        int index = 0;
        List<Student> stdList = new ArrayList<>();
        for (int i = 0; i < StudentList.size(); i++) {
            Student student = StudentList.get(i);
            student.setPracticalLifeList(randomData(PracticalLifeList));
            student.setSensorialList(randomData(SensorialList));
            student.setLanguageList(randomData(LanguageList));
            student.setMathematicsList(randomData(MathematicsList));
            student.setCultureList(randomData(CultureList));
            student.setObservationList(randomData(ObservationList));
            stdList.add(student);

            int last = StudentList.size() - 1;
            if (stdList.size() % 5 == 0 && i != last) {
                genData(stdList, baseTarget + index);
                index++;
                stdList.clear();
            }
            if (i == last) {
                genData(stdList, baseTarget + index);
            }
        }
    }

    public static List<String> randomData(List<String> allData) throws Exception {
        List<String> randomDataList = new ArrayList<>(5);
        randomDataList.add("");
        randomDataList.add("");
        randomDataList.add("");
        randomDataList.add("");
        randomDataList.add("");
        if (allData != null && allData.size() > 0) {
            int size = allData.size();
            if (size <= 5) {
                for (int i = 0; i < size; i++) {
                    randomDataList.set(i, allData.get(i));
                }
            } else {
                randomDataList.clear();
                Set<Integer> randomDataIndex = new HashSet<>();
                while (randomDataIndex.size() < 5) {
                    int x = new Random().nextInt(size - 1);
                    randomDataIndex.add(x);
                }
                for (Integer index : randomDataIndex) {
                    randomDataList.add(allData.get(index));
                }
            }
        }
        return randomDataList;
    }


    public static void initData() throws Exception {
        initStudentData();
        initPracticalLifeData();
        initSensorialData();
        initLanguageData();
        initMathematicsData();
        initCultureData();
        initObservationData();
    }

    //初始化学生
    private static List<Student> StudentList = new ArrayList<>();
    //初始化日常
    private static List<String> PracticalLifeList = new ArrayList<>();
    //初始化感官
    private static List<String> SensorialList = new ArrayList<>();
    //初始化语言
    private static List<String> LanguageList = new ArrayList<>();
    //初始化数学
    private static List<String> MathematicsList = new ArrayList<>();
    //初始化文化
    private static List<String> CultureList = new ArrayList<>();
    //初始化观察记录
    private static List<String> ObservationList = new ArrayList<>();

    public static void initStudentData() throws Exception {

        StudentList.add(new Student("杨依诺", 5));
        StudentList.add(new Student("王语墨", 5));
        StudentList.add(new Student("李世其", 5));
        StudentList.add(new Student("王泽翰", 5));
        StudentList.add(new Student("徐朗", 5));
        StudentList.add(new Student("赵昕宸", 5));
        StudentList.add(new Student("颜语希", 5));
        StudentList.add(new Student("刘小禾", 4));
        StudentList.add(new Student("蔡家宇", 4));
        StudentList.add(new Student("张珺贺", 4));
        StudentList.add(new Student("孙童瑶", 4));
        StudentList.add(new Student("戴艺璇", 3));
        StudentList.add(new Student("邓婉馨", 4));
        StudentList.add(new Student("王玄同", 3));
        StudentList.add(new Student("赵辰逸", 3));
        StudentList.add(new Student("王骏道", 5));
        StudentList.add(new Student("邓诗彤", 3));
        StudentList.add(new Student("唐嘉良", 3));

    }

    public static void initPracticalLifeData() throws Exception {
        //准备
        PracticalLifeList.add("正确使用椅子");
        PracticalLifeList.add("卷、铺工作毯");
        PracticalLifeList.add("双手端托盘");
        PracticalLifeList.add("舀豆子");
        PracticalLifeList.add("倒水");
        PracticalLifeList.add("擦玻璃");
        PracticalLifeList.add("拧湿布");
        PracticalLifeList.add("系鞋带");
        PracticalLifeList.add("切水果模型");
        PracticalLifeList.add("折叠布");
        PracticalLifeList.add("无声游戏");
        PracticalLifeList.add("走线");
        //生活自理
        PracticalLifeList.add("洗手");
        PracticalLifeList.add("刷毛");
        PracticalLifeList.add("梳头");
        PracticalLifeList.add("叠衣服");
        PracticalLifeList.add("蝴蝶结衣饰框");
        PracticalLifeList.add("皮带扣衣饰框");
        PracticalLifeList.add("大纽扣衣饰框");
        PracticalLifeList.add("拉拉链衣饰框");
        PracticalLifeList.add("挂钩衣饰框");
        PracticalLifeList.add("别针衣饰框");
        PracticalLifeList.add("穿带衣饰框");
        PracticalLifeList.add("小纽扣衣饰框");
        PracticalLifeList.add("粘扣衣饰框");
        //爱护环境
        PracticalLifeList.add("开门、关门");
        PracticalLifeList.add("搬运桌椅");
        PracticalLifeList.add("扫地");
        PracticalLifeList.add("照顾植物");
        PracticalLifeList.add("准备餐桌");
        PracticalLifeList.add("洗衣服");
        PracticalLifeList.add("擦洗桌子");
        PracticalLifeList.add("手工艺工作");
        //社会礼仪
        PracticalLifeList.add("打招呼");
        PracticalLifeList.add("自我介绍");
        PracticalLifeList.add("介绍别人");
        PracticalLifeList.add("帮助别人");
        PracticalLifeList.add("接待客人");
        PracticalLifeList.add("打断别人");
        PracticalLifeList.add("如何向别人借东西");
        PracticalLifeList.add("撮鼻涕");
    }

    public static void initSensorialData() throws Exception {
        //视觉识别力
        //辨识尺寸
        SensorialList.add("插座圆柱体");
        SensorialList.add("4组插座圆柱体");
        SensorialList.add("插座圆柱体及投影卡");
        SensorialList.add("粉红塔");
        SensorialList.add("棕色梯");
        SensorialList.add("长棒");
        SensorialList.add("粉红塔延伸");
        SensorialList.add("粉红塔与投影卡");
        SensorialList.add("棕色梯延伸");
        SensorialList.add("棕色梯与投影卡");
        SensorialList.add("粉红塔与棕色梯组合");
        SensorialList.add("长棒延伸");
        //辨识颜色
        SensorialList.add("色板1");
        SensorialList.add("色板盒2");
        //辨识形状
        SensorialList.add("立体几何体组");
        SensorialList.add("几何图形嵌板厨");
        SensorialList.add("几何图形嵌板厨及对应卡");
        //建构三角形
        //SensorialList.add("建构三角形");
        SensorialList.add("矩形盒A");
        SensorialList.add("矩形盒B");
        SensorialList.add("立方体");
        SensorialList.add("二项式");
        SensorialList.add("三项式");

        //听觉识别力
        SensorialList.add("无声游戏");
        SensorialList.add("听觉筒");
        SensorialList.add("音乐的调子");
        //触觉判断力
        SensorialList.add("触觉篮");
        SensorialList.add("触摸板1");
        SensorialList.add("触摸配对板");
        SensorialList.add("布盒");

        //综合感受力
        //实体感受力
        SensorialList.add("神秘袋");
        //重量感受力
        SensorialList.add("物体篮（轻/重）");
        SensorialList.add("图形渐层");
        SensorialList.add("十项式的平方");
        SensorialList.add("立体几何组命名");
        SensorialList.add("立体几何组与投影板");
        SensorialList.add("制作立体几何模型");
        //温觉感受力
        //嗅觉感受力
        //味觉感受力
    }

    public static void initLanguageData() throws Exception {
        LanguageList.add("语言");
        //听力开发
        LanguageList.add("听力游戏");
        LanguageList.add("我发现游戏");
        //视觉开发
        LanguageList.add("配对卡");
        LanguageList.add("图形练习");
        LanguageList.add("分类游戏");
        LanguageList.add("记忆力游戏");
        //动作准备和发展
        LanguageList.add("沙盘");
        LanguageList.add("黑板");
        LanguageList.add("金属嵌板");
        LanguageList.add("名字描摹");
        //综合技能
        LanguageList.add("字母砂板游戏");
        LanguageList.add("复述故事");
        LanguageList.add("看图说句子");
        LanguageList.add("创编故事");
        LanguageList.add("用固定词编故事");
        LanguageList.add("童谣");
        LanguageList.add("讲故事");
    }

    public static void initMathematicsData() throws Exception {
        //口语和听力的开发
        //在这个层次有很多工作可以做：聊天、讲故事、诗歌、童谣……

        //数字认读
        MathematicsList.add("数棒");
        MathematicsList.add("数字砂板");
        MathematicsList.add("数棒和数字的结合");
        MathematicsList.add("纺锤棒盒");
        MathematicsList.add("数字与筹码");
        MathematicsList.add("物体篮（实物/数字)");
        MathematicsList.add("数字游戏");
        MathematicsList.add("塞根板1");
        MathematicsList.add("塞根板2");
        MathematicsList.add("平方链");
        MathematicsList.add("立方链");
        MathematicsList.add("加法蛇游戏");
        MathematicsList.add("彩色串珠盒");
        MathematicsList.add("划卡游戏");
        MathematicsList.add("凑十游戏");
        MathematicsList.add("兑换游戏");
        MathematicsList.add("取量游戏");
        MathematicsList.add("分数小人");
        MathematicsList.add("邮票游戏");
        MathematicsList.add("手指板游戏");
    }

    public static void initCultureData() throws Exception {
        CultureList.add("水陆模型");
        CultureList.add("马的拼图");
        CultureList.add("花的拼图");
        CultureList.add("冷热分类卡");
        CultureList.add("左右分类卡");
        CultureList.add("砂纸地球仪");
        CultureList.add("彩色地球仪");
        CultureList.add("世界地图");
        CultureList.add("认识宇宙");
        CultureList.add("介绍太阳系");
        CultureList.add("制作太阳系");
        CultureList.add("制作自己想象的星系");
        CultureList.add("地球内部结构介绍");
        CultureList.add("介绍太阳");
        CultureList.add("介绍月球");
        CultureList.add("人造卫星的介绍");
        CultureList.add("自己制作人造卫星");
        CultureList.add("介绍木星");
        CultureList.add("介绍土星");
    }

    public static void initObservationData() throws Exception {
        ObservationList.add("");
    }


    public static void genData(List<Student> stdInfos, String target) throws Exception {
        String filePath = "/Users/shimanqiang/Downloads";
        //filePath = "E:\\meituan\\demo\\anna-biz\\src\\main\\resources";
        filePath = "E:\\meituan\\study-demo\\src\\main\\resources";
        String fileName = "主班教学双周计划.pdf";
        String targetFileName = target + ".pdf";

        File pdfFile = new File(filePath, fileName);
        PDDocument document = PDDocument.load(pdfFile);

        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(FONT_PATH);
        PDFont font = PDType0Font.load(document, inputStream);

//        int numberOfPages = document.getNumberOfPages();
//        System.out.println(numberOfPages);

//        PDFTextStripper textStripper = new PDFTextStripper();
//        System.out.println(textStripper.getText(document));


        PDPage page = document.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);

        //月份
//        contentStream.beginText();
//        contentStream.setFont(font, 12);
//        contentStream.newLineAtOffset(25, 802);
//        contentStream.showText("3");
//        contentStream.endText();

        //周
//        contentStream.beginText();
//        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
//        contentStream.newLineAtOffset(80, 802);
//        contentStream.showText("1 , 2");
//        contentStream.endText();

        //班级
        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.newLineAtOffset(475, 802);
        contentStream.showText("蛋白石");
        contentStream.endText();


        int group = 5;
        int lines = 7;
        int cells = 5;

        int tx = 112;
        int ty = 772;
        //group ==> one student
        for (int i = 0; i < group; i++) {
            if (i >= stdInfos.size()) {
                break;
            }
            Student student = stdInfos.get(i);
            //line
            for (int j = 0; j < lines; j++) {
                List<String> lineData = new ArrayList<>();
                if (j == 0) {
                    lineData = new ArrayList<>();
                    lineData.add(student.getName());
                    lineData.add("");
                    lineData.add("");
                    lineData.add(student.getAge() + "岁");
                    lineData.add("");
                }
                if (j == 1) {
                    lineData = student.getPracticalLifeList();
                }
                if (j == 2) {
                    lineData = student.getSensorialList();
                }
                if (j == 3) {
                    lineData = student.getLanguageList();
                }
                if (j == 4) {
                    lineData = student.getMathematicsList();
                }
                if (j == 5) {
                    lineData = student.getCultureList();
                }
                if (j == 6) {
                    lineData = student.getObservationList();
                }
                if (lineData == null || lineData.size() < cells) {
                    int gapData = cells - lineData.size();
                    for (int w = 0; w < gapData; w++) {
                        lineData.add("");
                    }
                }

                //offset
                int txTmp = tx;
                //cell
                for (int k = 0; k < cells; k++) {
                    contentStream.beginText();
                    contentStream.setFont(font, 10);//font:12
                    contentStream.newLineAtOffset(txTmp, ty);
                    String data = lineData.get(k);
                    if (data != null && data.length() > 0) {
                        if (data.length() > 6) {
                            contentStream.setFont(font, 8);
                        }
                        if (data.length() > 12) {
                            contentStream.setFont(font, 7);
                        }
                        if (data.length() > 17) {
                            contentStream.setFont(font, 5);
                        }
                        contentStream.showText(data);
                    }
                    contentStream.endText();

                    txTmp = txTmp + 98;
                }
                ty = ty - 16;
            }
            ty = ty - 39;
        }


        //关闭内容流
        contentStream.close();

        //修改后保存的路径
        document.save(new File(filePath, targetFileName));
        document.close();
    }
}
