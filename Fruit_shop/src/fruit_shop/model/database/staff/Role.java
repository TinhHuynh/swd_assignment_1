/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.model.database.staff;

/**
 *
 * @author TINH HUYNH
 */
public enum Role {
    ADMIN(1, "Admin"), CLERK(2, "Clerk");

    private String value;
    private int id;

    Role(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public String value() {
        return value;
    }
    
     public int id() {
        return id;
    }

    public static Role parseRole(String role) {
        switch (role) {
            case "Admin":
                return ADMIN;
            case "Clerk":
                return CLERK;
        }
        return CLERK;

    }

    public static Role parseRole(int id) {
        switch (id) {
            case 1:
                return ADMIN;
            case 2:
                return CLERK;
        }
        return CLERK;
    }
}
