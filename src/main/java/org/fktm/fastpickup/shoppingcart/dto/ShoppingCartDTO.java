package org.fktm.fastpickup.shoppingcart.dto;

import java.time.LocalDateTime;

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
public class ShoppingCartDTO {
    private Long cno;                   //장바구니 번호
    private String memberID;            // 아이디
    private LocalDateTime addedDate;    //장바구니 추가 날짜
}
