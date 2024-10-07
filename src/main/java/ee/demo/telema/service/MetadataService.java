package ee.demo.telema.service;

import static ee.demo.telema.config.WebConfig.restTemplate;

import org.springframework.stereotype.Service;

@Service
public class MetadataService {

  public String fetchMetadata(String fileName) {
    String url = "https://api.github.com";
    return restTemplate().getForObject(url, String.class);
  }
}
