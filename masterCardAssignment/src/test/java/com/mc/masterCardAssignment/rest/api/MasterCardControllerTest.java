package com.mc.masterCardAssignment.rest.api;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mc.masterCardAssignment.Service.FileReaderService;


@RunWith(MockitoJUnitRunner.class)
public class MasterCardControllerTest {


	@Autowired
	private MockMvc mockMvc;


	@InjectMocks
	private MasterCardController masterCardController;

	@Mock
	private FileReaderService fileReaderService;
	/** The log. */
	private static Logger LOG = LoggerFactory.getLogger(MasterCardController.class);

	/**
	 * Called before each test
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(masterCardController).build();

	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetConnectedCitites() throws Exception {
		MvcResult result = mockMvc.perform(get("/connected?origin=Trenton&destination=").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		assertNotNull(result);

	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetConnectedCititesWithoutParms() throws Exception {
		MvcResult result = mockMvc.perform(get("/connected?origin=&destination=").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andReturn();
		assertNotNull(result);

	}

}
