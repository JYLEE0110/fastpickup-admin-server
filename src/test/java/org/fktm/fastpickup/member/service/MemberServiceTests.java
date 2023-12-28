package org.fktm.fastpickup.member.service;

import org.fktm.fastpickup.member.dto.MemberListDTO;
import org.fktm.fastpickup.member.dto.MemberReadDTO;
import org.fktm.fastpickup.member.dto.MemberRegistDTO;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.fktm.fastpickup.util.page.PageResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberServiceTests {

    @Autowired(required = false)
    private MemberService memberService;

    private static final String TEST_MEMBER_ID = "admin";
    private static final String TEST_MEMBER_PW = "1234";
    private static final String TEST_MEMBER_PW_CHECK = "1234";
    private static final String TEST_MEMBER_NAME = "이주용";
    private static final String TEST_MEMBER_ADDR = "경기도 성남시 분당구 청구블루빌";
    private static final String TEST_MEMBER_PHONE_NUM = "010-5420-xxxx";

    // 검색 조건
    private static final String TEST_MEMBER_TYPE = "i";
    private static final String TEST_MEMBER_KEYWORD = "a";

    private MemberRegistDTO memberRegistDTO;
    private MemberReadDTO memberReadDTO;
    private PageRequestDTO pageRequestDTO;
    private PageResponseDTO<MemberListDTO> memberList;

    @BeforeEach
    public void init(){

        pageRequestDTO = PageRequestDTO.builder()
        .type(TEST_MEMBER_TYPE)
        .keyword(TEST_MEMBER_KEYWORD)
        .build();

        memberRegistDTO = MemberRegistDTO.builder()
                        .memberID(TEST_MEMBER_ID)
                        .memberPW(TEST_MEMBER_PW)
                        .checkedMemeberPw(TEST_MEMBER_PW_CHECK)
                        .memberName(TEST_MEMBER_NAME)
                        .memberAddr(TEST_MEMBER_ADDR)
                        .memberPhoneNum(TEST_MEMBER_PHONE_NUM)
                        .build();

    }

    @DisplayName("회원가입 서비스 테스트")
    @Test
    // @Transactional
    public void registMember(){

        //Given
        log.info("===== Start RegistMemberService =====");

        //WHEN
        memberService.registMember(memberRegistDTO);

        //THEN
        log.info("===== END RegistMemberService =====");
        
    }

    @DisplayName("회원상세 서비스 테스트")
    @Test
    // @Transactional
    public void readMember(){

        //Given
        log.info("===== Start ReadMemberService =====");

        //WHEN
        memberReadDTO = memberService.ReadMember(TEST_MEMBER_ID);

        //THEN
        log.info(memberReadDTO);
        Assertions.assertEquals(TEST_MEMBER_ID, memberReadDTO.getMemberID());
        Assertions.assertEquals(TEST_MEMBER_ADDR, memberReadDTO.getMemberAddr());
        log.info("===== END ReadMemberService =====");
        
    }

    @DisplayName("회원리스트 서비스 테스트")
    @Test
    // @Transactional
    public void getMemberList(){

        //Given
        log.info("===== Start getMemberList Service =====");

        //WHEN
        memberList = memberService.getMemberList(pageRequestDTO);

        //THEN
        log.info(memberList);
        Assertions.assertNotNull(memberList, "회원 리스트가 비어있습니다.");
        log.info("===== END getMemberList Service =====");
        
    }
    
}
