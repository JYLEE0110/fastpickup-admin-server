package org.fktm.fastpickup.member.mapper;

import java.util.List;

import org.apache.ibatis.javassist.compiler.ast.Member;
import org.fktm.fastpickup.member.dto.MemberListDTO;
import org.fktm.fastpickup.member.dto.MemberReadDTO;
import org.fktm.fastpickup.member.dto.MemberRegistDTO;
import org.fktm.fastpickup.member.dto.MemberRole;
import org.fktm.fastpickup.member.dto.MemberRoleDTO;
import org.fktm.fastpickup.member.dto.MemberModifyDTO;
import org.fktm.fastpickup.member.mappers.MemberMapper;
import org.fktm.fastpickup.member.mappers.MemberRoleMapper;
import org.fktm.fastpickup.member.page.MemberPageRequestDTO;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberMapperTests {

    @Autowired(required = false)
    MemberMapper memberMapper;

    @Autowired(required = false)
    MemberRoleMapper memberRoleMapper;

    @Autowired(required = false)
    PasswordEncoder passwordEncoder;

    private static final String TEST_MEMBER_ID = "admin";
    private static final String TEST_MEMBER_PW = "1234";
    private static final String TEST_MEMBER_NAME = "이주용";
    private static final String TEST_MEMBER_ADDR = "경기도 성남시 분당구 청구블루빌";
    private static final String TEST_MEMBER_PHONE_NUM = "010-5420-xxxx";

    private static final String TEST_UPDATE_MEMBER_PW = "수정된 비밀번호";
    private static final String TEST_UPDATE_MEMBER_ADDR = "수정된 주소";
    private static final String TEST_UPDATE_MEMBER_PHONE_NUM = "수정된 번호";


    private static final String TEST_MEMBER_TYPE = "i";
    private static final String TEST_MEMBER_KEYWORD = "z";


    private MemberRegistDTO memberRegistDTO;
    private MemberReadDTO memberReadDTO;
    private List<MemberListDTO> memberListDTO;
    private MemberModifyDTO memberModifyDTO;

    private MemberRoleDTO memberRoleDTO; 
    
    private PageRequestDTO pageRequestDTO;
    private MemberPageRequestDTO memberPageRequestDTO;

    @BeforeEach
    public void init(){

        pageRequestDTO = PageRequestDTO.builder()
                        .type(TEST_MEMBER_TYPE)
                        .keyword(TEST_MEMBER_KEYWORD)
                        .build();
        pageRequestDTO = MemberPageRequestDTO.builder()
                        .type(TEST_MEMBER_TYPE)
                        .keyword(TEST_MEMBER_KEYWORD)
                        .build();

        memberRegistDTO = MemberRegistDTO.builder()
                        .memberID(TEST_MEMBER_ID)
                        .memberPW(passwordEncoder.encode(TEST_MEMBER_PW))
                        .memberName(TEST_MEMBER_NAME)
                        .memberAddr(TEST_MEMBER_ADDR)
                        .memberPhoneNum(TEST_MEMBER_PHONE_NUM)
                        .build();
        
        memberModifyDTO = MemberModifyDTO.builder()
                        .memberID(TEST_MEMBER_ID)
                        .memberPW(passwordEncoder.encode(TEST_UPDATE_MEMBER_PW))
                        .memberAddr(TEST_UPDATE_MEMBER_ADDR)
                        .memberPhoneNum(TEST_UPDATE_MEMBER_PHONE_NUM)
                        .build();
        
    }

    @DisplayName("회원가입 매퍼 테스트")
    @Test
    // @Transactional
    public void registMember(){

        // GIVEN
        log.info("===== Start RegistMember Test =====");

        // WHEN
        memberMapper.registMember(memberRegistDTO);
        // 등록 후 selectKey로 ID값 추출
        String id = memberRegistDTO.getMemberID();

        MemberRoleDTO memberRoleDTO = MemberRoleDTO.builder()
                                                .roleName(MemberRole.ROLE_ADMIN.name())
                                                .memberID(id)
                                                .build();
        
        memberRoleMapper.registMemberRole(memberRoleDTO);

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
        memberReadDTO = memberMapper.readMember(TEST_MEMBER_ID);

        // THEN
        log.info("===== memberReadDTO =====");
        log.info(memberReadDTO);
        Assertions.assertNotNull(memberReadDTO, "등록되지 않은 회원 입니다.");
        log.info("===== END ReadMemeber Test =====");

    }

    @DisplayName("회원 리스트 테스트")
    @Test
    @Transactional
    public void getMemberList(){

        // GIVEN
        log.info("===== Start getMemberList Test =====");

        // WHEN
        memberListDTO = memberMapper.getMemberList(pageRequestDTO);

        // THEN
        log.info("===== memberListDTO =====");
        log.info(memberListDTO);
        log.info("===== END getMemberList Test =====");

    }

    @DisplayName("현재 페이지에 대한 회원 수 매퍼 테스트")
    @Test
    @Transactional
    public void getMemberTotal(){

        // GIVEN
        log.info("===== Start getMemberTotal Test =====");

        // WHEN
        Long memberTotal = memberMapper.getTotalMember(pageRequestDTO);

        // THEN
        log.info("===== getMemberTotal =====");
        log.info(memberTotal);
        log.info("===== END getMemberTotal Test =====");

    }

    @DisplayName("회원 탈퇴 매퍼 테스트")
    @Test
    // @Transactional
    public void withdrawMember(){

        // GIVEN
        log.info("===== Start withdrawMember Test =====");

        // WHEN
        int result = memberMapper.withdrawMember(TEST_MEMBER_ID);
        // THEN
        Assertions.assertEquals(1, result, "회원 탈퇴가 이뤄지지 않았습니다.");
        log.info("===== END withdrawMember Test =====");
    }

    @DisplayName("회원 수정 매퍼 테스트")
    @Test
    // @Transactional
    public void updateMapper(){

        // GIVEN
        log.info("===== Start updateMapper Test =====");

        // WHEN
        int result = memberMapper.modifyMember(memberModifyDTO);
        // THEN
        Assertions.assertEquals(1, result, "회원 수정이 이뤄지지 않았습니다.");
        log.info("===== END updateMapper Test =====");
    }
    
}
