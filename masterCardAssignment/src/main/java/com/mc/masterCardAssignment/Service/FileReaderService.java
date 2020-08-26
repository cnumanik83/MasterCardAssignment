package com.mc.masterCardAssignment.Service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mc.masterCardAssignment.fileReader.FileReaderInterFace;
import com.mc.masterCardAssignment.rest.api.MasterCardController;

@Component
public class FileReaderService {


	/** The log. */
	private static Logger LOG = LoggerFactory.getLogger(FileReaderService.class);

	@Value( "${file.path}" )
	String filepathName;
	/**
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 */
	public String getConnectedCties(String origin,String destination) throws Exception {
		LOG.debug("getConnectedCties");		
		FileReaderInterFace filereader=(s)->{
			Path path = FileSystems.getDefault().getPath(s);
			Map<String, String> mapFromFile=new HashMap<String, String>();
			try {						
				mapFromFile = Files.lines(path)
						.collect(Collectors.toMap(k -> k.split(",")[0], v -> v.split(",")[1]))
						.entrySet().stream()
						.filter(map -> origin.trim().equals(map.getKey().trim()))
						.filter(map -> destination.trim().equals(map.getValue().trim()))		
						.collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
				return  mapFromFile;

			} catch (IOException e) {					
				LOG.error("getConnectedCties-Unable to read file");
				
			}
			return mapFromFile;	


		};

		return filereader.readFromFile(filepathName).size()>0 ? "Yes":"No";

	}

}
