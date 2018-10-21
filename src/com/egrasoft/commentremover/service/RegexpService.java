package com.egrasoft.commentremover.service;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

public class RegexpService {
    private final String MULTITINE_BLOCK_COMMENT_TYPE_1 = "\\(\\*.*\n.*\\*\\)";
    private final String MULTILINE_BLOCK_COMMENT_TYPE_2 = "\\{.*\n.*}";
    private final String INLINE_BLOCK_COMMENT_TYPE_1 = "\\(\\*.*\\*\\)";
    private final String INLINE_BLOCK_COMMENT_TYPE_2 = "\\{.*}";
    private final String FULL_LINE_COMMENT = "^\\s*//.*$";

    private RegexpService() {
    }

    public String removeComments(String origin) {
        String literalEscaped = stream(origin.split("\n"))
                .map(line -> line.replaceAll("'(.*?)\\(\\*(.*?)\\*\\)(.*?)'", "'$1(&*$2*&)$3'"))
                .map(line -> line.replaceAll("'(.*?)\\{(.*?)}(.*?)'", "'$1(&&$2&&)$3'"))
                .collect(joining("\n"));
        String noBlockComments = literalEscaped
                .replaceAll(MULTITINE_BLOCK_COMMENT_TYPE_1, "\n")
                .replaceAll(MULTILINE_BLOCK_COMMENT_TYPE_2, "\n")
                .replaceAll(INLINE_BLOCK_COMMENT_TYPE_1, "")
                .replaceAll(INLINE_BLOCK_COMMENT_TYPE_2, "");
        String noEmptyLines = stream(noBlockComments.split("\n"))
                .filter(line -> !line.matches(FULL_LINE_COMMENT))
                .collect(joining("\n"));
        return noEmptyLines
                .replaceAll("\\(&\\*", "(*")
                .replaceAll("\\*&\\)", "*)")
                .replaceAll("\\(&&", "{")
                .replaceAll("&&\\)", "}");
    }

    public static RegexpService getInstance() {
        return SingletonHelper.instance;
    }

    private static class SingletonHelper {
        private static final RegexpService instance = new RegexpService();
    }
}
