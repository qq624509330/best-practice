package io.spldeolin.bestpractice.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * 代码修剪器，对通过代码生成器生成的Model、Mapper、Service、Controller代码，进行简化
 * <p>
 * 【0】最好，只用这个工具来处理那些由CodeGenerator刚生成的model，做过一定修改的model会出现一些问题。
 */
public class CodeTrimmer {

    /**
     * 【1】model类所在包的绝对路径
     */
    private static final String MODEL_PACKAGE_ABSOLUTE_PATH =
            "C:\\java-development\\idea-products\\best-practice\\src\\main\\java\\io\\spldeolin\\bestpractice\\po";

    public static void main(String[] args) {
        /**
         * 【2】需要被处理的model类名，不填则处理所有
         */
        trimModels();
    }

    private static void trimModels(String... targetFilenames) {
        List<String> filenames = Arrays.asList(targetFilenames);
        // 遍历MODEL_PACKAGE_ABSOLUTE_PATH下所有文件
        File folder = new File(MODEL_PACKAGE_ABSOLUTE_PATH);
        List<File> files = new ArrayList<>();
        interceptFiles(files, folder);
        for (File file : files) {
            if (filenames.size() != 0 && !filenames.contains(fileName(file))) {
                continue;
            }
            try {
                trimOneModel(file);
            } catch (Exception ignored) {
            }
        }
    }

    private static void interceptFiles(List<File> files, File folder) {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                interceptFiles(files, file);
            } else if ("java".equals(fileExtension(file))) {
                files.add(file);
            } else {
                // nothing
            }
        }
    }

    private static void trimOneModel(File file) throws Exception {
        // 读java文件
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        List<String> inputLines = new ArrayList<>();
        String tempLine;
        while ((tempLine = br.readLine()) != null) {
            inputLines.add(tempLine);
        }
        br.close();
        fr.close();
        // 读完毕，开始处理
        Collections.reverse(inputLines);
        List<String> outputLines = new ArrayList<>();
        boolean meetPublicClassEver = false;
        boolean meetImportEver = false;
        outputLines.add("}");
        for (String inputLine : inputLines) {
            // public class
            if (StringUtils.trimToEmpty(inputLine).startsWith("public class")) {
                outputLines.add("");
                outputLines.add("    private static final long serialVersionUID = 1L;");
                outputLines.add(addImplements(inputLine));
                addTableAnnouncement(inputLine, outputLines);
                addClassAnnotation(outputLines);
                addImport(outputLines);
                meetPublicClassEver = true;
                continue;
            }
            // @Table
            if (StringUtils.trimToEmpty(inputLine).startsWith("@Table")) {
                // 遇到@Table则不要
                continue;
            }
            // Getter, Setter 方法签名与方法体
            if (isMethodSignOrBody(inputLine)) {
                continue;
            }
            // 如果ignoreJavaDoc未被改变过（遇到类声明之前），且第一次遇到private，则都不再处理JavaDoc
            if (!meetPublicClassEver) {
                String lineContent = StringUtils.trimToEmpty(inputLine);
                if (!lineContent.startsWith("private") && !lineContent.startsWith("*/") &&
                        !lineContent.startsWith("*") && !lineContent.startsWith("/**") &&
                        !lineContent.equals("")) {
                    throw new RuntimeException("java文件中存在非getter、setter方法，跳过处理。");
                }
                if (lineContent.startsWith("private")) {
                    meetPublicClassEver = true;
                }
            }
            // Getter, Setter 方法注释
            if (!meetPublicClassEver && isJavaDoc(inputLine)) {
                continue;
            }
            outputLines.add(inputLine);
        }
        Collections.reverse(outputLines);
        // 处理完毕，开始写。
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for (String outputLine : outputLines) {
            bw.write(outputLine + System.getProperty("line.separator"));
        }
        bw.close();
        fw.close();
    }

    private static boolean isMethodSignOrBody(String line) {
        line = StringUtils.trimToEmpty(line);
        // 如果以public而不是public class开头
        if (StringUtils.startsWithIgnoreCase(line, "public")) {
            if (!StringUtils.startsWithIgnoreCase(line, "public class")) {
                return true;
            }
        }
        // 如果是return语句
        if (StringUtils.startsWithIgnoreCase(line, "return")) {
            return true;
        }
        // 如果是this.运算符
        if (StringUtils.startsWithIgnoreCase(line, "this.")) {
            return true;
        }
        // 如果是右大括号
        if (StringUtils.startsWithIgnoreCase(line, "}")) {
            return true;
        }
        return false;
    }

    private static boolean isJavaDoc(String line) {
        line = StringUtils.trimToEmpty(line);
        // 如果是JavaDOC
        if (StringUtils.startsWithIgnoreCase(line, "/**") || StringUtils.startsWithIgnoreCase(line, "*") ||
                StringUtils.startsWithIgnoreCase(line, "*/")) {
            return true;
        }
        return false;
    }

    private static void addClassAnnotation(List<String> lines) {
        lines.add("@NoArgsConstructor");
        lines.add("@EqualsAndHashCode");
        lines.add("@ToString");
        lines.add("@Setter");
        lines.add("@Getter");
    }

    private static void addImport(List<String> lines) {
        lines.add("import lombok.ToString;");
        lines.add("import lombok.Setter;");
        lines.add("import lombok.NoArgsConstructor;");
        lines.add("import lombok.Getter;");
        lines.add("import lombok.EqualsAndHashCode;");
        lines.add("import java.io.Serializable;");
    }

    private static String addImplements(String line) {
        line = StringUtils.stripEnd(line, "{");
        line += "implements Serializable {";
        return line;
    }

    private static void addTableAnnouncement(String line, List<String> lines) {
        String className = StringUtils.trimToEmpty(line).replace("public class ", "").replace(" {", "");
        String tableAnnouncement = "@Table(name = \"" + camelToUnderline(className) + "\")";
        lines.add(tableAnnouncement);
    }

    private static String fileName(File file) {
        String fileFullName = file.getName();
        return fileFullName.substring(0, fileFullName.lastIndexOf("."));
    }

    private static String fileExtension(File file) {
        String fileFullName = file.getName();
        return fileFullName.substring(fileFullName.lastIndexOf(".") + 1);
    }

    private static String camelToUnderline(String camelCaseName) {
        StringBuilder result = new StringBuilder();
        if (camelCaseName != null && camelCaseName.length() > 0) {
            result.append(camelCaseName.substring(0, 1).toLowerCase());
            for (int i = 1; i < camelCaseName.length(); i++) {
                char ch = camelCaseName.charAt(i);
                if (Character.isUpperCase(ch)) {
                    result.append("_");
                    result.append(Character.toLowerCase(ch));
                } else {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }

}
