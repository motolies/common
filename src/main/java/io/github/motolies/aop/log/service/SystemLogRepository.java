package io.github.motolies.aop.log.service;

import io.github.motolies.aop.log.model.SystemLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemLogRepository extends JpaRepository<SystemLog, Long> {

}