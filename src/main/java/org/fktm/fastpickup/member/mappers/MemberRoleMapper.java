package org.fktm.fastpickup.member.mappers;

import org.fktm.fastpickup.member.dto.MemberRoleDTO;

public interface MemberRoleMapper {
    
    int registMemberRole(MemberRoleDTO memberRoleDTO);  // 권한 생성
    int modifyMemberRole(String memberID);  // 권한 수정

}
