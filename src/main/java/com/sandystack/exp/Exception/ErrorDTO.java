package com.sandystack.exp.Exception;

import lombok.*;

import java.io.Serializable;
import java.util.List;


/**
 * Unified error message
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ErrorDTO implements Serializable {

    // TODO: Sleuth, Zipkin

    private Integer statusCode;

    private String status;

    private String message;

    private String timestamp;

    private List<String> details;

}
