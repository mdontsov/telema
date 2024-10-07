package ee.demo.telema.service;

import ee.demo.telema.entity.FileEntity;
import ee.demo.telema.repo.FileRepo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {

  private final FileRepo fileRepo;

  public FileEntity uploadFile(FileEntity fileEntity) {
    return fileRepo.save(fileEntity);
  }

  @Cacheable(value = "filesCache", key = "#uploadedBy")
  public List<FileEntity> getFilesByUser(String uploadedBy) {
    return fileRepo.findByUploadedBy(uploadedBy);
  }
}
