package client;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Hello world!
 */
public class Client {
    private static final RestTemplate restTemplate;

    static {
        restTemplate = new RestTemplate();
    }

    public static void main(String[] args) {
        Random random = new Random();

        String sensorName = "MainSensor";
        registrationSensor(sensorName);
        for (int i = 0; i < 100; i++) {
            createMeasurements(random.nextInt(199) - 99, random.nextBoolean(), sensorName);
        }
    }

    public static void registrationSensor(String sensorName) {
        Map<String, String> jsonSensor = new HashMap<>();

        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonSensor);
        jsonSensor.put("name", sensorName);

        String registrationResponse = restTemplate.postForObject("http://localhost:8080/sensors", request, String.class);

        System.out.println("Registration sensor" + registrationResponse);
    }

    public static void createMeasurements(double temperature, boolean isRain, String sensorName) {
        Map<String, Object> requestMeasurement = new HashMap<>();
        requestMeasurement.put("temperature", temperature);
        requestMeasurement.put("raining", isRain);
        requestMeasurement.put("sensor", Map.of("name", sensorName));

        HttpEntity<Map<String, Object>> requestToServer = new HttpEntity<>(requestMeasurement);

        String createResponse = restTemplate.postForObject("http://localhost:8080/measurements/add", requestToServer, String.class);

        System.out.println(createResponse);
    }
}
