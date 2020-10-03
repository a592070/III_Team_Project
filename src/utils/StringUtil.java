package utils;

import java.util.Objects;

public class StringUtil {
    public static boolean isEmpty(String str){
        str = String.valueOf(str).trim().toLowerCase();
        return Objects.equals(str,null) || Objects.equals(str,"") || Objects.equals(str,"n/a");
    }
}
