/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view.staff;

import fruit_shop.Config;
import fruit_shop.model.database.staff.Staff;
import fruit_shop.model.database.staff.StaffDAO;
import fruit_shop.utils.DateTimeUtils;
import fruit_shop.view.Table;
import java.util.List;

/**
 *
 * @author TINH HUYNH
 */
public class StaffsTable extends Table {

    private static StaffsTable table;

    public static StaffsTable getInstance() {
        if (table == null) {
            table = new StaffsTable();
            table.setParentMenu(StaffMenu.getInstance());
        }
        return table;
    }

    @Override
    public void print() {
        System.out.println("");
        System.out.println("========================== Product Table =============================");
        System.out.println("");
        System.out.println("Loading data. Please wait...");
        System.out.println("");
        List<Staff> staffs = new StaffDAO().getStaffs();
        printStaffs(staffs);
        System.out.println("");
        askGoBack();
    }

    private void printStaffs(List<Staff> staffs) {
        if (!staffs.isEmpty()) {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-10s%-20s%-20s%-20s%-25s%-10s%-20s%-25s%-20s\n",
                    "Id", "Username", "Password", "First name", "Last name", "Role", "Salary", "Join date", "On hired");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            staffs.forEach((staff) -> {
                System.out.printf("%-10d%-20s%-20s%-20s%-25s%-10s%-20.0f%-25s%-20b\n",
                        staff.getId(), staff.getUsername(), staff.getPassword(),
                        staff.getFirstName(), staff.getLastName(),
                        staff.getRole().value(), staff.getSalary(),
                        DateTimeUtils.convertTimestampToString(staff.getJoinDate(), Config.NON_TIME_DATE_FORMAT),
                        staff.isOnWork());
            });
        } else {
            System.out.println("There is no staff in the database. Please add one!");
        }
    }

}
