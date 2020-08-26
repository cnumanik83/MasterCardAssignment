package com.mc.masterCardAssignment.rest.api;

import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mc.masterCardAssignment.Service.FileReaderService;

@RestController
public class MasterCardController {

	/** The log. */
	private static Logger LOG = LoggerFactory.getLogger(MasterCardController.class);

	@Autowired
	FileReaderService fileReaderService;

	/**
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 */
	@GetMapping("/connected")
	public ResponseEntity<String> getConnectedCities(@QueryParam("origin") String origin,
			@QueryParam("destination") String destination) {
		LOG.debug("Start-getConnectedCities");
		if(StringUtils.isEmpty(origin)&& StringUtils.isEmpty(destination)) {
			return new ResponseEntity<>("Origin and Destination are Empty", HttpStatus.BAD_REQUEST);
		}else {
			try {
				return new ResponseEntity<>(fileReaderService.getConnectedCties(origin, destination), HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<>("Please Check The Logs", HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}


	}


}
