/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.statistic;

import fruit_shop.Config;
import fruit_shop.utils.DateTimeUtils;
import java.sql.Timestamp;
import java.util.Scanner;

/**
 *
 * @author TINH HUYNH
 */
public class StatisticIOUtils {
    private static Scanner scanner = new Scanner(System.in);
    
    public static Timestamp askAndGetDate() {
        Timestamp timestamp = null;
        String ask = "Date (dd/MM/yyyy)* (if input invalid, automatically get current date): ";
        System.out.print(ask);
        String input = scanner.nextLine();
        timestamp = DateTimeUtils.convertStringToTimeStamp(input,
                Config.NON_TIME_DATE_FORMAT);
        return timestamp;
    }
    
}
