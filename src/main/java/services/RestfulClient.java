package services;

import main.Main;
import models.CamImage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@RestController
public class RestfulClient {

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    private String URI;

    public RestfulClient() throws FileNotFoundException {
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            //load a properties file from class path, inside static method
            prop.load(input);
            //get the property value and print it out
            System.out.println(prop.getProperty("kafkaeskadapter.uri"));
            this.URI = prop.getProperty("kafkaeskadapter.uri");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

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

