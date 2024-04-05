package org.fktm.fastpickup.category.restcontroller;

import java.util.List;
import java.util.Map;

import org.fktm.fastpickup.category.dto.CategoryDTO;
import org.fktm.fastpickup.category.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
@Log4j2
public class CategoryRestController {

    private final CategoryService categoryService;

    // 카테고리 생성
    @PostMapping("/regist")
    public ResponseEntity<Map<String, String>> registCategory(
            @RequestBody CategoryDTO categoryDTO) {

        log.info("===== /category/regist POST ===+==");

        categoryService.registCategory(categoryDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Result", "Success Regist"));

    }

    // 카테고리 읽기
    @GetMapping("/read/{cno}")
    public ResponseEntity<Map<String, CategoryDTO>> readCategory(
            @PathVariable("cno") int cno) {
        log.info("===== /category/read/" + cno + " GET =====");

        CategoryDTO dto = categoryService.readCategory(cno);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Category", dto));
    }

    // 카테고리 리스트
    @GetMapping("/list")
    public ResponseEntity<Map<String, List<CategoryDTO>>> getCategoryList() {
        log.info("===== /category/list GET =====");

        List<CategoryDTO> list = categoryService.getCategoryList();

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("categoryList", list));

    }

    // 카테고리 삭제
    @PutMapping("/remove/{cno}")
    public ResponseEntity<Map<String, String>> removeCategory(
            @PathVariable("cno") int cno) {

        log.info("===== /category/remove" + cno + "POST =====");

        categoryService.removeCategory(cno);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Result","Success Remove"));

    }
}
