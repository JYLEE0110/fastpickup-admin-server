package org.fktm.fastpickup.member.mapper;

import org.fktm.fastpickup.member.dto.MemberReadDTO;
import org.fktm.fastpickup.member.dto.MemberRegistDTO;
import org.fktm.fastpickup.member.mappers.MemberMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberMapperTests {

    @Autowired(required = false)
    MemberMapper memberMapper;

    private static final String TEST_MEMBER_ID = "admin";
    private static final String TEST_MEMBER_PW = "1234";
    private static final String TEST_MEMBER_NAME = "이주용";
    private static final String TEST_MEMBER_ADDR = "경기도 성남시 분당구 청구블루빌";
    private static final String TEST_MEMBER_PHONE_NUM = "010-5420-xxxx";

    private MemberRegistDTO memberRegistDTO;

    @BeforeEach
    public void init(){
        memberRegistDTO = MemberRegistDTO.builder()
                        .memberID(TEST_MEMBER_ID)
                        .memberPW(TEST_MEMBER_PW)
                        .memberName(TEST_MEMBER_NAME)
                        .memberAddr(TEST_MEMBER_ADDR)
                        .memberPhoneNum(TEST_MEMBER_PHONE_NUM)
                        .build();
    }

    @DisplayName("회원가입 매퍼 테스트")
    @Test
    @Transactional
    public void registMember(){

        // GIVEN
        log.info("===== Start RegistMember Test =====");

        // WHEN
        memberMapper.registMember(memberRegistDTO);
        // 등록 후 selectKey로 ID값 추출
        String id = memberRegistDTO.getMemberID();

        // THEN
        // selectKey로 추출한 ID 값과 테스트값 상수로 등록한 Id값이 같아야한다.
        log.info("===== id =====");
        log.info(id);
        Assertions.assertEquals(id, TEST_MEMBER_ID);
        log.info("===== END RegistMemeber Test =====");
    }

    @DisplayName("회원 상세보기 테스트")
    @Test
    @Transactional
    public void readMember(){

        // GIVEN
        log.info("===== Start ReadMember Test =====");

        // WHEN
        MemberReadDTO dto = memberMapper.readMember(TEST_MEMBER_ID);

        // THEN
        log.info("===== memberReadDTO =====");
        log.info(dto);
        Assertions.assertNotNull(dto, "등록되지 않은 회원 입니다.");
        log.info("===== END ReadMemeber Test =====");

    }
    
}
