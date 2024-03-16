package io.github.motolies.aop.log.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "system_log")
public class SystemLog {

  @Id
  @jakarta.persistence.Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Id", nullable = false)
  private Long id;

  @Column(name = "RequestUri", length = 1024)
  private String requestUri;

  @Column(name = "ControllerName", length = 512)
  private String controllerName;

  @Column(name = "MethodName", length = 512)
  private String methodName;

  @Column(name = "HttpMethodType", length = 8)
  private String httpMethodType;

  @Column(name = "ParamData", columnDefinition = "TEXT")
  private String paramData;

  @Column(name = "ResponseBody", columnDefinition = "TEXT")
  private String responseBody;

  @Column(name = "RemoteAddr", length = 64)
  private String remoteAddr;

  @Column(name = "RequestDate", columnDefinition = "TIMESTAMP(6)")
  private Timestamp requestDateTime;

  @Column(name = "ProcessTime")
  private Long processTime;

}