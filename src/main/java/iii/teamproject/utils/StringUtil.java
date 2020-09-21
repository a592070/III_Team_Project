package iii.teamproject.utils;

import java.util.Objects;

public class StringUtil {
    public static boolean isEmpty(String str){
        str = str.trim().toLowerCase();
        return Objects.equals(str,null) || Objects.equals(str,"") || Objects.equals(str,"n/a");
    }

}
