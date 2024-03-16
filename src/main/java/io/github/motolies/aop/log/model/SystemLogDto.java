package io.github.motolies.aop.log.model;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemLogDto {

  private String requestUri;
  private String controllerName;
  private String methodName;
  private String httpMethodType;
  private String paramData;
  private String responseBody;
  private String remoteAddr;
  private Timestamp requestDateTime;
  private Long processTime;

  // to Entity
  public SystemLog toEntity() {
    return SystemLog.builder()
        .requestUri(requestUri)
        .controllerName(controllerName)
        .methodName(methodName)
        .httpMethodType(httpMethodType)
        .paramData(paramData)
        .responseBody(responseBody)
        .remoteAddr(remoteAddr)
        .requestDateTime(requestDateTime)
        .processTime(processTime)
        .build();
  }
}
