package org.fktm.fastpickup.member.dto;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.ibatis.javassist.Loader.Simple;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public class MemberDTO extends User {

    public MemberDTO(String memberID, String memberPW, List<String> authorities) {
        super(memberID, memberPW, authorities.stream()
                .map(str -> new SimpleGrantedAuthority(str))
                .collect(Collectors.toList()));

        this.memberID = memberID;
        this.memberPW = memberPW;
        this.roleNames = authorities;
    }

    // public MemberDTO(String memberID, String memberPW, Collection<? extends
    // GrantedAuthority> authorities,
    // String comfirmMemberPW, String memberName, String memberAddr, String
    // memberPhoneNum, LocalDateTime joinDate,
    // LocalDateTime withDrawalDate, boolean withDrawalStatus) {

    // super(memberID, memberPW, authorities.stream()
    // .map(Enum::name)
    // .map(SimpleGrantedAuthority::new)
    // .collect(Collectors.toList()));

    // super(memberID, memberPW, authorities);

    // // this.memberID = memberID;
    // // this.memberPW = memberPW;
    // // this.comfirmMemberPW = comfirmMemberPW;
    // // this.memberName = memberName;
    // // this.memberAddr = memberAddr;
    // // this.memberPhoneNum = memberPhoneNum;
    // // this.joinDate = joinDate;
    // // this.withDrawalDate = withDrawalDate;
    // // this.withDrawalStatus = withDrawalStatus;

    // }

    @NotBlank(message = "ID 입력은 필수 입니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String memberID; // 회원 ID

    @NotBlank(message = "PASSWORD 입력은 필수 입니다.")
    private String memberPW; // 회원 PassWrod

    private List<String> roleNames;

    @NotBlank(message = "PASSWORD 검증란 입력은 필수 입니다.")
    private String comfirmMemberPW; // 패스워드 검증

    @NotBlank(message = "이름 입력은 필수 입니다.")
    private String memberName; // 회원 이름

    @NotBlank(message = "주소 입력은 필수 입니다.")
    private String memberAddr; // 회원 주소

    @NotBlank(message = "핸드폰 번호 입력은 필수 입니다.")
    private String memberPhoneNum; // 회원 핸드폰 넘버

    private LocalDateTime joinDate; // 회원가입 일자
    private LocalDateTime withDrawalDate; // 회원탈퇴 일자
    private boolean withDrawalStatus; // 회원 탈퇴 여부


    // JWT토큰 생성을 위한 Clamis 작성
    public Map<String, Object> getClamis(){

        Map<String, Object> map = new HashMap<>();

        map.put("memberID", memberID);
        map.put("memberPW", memberPW);
        map.put("roleNames",roleNames);

        return map;
    }

}
