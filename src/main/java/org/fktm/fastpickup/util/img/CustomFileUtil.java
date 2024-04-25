package org.fktm.fastpickup.util.img;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

@Component
@Log4j2
@RequiredArgsConstructor
public class CustomFileUtil {

  @Value("${org.fktm.upload.path}")
  private String uploadPath;

  private final CustomS3Util s3Util;

  @PostConstruct
  public void init() {
    File tempFolder = new File(uploadPath);

    if (tempFolder.exists() == false) {
      tempFolder.mkdir();
    }

    uploadPath = tempFolder.getAbsolutePath();

    log.info("-------------------------------------");
    log.info(uploadPath);
  }

  public List<ImgFileUploadDTO> saveFiles(List<MultipartFile> files) throws RuntimeException {

    if (files == null || files.size() == 0) {
      return null;
    }

    List<ImgFileUploadDTO> fileList = new ArrayList<>();

    for (MultipartFile multipartFile : files) {

      ImgFileUploadDTO result = null;

      // uuid(pk) 생성
      String uuidStr = UUID.randomUUID().toString();
      String fileName = multipartFile.getOriginalFilename();

      String savedName = uuidStr + "_" + fileName;

      Path savePath = Paths.get(uploadPath, savedName);

      List<Path> uploadTargetPaths = new ArrayList<>();

      try {

        Files.copy(multipartFile.getInputStream(), savePath);

        uploadTargetPaths.add(savePath);

        result = ImgFileUploadDTO.builder()
            .uuid(uuidStr)
            .imgName(fileName)
            .build();

        String contentType = multipartFile.getContentType();

        if (contentType != null && contentType.startsWith("image")) { // 이미지 확인

          Path thumbnailPath = Paths.get(uploadPath, "s_" + savedName);

          Thumbnails.of(savePath.toFile())
              .size(80, 80)
              .toFile(thumbnailPath.toFile());

          uploadTargetPaths.add(thumbnailPath);
        }

        fileList.add(result);

        // s3 upload
        s3Util.uploadFiles(uploadTargetPaths, true);

      } catch (Exception e) {

        e.printStackTrace();
        throw new RuntimeException(e.getMessage());
      }
    } // end for
    return fileList;
  }

  public ResponseEntity<Resource> getFile(String fileName) {

    Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

    if (!resource.exists()) {

      resource = new FileSystemResource(uploadPath + File.separator + "default.jpeg");

    }

    HttpHeaders headers = new HttpHeaders();

    try {
      headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
    } catch (Exception e) {
      return ResponseEntity.internalServerError().build();
    }
    return ResponseEntity.ok().headers(headers).body(resource);

  }

  public void deleteFiles(String fileName) {

    if (fileName == null) {
      return;
    }

    List<Path> deleteTargetPaths = new ArrayList<>();

    try {
      // 썸네일이 있는지 확인하고 삭제
      String thumbnailFileName = "s_" + fileName;
      Path thumbnailPath = Paths.get(uploadPath, thumbnailFileName);
      Path filePath = Paths.get(uploadPath, fileName);

      Files.deleteIfExists(filePath);
      Files.deleteIfExists(thumbnailPath);

      deleteTargetPaths.add(filePath);
      deleteTargetPaths.add(thumbnailPath);

      s3Util.deleteFiles(deleteTargetPaths);

    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }

  }

  public void deleteAllFiles(List<String> files) {

    for (String fileName : files) {
      if (fileName == null) {
        return;
      }

      List<Path> deleteTargetPaths = new ArrayList<>();

      try {
        // 썸네일이 있는지 확인하고 삭제
        String thumbnailFileName = "s_" + fileName;
        Path thumbnailPath = Paths.get(uploadPath, thumbnailFileName);
        Path filePath = Paths.get(uploadPath, fileName);

        Files.deleteIfExists(filePath);
        Files.deleteIfExists(thumbnailPath);

        deleteTargetPaths.add(filePath);
        deleteTargetPaths.add(thumbnailPath);

        s3Util.deleteFiles(deleteTargetPaths);

      } catch (IOException e) {
        throw new RuntimeException(e.getMessage());
      }

    }
  }

}
