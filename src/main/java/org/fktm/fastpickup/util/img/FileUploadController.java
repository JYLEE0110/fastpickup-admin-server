package org.fktm.fastpickup.util.img;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
@Log4j2
public class FileUploadController {

    private final CustomFileUtil fileUtil;

    @PostMapping("/upload")
    public List<ImgFileUploadDTO> uploadFiles (@RequestParam("files") List<MultipartFile> files ) {

        List<ImgFileUploadDTO> fileList = fileUtil.saveFiles(files);

        log.info("------------------------------");
        log.info("------------------------------");
        log.info("------------------------------");
        log.info(fileList);

        return fileList;
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGET(@PathVariable String fileName){

        return fileUtil.getFile(fileName);

    }

    @DeleteMapping("remove/{fileName}")
    public Map<String, String> removeFile(
            @PathVariable("fileName") String fileName) {

                fileUtil.deleteFiles(fileName);

                return Map.of("result","success");

            }
}

