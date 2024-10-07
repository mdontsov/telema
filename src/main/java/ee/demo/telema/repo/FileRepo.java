package ee.demo.telema.repo;

import ee.demo.telema.entity.FileEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepo extends JpaRepository<FileEntity, Long> {

  List<FileEntity> findByUploadedBy(String uploadedBy);
}
