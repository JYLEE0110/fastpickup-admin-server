package org.fktm.fastpickup.member.service;

import org.fktm.fastpickup.member.dto.MemberListDTO;
import org.fktm.fastpickup.member.dto.MemberReadDTO;
import org.fktm.fastpickup.member.dto.MemberRegistDTO;
import org.fktm.fastpickup.member.dto.MemberModifyDTO;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.fktm.fastpickup.util.page.PageResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {

    // 회원 가입
    void registMember(MemberRegistDTO memberRegistDTO);

    // 회원 상세
    MemberReadDTO ReadMember(String memberID);

    // 회원 리스트
    PageResponseDTO<MemberListDTO> getMemberList(PageRequestDTO pageRequestDTO);

    // 회원 탈퇴
    void withdrawalMember(String memberID);

    // 회원 수정
    void modifyMember(MemberModifyDTO memberUpdateDTO);
    
}
