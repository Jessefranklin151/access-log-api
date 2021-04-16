package br.com.uploadfile.uploadfile.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uploadfile.uploadfile.models.AccessLog;
import br.com.uploadfile.uploadfile.repository.AccessLogRepository;

@Service
public class AccessLogService {

	@Autowired
	private AccessLogRepository AccessLogRepository;

	public AccessLog create(AccessLog log) {
		return AccessLogRepository.save(log);
	}

	public Set<AccessLog> batch(Set<AccessLog> logs) {
		return AccessLogRepository.saveBatch(logs);
	}

}
