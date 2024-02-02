package org.fktm.fastpickup.member.page;

import org.fktm.fastpickup.util.page.PageRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class MemberPageRequestDTO extends PageRequestDTO {

    private String withDrawalStatus;   //회원 탈퇴여부
    
}
