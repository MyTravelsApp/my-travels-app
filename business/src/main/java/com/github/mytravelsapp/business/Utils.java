package com.github.mytravelsapp.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Utility class with generic methods.
 *
 * @author fjtorres
 */
public final class Utils {

    public static final String LARGE_DATE_FORMAT = "EEEE - dd/MM/yyyy";

    public static final String DATE_FORMAT = "dd/MM/yyyy";

    public static String formatLargeDate(final Date pDate) {
        return formatDate(pDate, LARGE_DATE_FORMAT);
    }

    public static String formatDate(final Date pDate, final String format) {
        String result = null;
        if (pDate != null) {
            final SimpleDateFormat sdf = new SimpleDateFormat(format);
            result = sdf.format(pDate);
        }
        return result;
    }

    public static Date parseDate(final String str, final String format) {
        Date result = null;
        if (!isEmpty(str)) {
            final SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                result = sdf.parse(str);
            } catch (final ParseException e) {
                // FIXME ADD LOGGER MESSAGE
            }
        }

        return result;
    }

    public static boolean isEmpty(final String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isEmpty(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static void swap(final List<?> list, final int fromPosition, final int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(list, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(list, i, i - 1);
            }
        }
    }
}
