package services;

import models.CamImage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

public class RestfulClient {

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Value("${kafkaeskadapter.uri}")
//    private String URI;
//    private String URI = "http://180.0.0.7:8080/data";
    private String URI = "http://localhost:9001/postoffice";

    public RestfulClient() {}

    public HttpStatus postNumberPlate(CamImage camImage) {

        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("numplate", camImage.getIdentifier());
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<LinkedMultiValueMap<String,
                Object>>(map, headers);

        ByteArrayResource resource = new ByteArrayResource(camImage.getData()) {
            /**
             * SUPER IMPORTANT!!! Otherwise I receive a BAD REQUEST
             * @return
             */
            @Override
            public String getFilename() {
                return camImage.getIdentifier() + ".png";
            }
        };
        map.add("image", resource);

        ResponseEntity<String> result = restTemplate.exchange(URI,
                HttpMethod.POST,
                requestEntity, String.class);

        return result.getStatusCode();
    }


}

