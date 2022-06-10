package algorithm;

import java.util.Scanner;

/**
 * @author VarleyT
 * @date 2022/6/10 18:23
 */
public class KMP {
    // 最长相等前后缀表
    private static int[] next;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.next();
        String pattern = input.next();
        getNext(pattern);

        int index = 0;
        int i;
        for (i = 0; i < str.length(); i++) {
            //已全部匹配完成
            //此时i的值为str匹配字符 下一位的下标
            if(index == pattern.length()){
                break;
            }

            //不匹配
            while (index > 0 && str.charAt(i) != pattern.charAt(index)) {
                index = next[index - 1];
            }
            //当前字符匹配，匹配下一位
            if (str.charAt(i) == pattern.charAt(index)) {
                index++;
            }
        }
        //resultIndex值为匹配到的字符串首位下标
        int resultIndex = i - pattern.length();
        System.out.println(resultIndex);

        input.close();
    }

    // 获取Next数组
    private static void getNext(String pattern) {
        next = new int[pattern.length()];
        int j = 0;
        next[0] = 0;
        // i为后缀末尾下标 j为前缀末尾下标
        for (int i = 1; i < pattern.length(); i++) {
            // 前缀和后缀不相等
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            // 前缀和后缀相等
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
    }
}
