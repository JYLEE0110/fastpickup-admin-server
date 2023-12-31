package org.fktm.fastpickup.member.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class MemberRegistDTO {
    
    @NotBlank(message = "ID 입력은 필수 입니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String memberID;                // 회원 ID

    @NotBlank(message = "PASSWORD 입력은 필수 입니다.")
    @Pattern(regexp = "^(?=.*[!@#$%^&*()-+=]).*$", message = "특수문자 1개 이상을 포함해야 합니다.")
    private String memberPW;                // 회원 PassWrod

    @NotBlank(message = "PASSWORD 검증란 입력은 필수 입니다.")
    private String comfirmMemberPW;        // 패스워드 검증

    @NotBlank(message = "이름 입력은 필수 입니다.")
    private String memberName;              // 회원 이름

    @NotBlank(message = "주소 입력은 필수 입니다.")
    private String memberAddr;              // 회원 주소

    @NotBlank(message = "핸드폰 번호 입력은 필수 입니다.")
    private String memberPhoneNum;          // 회원 핸드폰 넘버

    @Builder.Default
    private List<MemberRole> memberRoles = new ArrayList<>();  // 권한 이름

    public void addMemberRoles(MemberRole memberRole){
        memberRoles.add(memberRole);
    }
}
