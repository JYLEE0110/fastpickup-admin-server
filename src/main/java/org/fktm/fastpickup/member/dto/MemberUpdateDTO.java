package org.fktm.fastpickup.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class MemberUpdateDTO {

    private String memberID;

    @NotBlank(message = "PASSWORD 입력은 필수 입니다.")
    @Pattern(regexp = "^(?=.*[!@#$%^&*()-+=]).*$", message = "특수문자 1개 이상을 포함해야 합니다.")
    private String memberPW;                // 회원 PassWrod

    @NotBlank(message = "PASSWORD 검증란 입력은 필수 입니다.")
    private String checkedMemeberPw;        // 패스워드 검증

    @NotBlank(message = "주소 입력은 필수 입니다.")
    private String memberAddr;              // 회원 주소

     @NotBlank(message = "핸드폰 번호 입력은 필수 입니다.")
    private String memberPhoneNum;          // 회원 핸드폰 넘버
    
}
