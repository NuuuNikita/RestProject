package web.nuuunikita.project.firstrestproject.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.nuuunikita.project.firstrestproject.dto.SensorDTO;
import web.nuuunikita.project.firstrestproject.models.Sensor;
import web.nuuunikita.project.firstrestproject.services.SensorService;
import web.nuuunikita.project.firstrestproject.util.MeasurementErrorResponse;
import web.nuuunikita.project.firstrestproject.util.MeasurementErrors;
import web.nuuunikita.project.firstrestproject.util.SensorValidator;

import static web.nuuunikita.project.firstrestproject.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/sensors")
public class SensorRESTController {

    private final SensorService sensorService;
    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorRESTController(SensorService sensorService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> registrationSensor(@RequestBody @Valid SensorDTO sensorDTO,
                                                         BindingResult bindingResult) {
        Sensor sensor = convertToSensor(sensorDTO);

        sensorValidator.validate(sensor, bindingResult);

        if (bindingResult.hasErrors()) returnErrorsToClient(bindingResult);

        sensorService.saveSensor(sensor);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementErrors errors) {
        MeasurementErrorResponse measurementErrorResponse = new MeasurementErrorResponse(
                errors.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(measurementErrorResponse, HttpStatus.BAD_REQUEST);
    }

    public Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
