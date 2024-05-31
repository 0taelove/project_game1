package org.yeongtae.main.controllers;

import org.yeongtae.global.AbstractController;
import org.yeongtae.global.constants.MainMenu;
import org.yeongtae.template.Templates;

/**
 * 메인 컨트롤러
 *
 */
public class MainController extends AbstractController {
    @Override
    public void show() {


        Templates.getInstance().render(MainMenu.MAIN);
    }
}