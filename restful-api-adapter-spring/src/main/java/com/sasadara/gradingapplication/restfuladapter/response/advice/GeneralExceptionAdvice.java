package com.sasadara.gradingapplication.restfuladapter.response.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.EntityAlreadyExistsException;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.EntityNotFoundException;
import com.sasadara.gradingapplication.ports.primary.usecase.exception.InvalidRequestException;
import com.sasadara.gradingapplication.restfuladapter.response.ErrorMessageDto;
import com.sasadara.gradingapplication.restfuladapter.response.ResponseWrapper;
import org.slf4j.Logger;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@RestControllerAdvice
public class GeneralExceptionAdvice {
    private static final Logger LOGGER = getLogger(GeneralExceptionAdvice.class);

    private final ObjectMapper mapper = new ObjectMapper();

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseWrapper> handleException(Exception exception) {
        LOGGER.error("500 - INTERNAL SERVER ERROR", exception);
        ErrorMessageDto errorMessageDTO = new ErrorMessageDto<>(exception.getMessage());
        ResponseWrapper errorResponseWrapper = new ResponseWrapper<>(errorMessageDTO);
        return new ResponseEntity<>(errorResponseWrapper, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ResponseWrapper> handleEntityExistsException(EntityAlreadyExistsException exception) {
        LOGGER.error("Entity already exists exception has occurred.", exception);
        ErrorMessageDto errorMessage
                = new ErrorMessageDto<>(exception.getMessage());
        return new ResponseEntity<>(new ResponseWrapper<>(errorMessage), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseWrapper> handleEntityNotFoundException(EntityNotFoundException exception) {
        LOGGER.error("Entity not found exception has occurred.", exception);
        ErrorMessageDto<EntityNotFoundException> errorMessage
                = new ErrorMessageDto<>(exception.getMessage());
        return new ResponseEntity<>(new ResponseWrapper<>(errorMessage), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ResponseWrapper> handleInvalidRequestException(InvalidRequestException exception) {
        LOGGER.error("Invalid request exception has occurred.", exception);
        ErrorMessageDto errorMessage
                = new ErrorMessageDto<>(exception.getMessage());
        return new ResponseEntity<>(new ResponseWrapper<>(errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseWrapper> handleValidationExceptions(MethodArgumentNotValidException ex) {
        LOGGER.error("Bad Request parameters.", ex);
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add("Field: " + fieldName + " Error: " + errorMessage);
        });

        ErrorMessageDto<String> errorMessageDto = new ErrorMessageDto<>("Invalid Arguments Provided", errors);
        return new ResponseEntity<>(new ResponseWrapper<>(errorMessageDto), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseWrapper> handleConstraintViolationException(ConstraintViolationException exception) {
        LOGGER.error("Invalid request exception has occurred.", exception);

        List<String> messages = exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        ErrorMessageDto<String> errorMessage
                = new ErrorMessageDto<>(String.join(", ", messages));
        return new ResponseEntity<>(new ResponseWrapper<>(errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResponseWrapper> handleBindException(BindException exception) {
        LOGGER.error("Bind exception has occurred.", exception);
        String errorString = exception.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));

        ErrorMessageDto errorMessage
                = new ErrorMessageDto<>(errorString);
        return new ResponseEntity<>(new ResponseWrapper<>(errorMessage), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<ResponseWrapper> handleHttpClientNotFound(HttpClientErrorException.NotFound exception) {
        LOGGER.error("Http Client Not Found exception has occurred.", exception);

        try {
            Map<String, String> errorBody = mapper.readValue(exception.getResponseBodyAsString(), Map.class);

            ErrorMessageDto errorMessage
                    = new ErrorMessageDto<>(errorBody.getOrDefault("message", "No error message available"));
            return new ResponseEntity<>(new ResponseWrapper<>(errorMessage), HttpStatus.NOT_FOUND);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error converting exception message to json");

            ErrorMessageDto errorMessage
                    = new ErrorMessageDto<>(exception.getMessage());
            return new ResponseEntity<>(new ResponseWrapper<>(errorMessage), HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<ResponseWrapper> handleHttpServerInternalServerError(HttpServerErrorException.InternalServerError exception) {
        LOGGER.error("Http Server Internal Server exception has occurred.", exception);

        try {
            Map<String, String> errorBody = mapper.readValue(exception.getResponseBodyAsString(), Map.class);

            ErrorMessageDto errorMessage
                    = new ErrorMessageDto<>(errorBody.getOrDefault("message", "No error message available"));
            return new ResponseEntity<>(new ResponseWrapper<>(errorMessage), HttpStatus.BAD_REQUEST);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error converting exception message to json");

            ErrorMessageDto errorMessage
                    = new ErrorMessageDto<>(exception.getMessage());
            return new ResponseEntity<>(new ResponseWrapper<>(errorMessage), HttpStatus.BAD_REQUEST);
        }
    }
}
