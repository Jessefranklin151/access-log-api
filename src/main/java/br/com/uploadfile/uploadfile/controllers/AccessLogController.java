package br.com.uploadfile.uploadfile.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.uploadfile.uploadfile.models.AccessLog;
import br.com.uploadfile.uploadfile.service.AccessLogService;

@RestController
@RequestMapping("accesslog")
public class AccessLogController {

	@Autowired
	private AccessLogService accessLogService;

	@PostMapping
	public ResponseEntity<AccessLog> create(@RequestBody AccessLog log) {
		return new ResponseEntity<AccessLog>(accessLogService.create(log), HttpStatus.CREATED);
	}

	@PostMapping("batch")
	public ResponseEntity<Set<AccessLog>> batch(@RequestBody Set<AccessLog> logs) {
		return new ResponseEntity<Set<AccessLog>>(accessLogService.batch(logs), HttpStatus.CREATED);

	}

}
