package org.yeongtae.main;

import org.yeongtae.game.controllers.GameControllerLocator;
import org.yeongtae.global.Controller;
import org.yeongtae.global.ControllerLocator;
import org.yeongtae.global.Router;
import org.yeongtae.global.constants.MainMenu;
import org.yeongtae.main.controllers.MainController;
import org.yeongtae.member.controllers.MemberControllerLocator;

public class MainRouter implements Router {

    private static Router instance;

    private MainRouter() {}

    public static Router getInstance() {
        if (instance == null) {
            instance = new MainRouter();
        }

        return instance;
    }

    @Override
    public void change(MainMenu mainMenu) {
        ControllerLocator memlocator = MemberControllerLocator.getInstance();
        ControllerLocator gamelocator = GameControllerLocator.getInstance();

        Controller controller = null;
        switch(mainMenu) {
            case JOIN: controller =  memlocator.find(MainMenu.JOIN); break;
            case LOGIN: controller = memlocator.find(MainMenu.LOGIN); break;
            case GAME: controller = gamelocator.find(MainMenu.GAME); break;
            default: controller = new MainController();
        }

        controller.run(); // common(), show(), prompt()
    }

    @Override
    public void start() {
        while(true) {
            change(MainMenu.MAIN); // 첫 화면은 메인 컨트롤러 출력 화면
        }
    }
}
