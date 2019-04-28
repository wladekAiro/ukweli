package com.ukweli.news.utils;

import org.springframework.util.StringUtils;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class SlugUtil {

    public static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    public static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    public static final Pattern EDGESDHASHES = Pattern.compile("(^-|-$)");

    public SlugUtil(){
        throw new UnsupportedOperationException(" Utility class cannot be instantiated ");
    }

    public static String toSlug(String input) {
        if (StringUtils.isEmpty(input)) {
            return " ";
        }
        String noWhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(noWhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll(" ");
        slug = EDGESDHASHES.matcher(slug).replaceAll(" ");
        return slug.toLowerCase(Locale.ENGLISH);
    }
}
