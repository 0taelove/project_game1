package org.yeongtae.member.mapper;

import org.yeongtae.member.controllers.SearchMember;
import org.yeongtae.member.entities.Member;

import java.util.List;

public interface MemberMapper {
    List<Member> getList(SearchMember search);
    Member get(String userId);
    long getTotal(SearchMember search);
    int exist(String userId);
    int register(Member member);
    int modify(Member member);
    int delete(String userId);
}