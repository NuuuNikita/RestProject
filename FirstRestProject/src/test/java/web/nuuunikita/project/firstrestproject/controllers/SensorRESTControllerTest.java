package web.nuuunikita.project.firstrestproject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import web.nuuunikita.project.firstrestproject.dto.SensorDTO;
import web.nuuunikita.project.firstrestproject.mapper.MeasurementMapper;
import web.nuuunikita.project.firstrestproject.mapper.SensorMapper;
import web.nuuunikita.project.firstrestproject.models.Sensor;
import web.nuuunikita.project.firstrestproject.services.SensorService;
import web.nuuunikita.project.firstrestproject.util.SensorValidator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SensorRESTController.class)
class SensorRESTControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private SensorService sensorService;
    @MockBean
    private SensorValidator sensorValidator;
    @MockBean
    private SensorMapper sensorMapper;
    @Test
    void registrationSensor() throws Exception {
        SensorDTO sensorDTO = new SensorDTO("Sensor 1");
        String jsonSensorDto = objectMapper.writeValueAsString(sensorDTO);
        Sensor sensor = new Sensor("Sensor 1");

        when(sensorMapper.convertToSensor(any(SensorDTO.class))).thenReturn(sensor);

        mockMvc.perform(post("/sensors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonSensorDto))
                .andExpect(status().isOk());

        verify(sensorService, times(1)).saveSensor(sensor);
    }
}