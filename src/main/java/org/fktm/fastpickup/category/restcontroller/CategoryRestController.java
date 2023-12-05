package org.fktm.fastpickup.category.restcontroller;

import org.fktm.fastpickup.category.dto.CategoryDTO;
import org.fktm.fastpickup.category.service.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
@Log4j2
public class CategoryRestController {

    private final CategoryService categoryService;

    @PostMapping("/regist")
    public int registCategory(
        @RequestBody CategoryDTO categoryDTO){

            return categoryService.registCategory(categoryDTO);

        }

    
}
