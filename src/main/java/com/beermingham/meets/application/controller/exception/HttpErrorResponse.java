package com.beermingham.meets.application.controller.exception;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class HttpErrorResponse implements Serializable {

    private static final long serialVersionUID = -5820663359565585933L;

    private Integer status;
    private String error;
    private String message;

    public HttpErrorResponse(Integer status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public HttpErrorResponse() {
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
