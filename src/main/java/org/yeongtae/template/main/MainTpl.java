package org.yeongtae.template.main;

import org.yeongtae.member.MemberSession;
import org.yeongtae.member.entities.Member;
import org.yeongtae.template.Template;

public class MainTpl implements Template {
    @Override
    public String getTpl() {
        StringBuffer sb = new StringBuffer(2000);

        sb.append("\n< 묵찌빠 게임 >\n"+"\n")
            .append("메뉴를 선택하세요.\n");
        if (MemberSession.isLogin()) {
            Member member = MemberSession.getMember();
            sb.append(String.format("%s(%s)님 로그인\n", member.getUserNm(), member.getUserId()));
            sb.append("1. 게임하기\n");
            sb.append("2. 로그아웃\n");
        } else {
            sb.append("1. 회원가입\n");
            sb.append("2. 로그인\n");
        }
        sb.append("--------------------------------\n");

        return sb.toString();
    }
}