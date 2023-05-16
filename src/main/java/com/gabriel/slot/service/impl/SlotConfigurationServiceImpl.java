package com.gabriel.slot.service.impl;

import com.gabriel.slot.domain.model.mathmodel.MathModel;
import com.gabriel.slot.domain.model.game.SlotGame;
import com.gabriel.slot.domain.model.game.SlotGameCatalog;
import com.gabriel.slot.exception.ParseException;
import com.gabriel.slot.service.FileService;
import com.gabriel.slot.service.SlotConfigurationService;
import com.gabriel.slot.utils.XmlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class that handles all related slot configuration
 */
@Service
public class SlotConfigurationServiceImpl implements SlotConfigurationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlotConfigurationService.class);

    @Value("${mathmodel.folder-location}")
    private transient String mathModelsFolder;

    @Value("${games-catalog.folder-location}")
    private transient String gamesCatalogPath;

    @Value("${games-catalog.file-name}")
    private transient String gamesCatalogFileName;


    @Autowired
    private transient FileService fileService;


    /**
     * @return 
     */
    @Override
    public Map<Integer, SlotGame> loadSlotGames() {

        try{
            LOGGER.info("Loading Slot Catalog Games...");

            //Create the object
            Map<Integer, SlotGame> games = new ConcurrentHashMap<>();

            //Build the game catalog folder path
            String path = new ClassPathResource(gamesCatalogPath).getFile().getPath();

            //Parse the XML into an Object
            SlotGameCatalog slotGameCatalog = (SlotGameCatalog) XmlUtils.xmlFileToObj(path, gamesCatalogFileName, SlotGameCatalog.class);

            //Populate Map
            for (SlotGame slotGame : slotGameCatalog.getGames()) {
                games.put(slotGame.getId(), slotGame);
            }
            return games;
        }
        catch (IOException e){
            LOGGER.error("IOException while parsing Slot Game Catalog", e);
            throw new ParseException("IOException while parsing Slot Game Catalog", e);
        }
    }

    /**
     * @return
     */
    @Override
    public Map<Integer, MathModel> loadMathModels() {

        try{
            LOGGER.info("Loading Math Models...");

            //Create the object
            Map<Integer, MathModel> mathModels = new ConcurrentHashMap<>();

            //Build the mathModels folder path
            String mathModelsPath = new ClassPathResource(mathModelsFolder).getFile().getPath();

            //Get the names of all math models in the folder
            List<String> mathModelFileNames = fileService.fetchFileNamesFromDirectory(mathModelsPath, ".xml");

            if (!mathModelFileNames.isEmpty()) {

                //Load the contents of each math model configuration file
                for (String mathModelFileName : mathModelFileNames) {

                    //Parse the XML into an Object
                    MathModel mathModel = (MathModel) XmlUtils.xmlFileToObj(mathModelsPath, mathModelFileName, MathModel.class);

                    //Ignore math models that have already been loaded
                    if(mathModels.containsKey(mathModel.getId())) {
                        LOGGER.warn("Math model [{}] [{}] has been loaded already.", mathModel.getId(), mathModel.getName());
                    }
                    else {
                        //Put it into the map
                        mathModels.put(mathModel.getId(), mathModel);
                        LOGGER.info("Math Model [{}] [{}] loaded succesfully", mathModel.getId(), mathModel.getName());
                    }
                }
            }

            //Load reels in memory
            LOGGER.info("Math Models loaded successfully  --> {}", mathModels);

            return mathModels;
        }
        catch (IOException e){
            LOGGER.error("IOException while parsing MathModelConfigData", e);
            throw new ParseException("IOException while parsing MathModelConfigData", e);
        }
    }


}
