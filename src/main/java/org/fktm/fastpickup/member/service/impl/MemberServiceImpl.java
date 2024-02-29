package org.fktm.fastpickup.member.service.impl;

import java.util.List;

import org.fktm.fastpickup.exception.customexception.FastPickUpException;
import org.fktm.fastpickup.member.dto.MemberListDTO;
import org.fktm.fastpickup.member.dto.MemberReadDTO;
import org.fktm.fastpickup.member.dto.MemberRegistDTO;
import org.fktm.fastpickup.member.dto.MemberRole;
import org.fktm.fastpickup.member.dto.MemberRoleDTO;
import org.fktm.fastpickup.member.dto.MemberModifyDTO;
import org.fktm.fastpickup.member.exception.enumcode.MemberExceptionCode;
import org.fktm.fastpickup.member.mappers.MemberMapper;
import org.fktm.fastpickup.member.mappers.MemberRoleMapper;
import org.fktm.fastpickup.member.page.MemberPageRequestDTO;
import org.fktm.fastpickup.member.service.MemberService;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.fktm.fastpickup.util.page.PageResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService{

    // RequiredArgsConstructor 의존성 주입
    private final MemberMapper memberMapper;
    private final MemberRoleMapper memberRoleMapper;
    private final PasswordEncoder passwordEncoder;

    // 회원 가입 서비스
    @Override
    public void registMember(MemberRegistDTO memberRegistDTO) {

        log.info("===== registMember Service =====");

        // 입력한 ID 추출
        String memberID = memberRegistDTO.getMemberID();
        // 입력한 ID가 중복인지 확인
        MemberReadDTO existingMember = memberMapper.readMember(memberID);

        // 입력한 PW 추출
        String memberPW = memberRegistDTO.getMemberPW();
        // PW 2차 검증
        String confirmMemberPW = memberRegistDTO.getComfirmMemberPW();

        // 아이디 중복일 시 예외를 던짐
        // existingMember != null이 먼저와야한다. 아닐 시 비어있으면 예외발생
        if(existingMember != null && memberID.equals(existingMember.getMemberID())){
            throw new FastPickUpException(MemberExceptionCode.DUPLICATED_MEMBER_ID);
        }

        // 비밀번호 검증에 실패 했을 시
        if(!memberPW.equals(confirmMemberPW)){
            throw new FastPickUpException(MemberExceptionCode.MISMATCH_PASSWORD);
        }

        // db에 저장 시 암호화 해서 저장
        memberRegistDTO.setMemberPW(passwordEncoder.encode(memberPW));

        memberMapper.registMember(memberRegistDTO);

        // 기본적으로 권한은 USER로 부여한다.
        MemberRoleDTO memberRoleDTO = MemberRoleDTO.builder()
                                        .memberID(memberID)
                                        .roleName(MemberRole.ROLE_USER.name())
                                        .build();

        memberRoleMapper.registMemberRole(memberRoleDTO);

        log.info("===== registMember Service =====");

    }

    // 회원 상세 서비스
    @Override
    public MemberReadDTO ReadMember(String memberID) {

        log.info("===== ReadMember Service =====");
        MemberReadDTO memberReadDTO = memberMapper.readMember(memberID);
        memberReadDTO.setMemberPW(null);
        log.info(memberReadDTO);
        log.info("===== ReadMember Service =====");
        
        return memberReadDTO;

    }

    // 회원 리스트 서비스
    @Override
    public PageResponseDTO<MemberListDTO> getMemberList(MemberPageRequestDTO memberPageRequestDTO ) {

        log.info("===== getMemberList Service =====");

        // 현재 페이지에 대한 회원 리스트를 가져온다.
        List<MemberListDTO> memberList = memberMapper.getMemberList(memberPageRequestDTO);

        // 현재 페이지에 대한 총 회원 수(페이징 계산에 필요함)
        Long memberTotal = memberMapper.getTotalMember(memberPageRequestDTO);

        log.info("===== getMemberList Service =====");

        return PageResponseDTO.<MemberListDTO>withAll()
                            .list(memberList)
                            .total(memberTotal)
                            .pageRequestDTO(memberPageRequestDTO)
                            .build();

    }

    // 회원 탈퇴 서비스
    @Override
    public void withdrawalMember(String memberID) {

        log.info("===== withdrawalMember Service =====");

        MemberReadDTO memberReadDTO = memberMapper.readMember(memberID);
        
        // 회원 탈퇴 전 존재하는 회원인지 체크 후 탈퇴, 존재하지 않는 회원일시 예외를 던진다.
        if(memberReadDTO != null && memberReadDTO.getMemberID().equals(memberID)){

            memberMapper.withdrawMember(memberID);
        }else{
            throw new FastPickUpException(MemberExceptionCode.NOT_EXCIST_MEMBER);
        }


        log.info("===== withdrawalMember Service =====");

    }

    // 회원 수정 서비스
    @Override
    public void modifyMember(MemberModifyDTO memberModifyDTO) {

        log.info("===== updateMember Service =====");

        // 입력한 PW 추출
        String memberPW = memberModifyDTO.getMemberPW();
        // PW 2차 검증
        String comfirmMemberPW = memberModifyDTO.getComfirmMemberPW();

        // 비밀번호 검증에 실패 했을 시
        if(!memberPW.equals(comfirmMemberPW)){
            throw new FastPickUpException(MemberExceptionCode.MISMATCH_PASSWORD);
        }
        memberModifyDTO.setMemberPW(passwordEncoder.encode(memberPW));

        memberMapper.modifyMember(memberModifyDTO);

        log.info("===== updateMember Service =====");

    }

    // 탈퇴 회원 재활성화
    @Override
    public void reactivateMember(String memberID) {

        log.info("===== reactivateMember Service =====");
        
        memberMapper.reactivateMember(memberID);
        
        log.info("===== reactivateMember Service =====");
    }
    
}
