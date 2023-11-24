package web.nuuunikita.project.firstrestproject.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.nuuunikita.project.firstrestproject.dto.MeasurementDTO;
import web.nuuunikita.project.firstrestproject.dto.MeasurementResponse;
import web.nuuunikita.project.firstrestproject.mapper.MeasurementMapper;
import web.nuuunikita.project.firstrestproject.models.Measurement;
import web.nuuunikita.project.firstrestproject.services.MeasurementService;
import web.nuuunikita.project.firstrestproject.util.MeasurementErrorResponse;
import web.nuuunikita.project.firstrestproject.util.MeasurementErrors;
import web.nuuunikita.project.firstrestproject.util.MeasurementValidator;

import java.util.List;
import java.util.stream.Collectors;

import static web.nuuunikita.project.firstrestproject.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
public class MeasurementRESTController {
    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final MeasurementMapper measurementMapper;

    @GetMapping
    public ResponseEntity<MeasurementResponse> getMeasurements() {
        MeasurementResponse measurementResponse = new MeasurementResponse(measurementService.getMeasurements().stream()
                .map(measurementMapper::convertToMeasurementDTO)
                .collect(Collectors.toList()));
        return new ResponseEntity<>(measurementResponse, HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity<Long> getCountRainyDay() {
        return new ResponseEntity<>(measurementService.getCountRainyDay(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> newMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult) {
        Measurement measurement = measurementMapper.convertToMeasurement(measurementDTO);

        measurementValidator.validate(measurement, bindingResult);
        if (bindingResult.hasErrors()) returnErrorsToClient(bindingResult);

        measurementService.saveMeasurement(measurement);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/avg")
    public ResponseEntity<Double> getAvgTemperature() {
        return new ResponseEntity<>(measurementService.getAvgTemperature(), HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementErrors measurementErrors) {
        MeasurementErrorResponse measurementErrorResponse = new MeasurementErrorResponse(
                measurementErrors.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(measurementErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
