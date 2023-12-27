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
public class MemberDTO {

    @NotBlank(message = "ID 입력은 필수 입니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String memberID;                // 회원 ID

    @NotBlank(message = "PASSWORD 입력은 필수 입니다.")
    private String memberPW;                // 회원 PassWrod

    @NotBlank(message = "PASSWORD 검증란 입력은 필수 입니다.")
    private String checkedMemeberPw;        // 패스워드 검증

    @NotBlank(message = "이름 입력은 필수 입니다.")
    private String memberName;              // 회원 이름

    @NotBlank(message = "주소 입력은 필수 입니다.")
    private String memberAddr;              // 회원 주소

    @NotBlank(message = "핸드폰 번호 입력은 필수 입니다.")
    private String memberPhoneNum;          // 회원 핸드폰 넘버

    private LocalDateTime joinDate;         // 회원가입 일자
    private LocalDateTime withDrawalDate;   // 회원탈퇴 일자
    private boolean withDrawalStatus;       // 회원 탈퇴 여부
    
}
