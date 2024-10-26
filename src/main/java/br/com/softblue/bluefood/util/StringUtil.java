package br.com.softblue.bluefood.util;

import java.util.Collection;
import java.util.stream.Collectors;

public class StringUtil {

    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }

        return str.trim().isEmpty();
    }

    public static String concatenate(Collection<String> strs) {
        if (strs == null || strs.size() == 0) {
            return null;
        }

        return String.join(", ", strs);
    }

}
