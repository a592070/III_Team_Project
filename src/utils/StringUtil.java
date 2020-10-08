package utils;

import java.util.Objects;

public class StringUtil {
    public static boolean isEmpty(String str){
        if(Objects.equals(str,null)) return true;
        str = str.trim().toLowerCase();
        return Objects.equals(str,"") || Objects.equals(str,"n/a");
    }
}
