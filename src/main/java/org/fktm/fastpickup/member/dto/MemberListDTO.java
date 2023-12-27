package org.fktm.fastpickup.member.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class MemberListDTO {

    private String memberID;                // 회원 ID
    private String memberName;              // 회원 이름
    private String memberAddr;              // 회원 주소
    private String memberPhoneNum;          // 회원 핸드폰 번호
    private LocalDateTime joinDate;         // 회원가입 일자
    
}
