package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateUtil {
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final DateFormat FORMATTER = new SimpleDateFormat(DEFAULT_PATTERN);
}
