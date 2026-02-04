//main
package com.hospital.management;
import com.hospital.management.menu.Menu;
import com.hospital.management.menu.MenuManagment;
public class Main {
    public static void main(String[] args) {
        Menu menu = new MenuManagment();
        menu.run();
    }
}