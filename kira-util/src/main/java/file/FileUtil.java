package file;

import regex.RegexUtil;

import java.util.Objects;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/2/22 17:13
 */
public class FileUtil {


    /**
     * 将输入的字符串中除数字、字母和点号以外的所有字符映射成@+编码值的形式。
     * 中文字符也会被映射。
     * <p>1.判断中文 字符映射</p>
     * <p>2.在 Unicode 中，中文字符属于“CJK Unified Ideographs”，也就是“中日韩统一表意文字”，其中的汉字都被认为是字母,所以中文需要提前判断</p>
     *
     * @param input 需要处理的字符串
     * @return 处理后的字符串
     */
    public static String convertNonAlphanumericToHex(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (RegexUtil.isChinese(c)) {
                sb.append("@").append(Integer.toHexString(c));
            } else if (Character.isDigit(c) || Character.isLetter(c) || Objects.equals(c, '.')) {
                sb.append(c);
            } else {
                sb.append("@").append(String.format("%04x", (int) c));
            }
        }
        return sb.toString();
    }


    /**
     * 反解析 {@link #convertNonAlphanumericToHex(String)} 处理过的字符串
     *
     * @param input 需要处理的字符串
     * @return 处理后的字符串
     */
    public static String convertHexToNonAlphanumeric(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Objects.equals(c, '@')) {
                String code = input.substring(i + 1, i + 5);
                try {
                    sb.appendCodePoint(Integer.parseInt(code, 16));
                } catch (NumberFormatException e) {
                    sb.append('@').append(code);
                }
                i += 4;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}