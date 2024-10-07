package ee.demo.telema.controller;

import ee.demo.telema.entity.FileEntity;
import ee.demo.telema.service.FileService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class FileController {

  private final FileService fileService;

  @PostMapping("/upload")
  public ResponseEntity<List<FileEntity>> uploadFile(@RequestBody FileEntity fileEntity) {
    // Disallow image file types
    if (fileEntity.getFileType()
        .matches("image/jpeg|image/png|image/gif")) {
      return ResponseEntity.badRequest()
          .body(null);
    }

    FileEntity savedFile = fileService.uploadFile(fileEntity);
    List<FileEntity> files = fileService.getFilesByUser(fileEntity.getUploadedBy());
    return ResponseEntity.ok(files);
  }

  @GetMapping("/list")
  public ResponseEntity<List<FileEntity>> getFiles(@RequestParam String user) {
    List<FileEntity> files = fileService.getFilesByUser(user);
    return ResponseEntity.ok(files);
  }
}
