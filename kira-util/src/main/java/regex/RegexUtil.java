package regex;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/2/22 16:51
 */
public class RegexUtil {


    /**
     * 是否是中文
     *
     * @param input 需要判断的字符
     * @return 判断结果
     */
    public static boolean isChinese(char input) {
        return input >= '\u4e00' && input <= '\u9fa5';
    }

    /**
     * 是否包含中文
     *
     * @param input 需要判断的字符串
     * @return 判断结果
     */
    public static boolean containChinese(String input) {
        return input.matches(".*[\\u4e00-\\u9fa5].*");
    }


}