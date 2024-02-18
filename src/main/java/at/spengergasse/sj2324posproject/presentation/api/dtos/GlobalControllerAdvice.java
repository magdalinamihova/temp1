package at.spengergasse.sj2324posproject.presentation.api.dtos;

import at.spengergasse.sj2324posproject.service.exceptions.BookNotFoundException;
import at.spengergasse.sj2324posproject.service.exceptions.NotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HttpEntity<ProblemDetail> handleGenericNotFoundException(BookNotFoundException nfEx) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, nfEx.getMessage());
        problemDetail.setProperty("key", nfEx.getKey());
        return ResponseEntity.of(problemDetail).build();
    }
}
