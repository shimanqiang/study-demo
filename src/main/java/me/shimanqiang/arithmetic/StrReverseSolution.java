package me.shimanqiang.arithmetic;

/**
 * 字符串反转
 *
 * @author xxxx
 * @since 2019/8/7 14:28
 */
public class StrReverseSolution {

    public static void main(String[] args) {
        System.out.println(reverse2("abcde"));
    }

    public static String reverse2(String str) {
        int len = str.length();
        //空串。方便拼接字符串
        String reverse = "";
        for (int i = 0; i < len; i++) {
            //charArt(int index) 返回指定索引处的字符。
            reverse = str.charAt(i) + reverse;
        }
        return reverse;
    }

    private static String reverse1(String s) {
        char[] chars = s.toCharArray();

        int start = 0;
        int last = chars.length - 1;

        while (start < last) {
            char tmp = chars[start];
            chars[start] = chars[last];
            chars[last] = tmp;

            start++;
            last--;
        }

        return new String(chars);
    }
}
