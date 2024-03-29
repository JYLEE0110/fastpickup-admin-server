package org.fktm.fastpickup.member.mappers;

import java.util.List;

import org.fktm.fastpickup.member.dto.MemberListDTO;
import org.fktm.fastpickup.member.dto.MemberReadDTO;
import org.fktm.fastpickup.member.dto.MemberRegistDTO;
import org.fktm.fastpickup.member.page.MemberPageRequestDTO;
import org.fktm.fastpickup.member.dto.MemberModifyDTO;
import org.fktm.fastpickup.util.page.PageRequestDTO;

public interface MemberMapper {
    
    // 회원가입
    int registMember(MemberRegistDTO memberRegistDTO);

    // 회원 상세
    MemberReadDTO readMember(String memberID);

    // 회원 리스트
    List<MemberListDTO> getMemberList(MemberPageRequestDTO memberPageRequestDTO);
    // 페이징을 위한 현 페이지에 대한 회원 수
    Long getTotalMember(MemberPageRequestDTO memberPageRequestDTO);

    // 회원 탈퇴
    int withdrawMember(String memberID);

    int modifyMember(MemberModifyDTO memberUpdateDTO);

    // 탈퇴 회원 활성화
    int reactivateMember(String memberID);

}
