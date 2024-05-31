package org.yeongtae.member.controllers;

import org.yeongtae.global.AbstractController;
import org.yeongtae.global.Router;
import org.yeongtae.global.Service;
import org.yeongtae.global.constants.MainMenu;
import org.yeongtae.main.MainRouter;
import org.yeongtae.member.services.MemberServiceLocator;
import org.yeongtae.template.Templates;

/**
 * 로그인 컨트롤러
 *
 */
public class LoginController extends AbstractController {

    @Override
    public void show() {

        Templates.getInstance().render(MainMenu.LOGIN);
    }

    @Override
    public void prompt() {
        String userId = promptWithValidation("아이디: ", s -> !s.isBlank());
        String userPw = promptWithValidation("비밀번호: ", s -> !s.isBlank());

        RequestLogin form = RequestLogin.builder()
                .userId(userId)
                .userPw(userPw)
                .build();


        // 로그인 처리 ...
        Router router = MainRouter.getInstance();
        try {

            Service service = MemberServiceLocator.getInstance().find(MainMenu.LOGIN);
            service.process(form);

            router.change(MainMenu.MAIN); // 로그인 성공시 -> 메인페이지
        } catch (RuntimeException e) {

            System.err.println(e.getMessage());
            router.change(MainMenu.LOGIN); // 로그인 실패시 -> 로그인 페이지
        }

    }
}