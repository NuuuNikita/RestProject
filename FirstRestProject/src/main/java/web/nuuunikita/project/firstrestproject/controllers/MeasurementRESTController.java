package web.nuuunikita.project.firstrestproject.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.nuuunikita.project.firstrestproject.dto.MeasurementDTO;
import web.nuuunikita.project.firstrestproject.dto.MeasurementResponse;
import web.nuuunikita.project.firstrestproject.models.Measurement;
import web.nuuunikita.project.firstrestproject.services.MeasurementService;
import web.nuuunikita.project.firstrestproject.util.MeasurementErrorResponse;
import web.nuuunikita.project.firstrestproject.util.MeasurementErrors;
import web.nuuunikita.project.firstrestproject.util.MeasurementValidator;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static web.nuuunikita.project.firstrestproject.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/measurements")
public class MeasurementRESTController {

    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementRESTController(MeasurementService measurementService,
                                     MeasurementValidator measurementValidator,
                                     ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public MeasurementResponse getMeasurements() {
        return new MeasurementResponse(measurementService.getMeasurements().stream()
                .map(this::convertToMeasurementDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public long getCountRainyDay() {
        return measurementService.getMeasurements().stream()
                .filter(Measurement::isRaining)
                .filter(distinctByKey(Measurement::getFixedIn))
                .count();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> newMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult) {
        Measurement measurement = convertToMeasurement(measurementDTO);

        measurementValidator.validate(measurement, bindingResult);

        if (bindingResult.hasErrors()) returnErrorsToClient(bindingResult);

        measurementService.saveMeasurement(measurement);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementErrors measurementErrors){
        MeasurementErrorResponse measurementErrorResponse = new MeasurementErrorResponse(
                measurementErrors.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(measurementErrorResponse, HttpStatus.BAD_REQUEST);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
