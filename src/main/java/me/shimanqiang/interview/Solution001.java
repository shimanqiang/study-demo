package me.shimanqiang.interview;

/**
 * @author smq
 * @since 2019/5/29 11:04
 */
public class Solution001 {
    /**
     * 题目: 两个线程分别打印26个英文字母的元音（a,e,i,o,u）和辅音（其他）,按字母序输出
     * 考察点：线程并发问题
     */
    static char[] vowel = new char[]{'a', 'e', 'i', 'o', 'u'};
    static char[] consonant = new char[]{'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n',
            'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'};

    public static void main(String[] args) throws Exception {
        new WorkThread(vowel).start();
        new WorkThread(consonant).start();
    }
}

class WorkThread extends Thread {
    private static volatile char next = 'a';
    char[] data;
    int index = 0;

    public WorkThread(char[] data) {
        this.data = data;
    }

    @Override
    public void run() {
        while (data.length > index) {
            char tmp = data[index];
            if (next == tmp) {
                System.out.println(next);
                index++;
                next++;
            }
        }
    }
}



