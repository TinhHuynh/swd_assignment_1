/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.utils;

/**
 *
 * @author TINH HUYNH
 */
public class SexUtils {

    public final static int MALE = 1;
    public final static int FEMALE = 0;

    public final static String STR_FEMALE = "Female";
    public final static String STR_MALE = "Male";

    public static String parseToString(int sexNum) {
        if (sexNum == FEMALE) {
            return STR_FEMALE;
        } else {
            return STR_MALE;
        }
    }

    public static String parseToString(boolean isMale) {
        if (!isMale) {
            return STR_FEMALE;
        } else {
            return STR_MALE;
        }
    }
    

}
