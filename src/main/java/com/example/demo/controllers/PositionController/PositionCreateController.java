package com.example.demo.controllers.PositionController;

import com.example.demo.entities.Position;
import com.example.demo.entities.Sector;
import com.example.demo.service.PositionService.PositionCreateService;
import com.example.demo.service.SectorService.SectorCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/position")
@CrossOrigin("*")
public class PositionCreateController{
    @Autowired
    private PositionCreateService positionCreateService;

    @PostMapping(path = "create")
    public Position Create(@Valid @RequestBody Position request) {
         return this.positionCreateService.create(request);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
