//Namespace
package com.gabriel.slot.service.impl;

//Imports

import com.gabriel.slot.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Service implementation that handle files operation
 */
@Service
public class FileServiceImpl implements FileService {

    //The LOG
    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);


    /**
     * Retrieve all the file names with an specific extension from a directory given
     *
     * @param path
     * @param fileExtension
     * @return
     */
    @Override
    public List<String> fetchFileNamesFromDirectory(String path, String fileExtension) {
        LOGGER.info("Retrieving files from directory [{}] with extension [{}] ", path, fileExtension);

        List<String> fileNames = new ArrayList<>();

        //Create the file
        File folder = new File(path);

        //Retrieve all the files in the folder
        File[] listOfFiles = folder.listFiles();

        //Verify that are files to process
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().indexOf(fileExtension) > 0) {
                    fileNames.add(file.getName());
                }
            }
        }

        LOGGER.info("Returning [{}] files from directory [{}] with extension [{}] ", fileNames.size(), path, fileExtension);
        return fileNames;
    }
}
