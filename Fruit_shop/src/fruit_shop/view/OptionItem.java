/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.view;

/**
 *
 * @author TINH HUYNH
 */
public class OptionItem {
    private int index;
    private String name;

    public OptionItem(int index, String name) {
        this.index = index;
        this.name = name;
    }
    
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
