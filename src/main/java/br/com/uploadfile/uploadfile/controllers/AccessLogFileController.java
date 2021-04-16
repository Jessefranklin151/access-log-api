package br.com.uploadfile.uploadfile.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.uploadfile.uploadfile.service.AccessLogFileService;

@RestController
@RequestMapping("batch")
public class AccessLogFileController {
	
	@Autowired
	private AccessLogFileService AccessLogFileService;
	
	@PostMapping
	public void batchFile(@RequestParam("file") MultipartFile file) {
		AccessLogFileService.saveFileContent(file);
	}

}
