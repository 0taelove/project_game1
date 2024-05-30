package org.yeongtae.game.mapper;

import org.apache.ibatis.session.SqlSession;
import org.yeongtae.game.entities.PointLog;
import org.yeongtae.game.entities.Rank;
import org.yeongtae.global.Service;
import org.yeongtae.global.configs.DBConn;
import org.yeongtae.global.constants.MainMenu;
import org.yeongtae.member.controllers.RequestJoin;
import org.yeongtae.member.entities.Member;
import org.yeongtae.member.mapper.MemberMapper;
import org.yeongtae.member.services.MemberServiceLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PointLogMapperTest {

    private MemberMapper memberMapper;
    private Service<RequestJoin> joinService;
    private PointLogMapper mapper;
    private List<Member> members;

    @BeforeEach
    void init() {
        SqlSession session = DBConn.getSession();
        mapper = session.getMapper(PointLogMapper.class);
        memberMapper = session.getMapper(MemberMapper.class);
        joinService = MemberServiceLocator.getInstance().find(MainMenu.JOIN);
        members = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            RequestJoin form = RequestJoin.builder()
                    .userId("u" + System.currentTimeMillis())
                    .userPw("12345678")
                    .confirmPw("12345678")
                    .userNm("사용자")
                    .build();
            joinService.process(form);

            Member member = memberMapper.get(form.getUserId());
            members.add(member);
        }

    }

    @Test
    void mapperTest1() {
        for (Member member : members) {
            for (int i = 0; i < 50; i++) {
                int point = (int)(Math.random() * 3 - 1) * 10; // -10, 0, 10

                PointLog item = PointLog.builder()
                        .point(point)
                        .userNo(member.getUserNo())
                        .message("테스트 포인트")
                        .build();

                mapper.register(item);
            } // endfor
        } // endfor
    }

    @Test
    void mapperTest2() {
        List<Rank> ranks = mapper.getRank();
        ranks.forEach(System.out::println);
    }
}