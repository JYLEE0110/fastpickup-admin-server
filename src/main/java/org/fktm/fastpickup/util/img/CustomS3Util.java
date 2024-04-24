package org.fktm.fastpickup.util.img;

import java.net.URI;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
@Log4j2
@RequiredArgsConstructor
public class CustomS3Util {

  @Value("${spring.cloud.aws.s3.bucket}")
  private String bucket;

  private final S3Client s3Client;

  public void uploadFiles(List<Path> filePaths, boolean delFlag) {

    if(filePaths == null || filePaths.size() == 0) {
      return;
    }

    for (Path filePath : filePaths) {

      PutObjectRequest request = PutObjectRequest.builder()
      .bucket(bucket)
      .key(filePath.toFile().getName())
      .build();
  
       s3Client.putObject(request, filePath);

      if(delFlag) {
        filePath.toFile().delete();
      }
    }
  }

  public void deleteFiles(List<Path> filePaths) {

    if(filePaths == null || filePaths.size() == 0) {
      return;
    }

    for (Path filePath : filePaths) {

      DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
        .bucket(bucket)
        .key(filePath.toFile().getName())
        .build();

     s3Client.deleteObject(deleteObjectRequest);


    }

  }

}
