package services;

import models.CamImage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;


public class RestfulClient {

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Value("${kafkaeskadapter.uri}")
    private String URI = "http://localhost:8080/data";

    public RestfulClient() {
    }

    public String postNumberPlate(CamImage camImage) {

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

        return result.getBody();
    }
}
