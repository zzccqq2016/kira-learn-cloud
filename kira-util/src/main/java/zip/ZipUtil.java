package zip;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @author: Zhang Chaoqing
 * @date: 2023/2/7 22:25
 */
public class ZipUtil {


    /**
     * <a href="https://blog.csdn.net/shiyuezhong/article/details/79996123">...</a>
     * 解压zip压缩文件到指定目录
     *
     * @param zipPath zip压缩文件绝对路径
     * @param descDir 指定的解压目录
     */
    public static void unzipWithPath(String zipPath, String descDir) throws IOException {
        try {
            File zipFile = new File(zipPath);
            if (!zipFile.exists()) {
                throw new IOException("要解压的压缩文件不存在");
            }
            File pathFile = new File(descDir);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
            InputStream input = Files.newInputStream(Paths.get(zipPath));
            unzipWithStream(input, descDir);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    /**
     * 解压
     *
     * @param inputStream zip文件输入流
     * @param descDir     解压输出文件
     */
    public static void unzipWithStream(InputStream inputStream, String descDir) {
        if (!descDir.endsWith(File.separator)) {
            descDir = descDir + File.separator;
        }
        try (ZipInputStream zipInputStream = new ZipInputStream(inputStream, Charset.forName("GBK"))) {
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                String zipEntryNameStr = zipEntry.getName();
                String zipEntryName = zipEntryNameStr;
                if (zipEntryNameStr.contains("/")) {
                    String str1 = zipEntryNameStr.substring(zipEntryNameStr.indexOf("/")+1);
                    zipEntryName = zipEntryNameStr.substring(0,str1.length() + 1);
                }
                String outPath = (descDir + zipEntryName).replace("\\\\", "/");
                File outFile = new File(outPath.substring(0, outPath.lastIndexOf('/')));
                if (!outFile.exists()) {
                    outFile.mkdirs();
                }
                if (new File(outPath).isDirectory()) {
                    continue;
                }
                File file = new File(outPath);
                writeFile(new BufferedOutputStream(Files.newOutputStream(file.toPath())), zipInputStream);
                file.setLastModified(zipEntry.getTime());
                zipInputStream.closeEntry();
            }
            System.out.println("======解压成功=======");
        } catch (IOException e) {
            System.out.println("压缩包处理异常，异常信息{}" + e);
        }
    }

    /**
     * 将流写到文件中
     *
     * @param zipInputStream zipInputStream
     */
    public static void writeFile(OutputStream outputStream, ZipInputStream zipInputStream) {
        try {
            byte[] bytes = new byte[4096];
            int len;
            while ((len = zipInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
        } catch (IOException ex) {
            System.out.println("解压文件时，写出到文件出错");
        }
    }

    public static void main(String[] args) throws Exception {

        String zipPath = "D:/test/test.zip";
        String descDir = "D:/test/";

        unzipWithPath(zipPath, descDir);
//        int i = unzipFiles(new File(zipPath), new File(descDir));
    }

    public static int unzipFiles(File zipFile, File extractDir) throws Exception {
        int totalFileCount = 0;
        String zipFilePath = zipFile.getPath();

        System.out.println("Zip File Path: " + zipFilePath);

        ZipFile zfile = new ZipFile(zipFile);
        System.out.println("Size of ZipFile: " + zfile.size());

        Enumeration<? extends ZipEntry> entries = zfile.entries();

        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            System.out.println("ZipEntry File: " + entry.getName());
            File file = new File(extractDir, entry.getName());
            if (entry.isDirectory()) {
                System.out.println("Creating Directory");
                file.mkdirs();
                file.setLastModified(entry.getTime());
            } else {
                file.getParentFile().mkdirs();
                file.setLastModified(entry.getTime());
                InputStream in = zfile.getInputStream(entry);
                try {
                    copy(in, Files.newOutputStream(file.toPath()));
                } finally {
                    in.close();
                }
            }
            totalFileCount++;
        }
        return totalFileCount;
    }

    private static void copy(InputStream in, OutputStream out) throws IOException {

        byte[] buffer = new byte[1024];
        System.out.println("InputStream/OutputStram copy");
        while (true) {
            int readCount = in.read(buffer);
            if (readCount < 0) {
                break;
            }
            out.write(buffer, 0, readCount);
        }
    }

}