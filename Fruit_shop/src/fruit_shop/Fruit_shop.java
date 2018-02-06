/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop;

import fruit_shop.view.login.LoginForm;

/**
 *
 * @author TINH HUYNH
 */
public class Fruit_shop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LoginForm loginActivity = LoginForm.getInstance();
        loginActivity.print();
    }
    
}
