package dev.gentile.base.api.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.gentile.base.business.util.DateUtil;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.util.Date;

@Data
public class ApiErrorResponse
{
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
  private Date timestamp;
  private int code;
  private String description;
  private String message;
  private String body;
  private Object data;

  public ApiErrorResponse()
  {
    this.timestamp = DateUtil.nowDate();
  }

  public ApiErrorResponse(HttpStatus httpStatus, String message)
  {
    this();
    this.code = httpStatus.value();
    this.description = httpStatus.getReasonPhrase();
    this.message = message;
  }

  public ApiErrorResponse(HttpStatus httpStatus, String message, String body)
  {
    this(httpStatus, message);
    this.body = body;
  }

  public ApiErrorResponse(HttpStatus httpStatus, String message, String body, Object data)
  {
    this(httpStatus, message, body);
    this.data = data;
  }
}