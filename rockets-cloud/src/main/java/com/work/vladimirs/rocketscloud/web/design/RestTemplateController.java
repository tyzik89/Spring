package com.work.vladimirs.rocketscloud.web.design;

import com.work.vladimirs.rocketscloud.models.inventory.Rocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Через RespTemplate объект спринг может потреблять другие Rest API.
 * В данном случае мы потребляем свой же API по получению деталей ракеты.
 */
@RestController
@RequestMapping("/restTemplate")
public class RestTemplateController {

    private static final Logger LOG = LoggerFactory.getLogger(RestTemplateController.class);

    @Autowired
    private RestTemplate rest;

    @GetMapping("/rocketIdOne/{id}")
    public Rocket getComponentByIdOne(@PathVariable("id") String rocketId) {
        return rest.getForObject("http://localhost:8080/design/{id}",
                Rocket.class, rocketId);
    }

    @GetMapping("/rocketIdTwo/{id}")
    public Rocket getComponentByIdTwo(@PathVariable("id") String rocketId) {
        Map<String,String> urlVariables = new HashMap<>();
        urlVariables.put("id", rocketId);
        URI url = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/design/{id}")
                .build(urlVariables);
        return rest.getForObject(url, Rocket.class);
    }

    @GetMapping("/rocketIdThree/{id}")
    public Rocket getComponentByIdThree(@PathVariable("id") String rocketId) {
        ResponseEntity<Rocket> responseEntity =
                rest.getForEntity("http://localhost:8080/design/{id}",
                        Rocket.class, rocketId);
        LOG.info("Fetched time: " +
                responseEntity.getHeaders().getDate());
        return responseEntity.getBody();
    }
}
