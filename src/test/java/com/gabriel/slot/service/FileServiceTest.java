
//Namespace
package com.gabriel.slot.service;

//Imports

import com.gabriel.slot.exception.FileException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;

/**
 * Unit test class
 */
@ActiveProfiles("unitTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileServiceTest {

	//Fields
	@Autowired
	private FileService fileService;
	private org.springframework.core.io.Resource resource = new ClassPathResource("/mathmodels-test/");


	// Verify that files exists from a folder given
	@Test
	public void fetchFileNamesFromDirectoryTest() throws IOException, FileException {
		String filePath = resource.getFile().getPath();
		List<String> files = fileService.fetchFileNamesFromDirectory(filePath, ".xml");
		Assertions.assertEquals(3, files.size());
	}


	// Verify that throws an exception when path is wrong
	@Test
	public void fetchFileNamesFromDirectoryWrongPathTest() throws FileException {
		List<String> files = fileService.fetchFileNamesFromDirectory("this is a wrong path", ".xml");
		Assertions.assertEquals(0, files.size());
	}
}

