package com.app.blog_api.utils;

public class UrlUtils {
    public static String getUrl(String postTitle) {
        return postTitle.replaceAll("\\s+", "-").toLowerCase();
    }
}
