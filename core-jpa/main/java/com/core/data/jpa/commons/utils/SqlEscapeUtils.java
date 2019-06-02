package com.core.data.jpa.commons.utils;

import com.core.commons.util.ArrayUtils;
import com.core.commons.util.StringUtils;

/**
 * @author AGbetrayal
 * @date 2019/5/31 22:29
 */
public class SqlEscapeUtils {
    private static final String[] SQL_KEY_WORD = {"CREATE", "AND", "OR", "USE", "--", ";",
            "INSERT", "UPDATE", "DELETE", "DROP", "ALTER", "GRANT", "EXECUTE", "EXEC"};

    static String escape(String args) {
        return (StringUtils.isEmpty(args) || ArrayUtils.contains(SQL_KEY_WORD, args.trim().toUpperCase()))
                ? null : args;
    }
}
