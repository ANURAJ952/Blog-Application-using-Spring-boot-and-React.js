package com.anuraj.blogapp.services.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.anuraj.blogapp.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		String name = file.getOriginalFilename();
//		random name 
		String randomId = UUID.randomUUID().toString();
		 name = randomId.concat(name.substring(name.lastIndexOf(".")));
		String FilePath = path+File.separator+name;
		
		File fi = new File(path);
		if(!fi.exists()) {
			fi.mkdir();
		}
		
		Files.copy(file.getInputStream(), Paths.get(FilePath));
		return name;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path+File.separator+fileName;
		InputStream is  = new FileInputStream(fullPath);
		return is;
	}

}
