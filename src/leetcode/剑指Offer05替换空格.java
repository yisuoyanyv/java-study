package leetcode;

/**
 * https://leetcode-cn.com/leetbook/read/illustrate-lcof/xz2cf5/
 *
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 */
public class 剑指Offer05替换空格 {
    //思路： 遍历，先确定空格的个数,若个数为n，则申请  原来空间+3n的数组
    //      倒序遍历，遇到空格，则向新数组中添加 %20,
    //
    public String replaceSpace(String s) {
        char[] chars = s.toCharArray();

        int blankCount = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == ' '){
                blankCount++;
            }
        }

        char[] newChars = new char[chars.length+2*blankCount];
        for (int j = newChars.length-1,k = chars.length - 1 ; j >= 0; ) {
            if(chars[k] == ' '){
                newChars[j--]= '0';
                newChars[j--]= '2';
                newChars[j--]= '%';
                k--;
            }else {
                newChars[j--] = chars[k--];
            }

        }
        return String.valueOf(newChars);
    }

    public static void main(String[] args) {
        String s = "We are happy.";
        System.out.println(new 剑指Offer05替换空格().replaceSpace(s));

    }

}
