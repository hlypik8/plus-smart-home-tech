package collector.common;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {

    private String status;
    private String reason;
    private String message;
    private String timestamp;
}
