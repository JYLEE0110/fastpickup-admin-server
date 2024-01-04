package org.fktm.fastpickup.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberRoleDTO {
    
    private Long roleID;        // PK
    private String roleName;    // 권한이름
    private String memberID;    // fk 회원 아이디

}
