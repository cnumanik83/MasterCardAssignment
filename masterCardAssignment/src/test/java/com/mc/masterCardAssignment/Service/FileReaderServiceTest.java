package com.mc.masterCardAssignment.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mc.masterCardAssignment.fileReader.FileReaderInterFace;
import com.mc.masterCardAssignment.rest.api.MasterCardController;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class FileReaderServiceTest {


	/** The log. */
	private static Logger LOG = LoggerFactory.getLogger(FileReaderServiceTest.class);

	@InjectMocks
	private FileReaderService fileReaderService;


	@Value( "${file.path}" )
	String filepathName;


	/**
	 * 	
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		ReflectionTestUtils.setField(fileReaderService, "filepathName", "src/test/resources/city.txt");

	}


	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetNoConnectedCitites() throws Exception {

		String result= fileReaderService.getConnectedCties("test", "test");
		assertEquals("No", result);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetConnectedCitites() throws Exception {

		String result= fileReaderService.getConnectedCties("Philadelphia", "Newark");
		assertEquals("Yes", result);
	}
	
	@Test
	public void testGetConnectedCititesEmpty() throws Exception {

		String result= fileReaderService.getConnectedCties("", "");
		assertEquals("No", result);
	}

}
