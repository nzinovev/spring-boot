package com.example.springboot.domain.exception;

public class ServiceApiException {

    private String detailedMessage;
    private String serviceName;
    private Integer status;

    public ServiceApiException(String detailedMessage, Integer status) {
        this.status = status;
        this.detailedMessage = detailedMessage;
        this.serviceName = "spring-boot-service";
    }
}
