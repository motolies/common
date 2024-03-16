package io.github.motolies.aop.log.service;

import io.github.motolies.aop.log.model.SystemLog;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemLogService {

  private final SystemLogRepository systemLogRepository;

  @Async
  public void save(SystemLog systemLog) {
    systemLogRepository.save(systemLog);
  }

}
