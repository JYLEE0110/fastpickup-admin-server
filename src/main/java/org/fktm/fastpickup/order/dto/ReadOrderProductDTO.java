package org.fktm.fastpickup.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
public class ReadOrderProductDTO {

    private String productName;
    private Long pno;
    private int quantity;
    private String imgName;
    
}
