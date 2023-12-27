package org.fktm.fastpickup.member.mappers;

import java.util.List;

import org.fktm.fastpickup.member.dto.MemberListDTO;
import org.fktm.fastpickup.member.dto.MemberReadDTO;
import org.fktm.fastpickup.member.dto.MemberRegistDTO;
import org.fktm.fastpickup.member.dto.MemberUpdateDTO;
import org.fktm.fastpickup.util.page.PageRequestDTO;

public interface MemberMapper {
    
    // 회원가입
    int registMember(MemberRegistDTO memberRegistDTO);

    // 회원 상세
    MemberReadDTO readMember(String memberID);

    // 회원 리스트
    List<MemberListDTO> getMemberList(PageRequestDTO pageRequestDTO);
    // 페이징을 위한 현 페이지에 대한 회원 수
    Long getTotalMember(PageRequestDTO pageRequestDTO);

    // 회원 탈퇴
    int withdrawMember(String memberID);

    int updateMember(MemberUpdateDTO memberUpdateDTO);

}
