package org.fktm.fastpickup.security;

import org.fktm.fastpickup.exception.customexception.FastPickUpException;
import org.fktm.fastpickup.member.dto.MemberDTO;
import org.fktm.fastpickup.member.dto.MemberReadDTO;
import org.fktm.fastpickup.member.exception.enumcode.MemberExceptionCode;
import org.fktm.fastpickup.member.mappers.MemberMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 입력된 username으로 DB에 사용자 정보를 가져온다.
        MemberReadDTO member = memberMapper.readMember(username);

        // 등록된 사용자, 이미 탈퇴한 회원 일 경우
        if(member == null || member.isWithDrawalStatus() == true){
            throw new FastPickUpException(MemberExceptionCode.NOT_EXCIST_MEMBER);
        }
        
        MemberDTO memberDTO = new MemberDTO(
            member.getMemberID(),
            member.getMemberPW(),
            member.getMemberRoles()
        );

        log.info(memberDTO);

        return memberDTO;
    }
    
}