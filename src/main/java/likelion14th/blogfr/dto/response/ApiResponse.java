package likelion14th.blogfr.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({ "success", "code", "message", "data" })
public class ApiResponse<T>{
        private boolean success;
        private int code;
        private String message;
        private T data;

        public ApiResponse(boolean success, int code, String message) {
            this.success = success;
            this.code = code;
            this.message = message;
        }
}