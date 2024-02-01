package org.fktm.fastpickup.member.restcontroller;

import java.util.Map;

import org.fktm.fastpickup.member.dto.MemberListDTO;
import org.fktm.fastpickup.member.dto.MemberModifyDTO;
import org.fktm.fastpickup.member.dto.MemberReadDTO;
import org.fktm.fastpickup.member.dto.MemberRegistDTO;
import org.fktm.fastpickup.member.service.MemberService;
import org.fktm.fastpickup.product.dto.ProductListDTO;
import org.fktm.fastpickup.util.page.PageRequestDTO;
import org.fktm.fastpickup.util.page.PageResponseDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Log4j2
public class MemberRestController {

    // 의존성 주입
    private final MemberService memberService;

    // 회원 가입
    @PostMapping("/regist")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Map<String, String>> registMember(
            @Valid @RequestBody MemberRegistDTO memberRegistDTO) {

        log.info("===== /api/member/regist | Post =====");

        memberService.registMember(memberRegistDTO);

        log.info("===== /api/member/regist | Post =====");

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Result", "Success Regist Member"));
    }

    // 회원 상세
    @GetMapping("/read/{memberID}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<MemberReadDTO> readMember(
            @PathVariable("memberID") String memberID) {
        log.info("===== /api/member/read " + memberID + " | Post =====");

        MemberReadDTO memberReadDTO = memberService.ReadMember(memberID);

        return ResponseEntity.status(HttpStatus.CREATED).body(memberReadDTO);

    }

    // 회원 리스트
    // @PreAuthorize("hasAnyRole('ROLE_USER')")
    @PreAuthorize("permitAll()")
    @GetMapping("/list")
    public ResponseEntity<PageResponseDTO<MemberListDTO>> getMemberList(
            PageRequestDTO pageRequestDTO) {
        log.info("===== /api/member/list/ | GET =====");

        PageResponseDTO<MemberListDTO> memberList = memberService.getMemberList(pageRequestDTO);

        log.info("===== /api/member/list/ | GET =====");

        return ResponseEntity.status(HttpStatus.CREATED).body(memberList);
    }

    // 회원 삭제
    @PutMapping("/remove/{memberID}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Map<String, String>> removeProduct(
            @PathVariable("memberID") String memberID) {
        log.info("===== /api/member/remove/" + memberID + "| PUT =====");

        memberService.withdrawalMember(memberID);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Result", "Success withdrawal Member"));

    }

    // 회원 수정
    @PutMapping("/modify")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Map<String, String>> modifyMember(
            @Valid @RequestBody MemberModifyDTO memberModifyDTO) {
        log.info("===== /api/member/modify/ | PUT =====");

        memberService.modifyMember(memberModifyDTO);

        log.info("===== /api/member/modify/ | PUT =====");

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Result", "Success Modify Member"));

    }

}
