package br.com.uploadfile.uploadfile.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.uploadfile.uploadfile.models.AccessLog;

@Service
public class AccessLogFileService {

	@Autowired
	private AccessLogService accessLogService;

	public void saveFileContent(MultipartFile file) {
		Set<AccessLog> readFile = readFile(file);
		accessLogService.batch(readFile);
	}

	private Set<AccessLog> readFile(MultipartFile file) {
		BufferedReader br;
		Set<AccessLog> result = new HashSet<>();
		try {

			String line;
			InputStream is = file.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				String[] lineArr = line.split("|");
				String data = lineArr[0];
				String ip = lineArr[1];
				String request = lineArr[2];
				String status = lineArr[3];
				String userAgent = lineArr[4];
				result.add(new AccessLog(data, ip, request, status, userAgent));
			}

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return result;
	}

}
