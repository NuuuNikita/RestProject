package web.nuuunikita.project.firstrestproject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import web.nuuunikita.project.firstrestproject.dto.MeasurementDTO;
import web.nuuunikita.project.firstrestproject.dto.SensorDTO;
import web.nuuunikita.project.firstrestproject.mapper.MeasurementMapper;
import web.nuuunikita.project.firstrestproject.models.Measurement;
import web.nuuunikita.project.firstrestproject.services.MeasurementService;
import web.nuuunikita.project.firstrestproject.util.MeasurementValidator;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MeasurementRESTController.class)
class MeasurementRESTControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MeasurementService measurementService;
    @MockBean
    private MeasurementValidator measurementValidator;
    @MockBean
    private MeasurementMapper measurementMapper;

    @Test
    void getMeasurements() throws Exception {
        when(measurementService.getMeasurements()).thenReturn(List.of(new Measurement(44.4, true)));
        MeasurementDTO measurementDTO = new MeasurementDTO(44.4, true, new SensorDTO("Sensor 1"));

        when(measurementMapper.convertToMeasurementDTO(any(Measurement.class))).thenReturn(measurementDTO);

        mockMvc.perform(get("/measurements"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(
                        measurementDTO))));

        verify(measurementService, times(1)).getMeasurements();
    }

    @Test
    void getCountRainyDay() throws Exception {
        when(measurementService.getCountRainyDay()).thenReturn(10L);

        mockMvc.perform(get("/measurements/rainyDaysCount"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(10L));

        verify(measurementService, times(1)).getCountRainyDay();
    }

    @Test
    void newMeasurement() throws Exception {
        MeasurementDTO measurementDTO = new MeasurementDTO(21.2, true, new SensorDTO("Sensor1"));
        Measurement measurement = new Measurement(21.2, true);
        String measurementJson = objectMapper.writeValueAsString(measurementDTO);

        when(measurementMapper.convertToMeasurement(any(MeasurementDTO.class))).thenReturn(measurement);

        mockMvc.perform(post("/measurements/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(measurementJson))
                .andExpect(status().isOk());

        verify(measurementService, times(1))
                .saveMeasurement(measurement);
    }

    @Test
    void getAvgTemperature() throws Exception {
        when(measurementService.getAvgTemperature()).thenReturn(33.3);

        mockMvc.perform(get("/measurements/avg"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(33.3));

        verify(measurementService, times(1)).getAvgTemperature();
    }
}