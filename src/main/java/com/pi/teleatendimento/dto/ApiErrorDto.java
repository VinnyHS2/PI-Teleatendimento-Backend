package com.pi.teleatendimento.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author MZN Solucoes
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ApiErrorDto {

    @JsonInclude(Include.NON_NULL)
    class ErrorMessage {
    	
        @JsonProperty("message")
        public String message;

        @JsonProperty("more_info")
        private String moreInfo;
        
        @JsonProperty("code")
        public Integer code;


        public ErrorMessage(String message, String moreInfo) {
            this.message = message;
            this.moreInfo = moreInfo;
        }
        
        public ErrorMessage(String message, int code) {
            this.message = message;
            this.code = code;
            this.moreInfo = null;
        }

        public ErrorMessage(String message) {
            this.message = message;
            this.moreInfo = null;
        }
    }

    @JsonProperty("http_status")
    protected Integer httpStatusCode;

    @JsonProperty("error_message")
    protected ErrorMessage message;

    protected Map<String, String> fieldErrors;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();

    public ApiErrorDto(Integer httpStatusCode, String message) {
        this.httpStatusCode = httpStatusCode;
        this.message = new ErrorMessage(message);
    }

    public ApiErrorDto(Integer httpStatusCode, String message, Map<String, String> fieldErrors) {
        this.httpStatusCode = httpStatusCode;
        this.message = new ErrorMessage(message);
        this.fieldErrors = fieldErrors;
    }
    
    public ApiErrorDto(Integer httpStatusCode, Integer errorCode, String message, Map<String, String> fieldErrors) {
        this.httpStatusCode = httpStatusCode;
        this.message = new ErrorMessage(message, errorCode);
        this.fieldErrors = fieldErrors;
    }
}