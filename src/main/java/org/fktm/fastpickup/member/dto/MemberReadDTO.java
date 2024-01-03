package org.fktm.fastpickup.member.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class MemberReadDTO {
    
    private String memberID;                // 회원 ID
    private String memberPW;
    private String memberName;              // 회원 이름
    private String memberAddr;              // 회원 주소
    private String memberPhoneNum;          // 회원 핸드폰 번호
    private LocalDateTime joinDate;         // 회원가입 일자
    private boolean withDrawalStatus;       // 회원 탈퇴여부

}
