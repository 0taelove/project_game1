package org.yeongtae.game.controllers;

import org.yeongtae.game.constants.SubMenu;
import org.yeongtae.global.AbstractController;
import org.yeongtae.global.Controller;
import org.yeongtae.global.ControllerLocator;
import org.yeongtae.global.constants.MainMenu;
import org.yeongtae.main.MainRouter;
import org.yeongtae.template.Templates;

public class GameController extends AbstractController {
    @Override
    public void show() {
        Templates.getInstance().render(MainMenu.GAME);
    }

    @Override
    public void prompt() {
        while(true) {
            System.out.print("메뉴 선택: ");
            String menu = sc.nextLine();
            try {
                int m = Integer.parseInt(menu);
                if (m >= 1 && m <= 4) {
                    change(m);
                    break;
                }
            } catch (Exception e) {
                System.err.println("메뉴 1,2,3,4 중에서 선택하세요.");
            }
        }
    }

    private void change(int menuNo) {
        ControllerLocator locator = GameControllerLocator.getInstance();
        Controller controller = null;
        switch(menuNo) {
            case 1: controller = locator.find(SubMenu.ALONE); break;
            case 2: controller = locator.find(SubMenu.TOGETHER); break;
            case 3: controller = locator.find(SubMenu.RANKING); break;
            default:
                MainRouter.getInstance().change(MainMenu.MAIN);
                return;
        }

        if (controller != null) {
            controller.run();
        }
    }
}