package io.spldeolin.bestpractice.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.util.StringUtils;

import lombok.extern.log4j.Log4j2;

/**
 * 任务工具类：用于演示spring-quartz.xml里第一种触发器的功能
 *
 * @author Deolin
 */
@Log4j2

public class AaaaJob {

    public void doJob() {
        log.info("干活aaa");
    }

    public static void main(String[] args) throws Exception {
        FileReader fr = new FileReader("C:\\Users\\Deolin\\Desktop\\User.java");
        BufferedReader br = new BufferedReader(fr);
        String line;
        List<String> lines = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        Collections.reverse(lines);
        for (String l : lines) {
            log.info(l);
        }
        br.close();
        fr.close();
    }

    private static boolean startsWithPulic(String line) {
        line = StringUtils.trimLeadingWhitespace(line);
        if (StringUtils.startsWithIgnoreCase(line, "public")) {
            if (!StringUtils.startsWithIgnoreCase(line, "public class")) {
                return true;
            }
        }
        return false;
    }

    private static boolean isMethodDoc(String line) {
        line = StringUtils.trimLeadingWhitespace(line);
        return StringUtils.startsWithIgnoreCase(line, "/**") || StringUtils.startsWithIgnoreCase(line, "*") ||
                StringUtils.startsWithIgnoreCase(line, "*/");
    }

    private static boolean startsWithReturn(String line) {
        line = StringUtils.trimLeadingWhitespace(line);
        return StringUtils.startsWithIgnoreCase(line, "return");
    }

    private static boolean startsWithThis(String line) {
        line = StringUtils.trimLeadingWhitespace(line);
        return StringUtils.startsWithIgnoreCase(line, "this.");
    }

    private static boolean startsWithEbrace(String line) {
        line = StringUtils.trimLeadingWhitespace(line);
        return StringUtils.startsWithIgnoreCase(line, "}");
    }

}
