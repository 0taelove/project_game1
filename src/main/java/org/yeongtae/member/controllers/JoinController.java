package org.yeongtae.member.controllers;

import org.yeongtae.global.AbstractController;
import org.yeongtae.global.Router;
import org.yeongtae.global.Service;
import org.yeongtae.global.constants.MainMenu;
import org.yeongtae.main.MainRouter;
import org.yeongtae.member.services.MemberServiceLocator;
import org.yeongtae.template.Templates;

/**
 * 회원 가입 컨트롤러
 *
 */
public class JoinController extends AbstractController {
    @Override
    public void show() {
        Templates.getInstance().render(MainMenu.JOIN);
    }

    @Override
    public void prompt() { // 기본 구현 : 메뉴 구현 -> 필요한 내용 재정의
        String userId = promptWithValidation("아이디(6자리 이상): ", s -> s.length() >= 6);

        String userPw = promptWithValidation("비밀번호(8자리 이상): ", s -> s.length() >= 8);

        String confirmPw = promptWithValidation("비밀번호 확인: ", s -> {
            boolean match = s.equals(userPw);
            if (!match) {
                System.err.println("\n비밀번호가 일치하지 않습니다.");
            }

            return match;
        });

        String userNm = promptWithValidation("회원명: ", s -> !s.isBlank());

        // 사용자가 입력한 데이터를 담아서(controllers), DTO 객체 -> View로 보내기 위해(처리)
        RequestJoin form = RequestJoin.builder()
                .userId(userId)
                .userPw(userPw)
                .confirmPw(confirmPw)
                .userNm(userNm)
                .build();

        Router router = MainRouter.getInstance();
        try {
            // 회원 가입 처리...
            Service service = MemberServiceLocator.getInstance().find(MainMenu.JOIN);
            service.process(form);

            // 회원 가입 성공시 -> 로그인화면 이동
            router.change(MainMenu.LOGIN);
        } catch (RuntimeException e) {
            // 회원가입 실패시 -> 회원가입 화면으로 이동
            System.err.println(e.getMessage());
            router.change(MainMenu.JOIN);
        }
    }
}