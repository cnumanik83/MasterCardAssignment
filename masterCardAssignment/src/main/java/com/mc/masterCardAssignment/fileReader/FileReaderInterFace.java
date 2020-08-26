package com.mc.masterCardAssignment.fileReader;

import java.util.Map;

@FunctionalInterface
public interface FileReaderInterFace {
	
	public Map<String, String> readFromFile(String fileName);

}
