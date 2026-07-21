package org.restaurant.shared.util.handler;

import org.restaurant.shared.dto.ErrorResponse;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

public class Util {

    public static ErrorResponse buildResponse(String message, WebRequest request) {
        return ErrorResponse
                .builder()
                .code(extractCode(message))
                .message(extractMessage(message))
                .timestamp(LocalDateTime.now())
                .path(request
                        .getDescription(false)
                        .replace("uri=", ""))
                .build();
    }

    /**
     * Extract error code from message.
     * Message format: "CODE_XXX - Error message"
     */
    public static String extractCode(String message){
        if(message == null || !message.contains("-")){
            return "UNKNOWN";
        }

        return message.substring(0, message.indexOf("-"));
    }

    /**
     * Extract error message from message.
     * Message format: "CODE_XXX - Error message"
     */
    public static String extractMessage(String message){
        if(message == null || !message.contains("-")){
            return message;
        }

        return message.substring(message.indexOf("-") + 1);
    }
}
