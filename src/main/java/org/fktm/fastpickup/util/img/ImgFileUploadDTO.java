package org.fktm.fastpickup.util.img;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImgFileUploadDTO {
    
    private String uuid;        // 이미지 uuid
    private String imgName;     // 이미지 명
    private boolean img;        // 이미지 존재 유무

    // 이미지 파일 경로 가져오기
    public String getLink(){

        return "s_" + uuid + "_" + imgName;

    }

}
