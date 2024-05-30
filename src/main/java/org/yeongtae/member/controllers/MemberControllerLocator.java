package org.yeongtae.member.controllers;

import org.yeongtae.global.AbstractControllerLocator;
import org.yeongtae.global.Controller;
import org.yeongtae.global.ControllerLocator;
import org.yeongtae.global.Menu;
import org.yeongtae.global.constants.MainMenu;

public class MemberControllerLocator extends AbstractControllerLocator {

    private static ControllerLocator instance;

    private MemberControllerLocator() {}

    public static ControllerLocator getInstance() {
        if (instance == null) {
            instance = new MemberControllerLocator();
        }

        return instance;
    }


    @Override
    public Controller find(Menu menu) {
        Controller controller = controllers.get(menu);
        if (controller != null) {
            return controller;
        }

        MainMenu mainMenu = (MainMenu)menu;

        switch(mainMenu) {
            case JOIN: controller = new JoinController(); break;
            default: controller = new LoginController();
        }

        controllers.put(menu, controller);

        return controller;
    }
}