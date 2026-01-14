package dev.sistema.SistemaRh.controller.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError {
    private Instant timestamp;
    private Integer statusError;
    private String error;
    private String message;
    private String path;
    private List<FieldMessage> errorList = new ArrayList<>();

    public void addError(String fieldName, String message){
        errorList.add(new FieldMessage(fieldName, message));
    }

    public ValidationError(Instant timestamp, Integer statusError, String error, String message, String path) {
        this.timestamp = timestamp;
        this.statusError = statusError;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatusError() {
        return this.statusError;
    }

    public void setStatusError(Integer statusError) {
        this.statusError = statusError;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<FieldMessage> getErrorList() {
        return this.errorList;
    }

    public void setErrorList(List<FieldMessage> errorList) {
        this.errorList = errorList;
    }

}
