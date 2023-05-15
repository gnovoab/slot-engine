
//Namespace
package com.gabriel.slot.service;

//Imports

import java.util.List;

/**
 * Service implementation that handle files operation
 */
@SuppressWarnings("PMD.CommentRequired")
public interface FileService {

    List<String> fetchFileNamesFromDirectory(String path, String fileExtension);
}
