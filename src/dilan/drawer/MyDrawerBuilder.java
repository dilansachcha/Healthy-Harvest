package punsisi.drawer;

import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.menu.MenuValidation;
import raven.drawer.component.menu.SimpleMenuOption;
import raven.swing.AvatarIcon;
import com.main.dashboard;
import java.awt.Dimension;
import raven.drawer.component.header.SimpleHeader;

public class MyDrawerBuilder extends SimpleDrawerBuilder {

    private dashboard mainDashboard;

    public MyDrawerBuilder(dashboard mainDashboard) {
        this.mainDashboard = mainDashboard;

    }

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        
        return new SimpleHeaderData()
                .setIcon(new AvatarIcon(getClass().getResource("/punsisi/image/profile.png"), 60, 60, 999))
                .setTitle("Punsisi")
                .setDescription("punsisi@gmail.com");
    }

    @Override
    public SimpleMenuOption getSimpleMenuOption() {
        String menus[][] = {
            {"~MAIN~"},
            {"Dashboard"},
            {"Chat"},
            {"Calendar"},
            {"~DEPARTMENTS~"},
            {"HR Deparment", "Employee Managment", "Employee Training", "Employee Attendance","Employee attendace marking","Employee Leave marking", "Payroll"},
            {"Finance Deparment", "Employee Managment", "Employee Training", "Attendance", "Leave Management"},
            {"Marketing Deparment", "Market Research \n" + "and Analysis", "Product and Service \n" + "Attendance", "Customer and \n" + "loyalty Program \n"},
            {"Production Deparment", "Production and stock management", "Procurement Management"},
            {"Storage Deparment", "Damage Product \n"
                + "Management ", "Quality Assurance", "Logistics and Shipping \n"
                + "Management"},
            {"Tourism Experience ", "Ticket Issuing", "Tour guide"},
            {"~OTHER~"},
            {"Theams", "Light Mode", "Dark Mode"},
            {"Logout"}};

        String icons[] = {
            "dashboard.svg",
            "chat.svg",
            "calendar.svg",
            "hr.svg",
            "finance.svg",
            "marketing.svg",
            "production.svg",
            "storage.svg",
            "tour.svg",
            "theam.svg",
            "exit.svg"};

        return new SimpleMenuOption()
                .setMenus(menus)
                .setIcons(icons)
                .setBaseIconPath("punsisi/image")
                .setIconScale(0.45f)
                .addMenuEvent(new MenuEvent() {
                    @Override
                    public void selected(MenuAction action, int index, int subIndex) {
//                        System.out.println("Menu selected " + index + " " + subIndex);

                        // Show jPanel4 and hide jPanel3
                        mainDashboard.loadPanel(index, subIndex);
                        System.out.println("pass");

                    }
                })
                .setMenuValidation(new MenuValidation() {
                    @Override
                    public boolean menuValidation(int index, int subIndex) {
                        if (index == 5) {
                            return false;
                        } else if (index == 7) {
                            return false;
                        } else if (index == 8) {
                            return false;
                        }
                        return true;
                    }

                });

    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData()
                .setTitle("Healthy Harvest")
                .setDescription("Version 1.1.0");
    }

    @Override
    public int getDrawerWidth() {
        return 305;
    }

}
