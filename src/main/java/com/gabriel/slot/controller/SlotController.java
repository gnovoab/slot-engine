package com.gabriel.slot.controller;

import com.gabriel.slot.domain.dto.api.ApiErrorResponse;
import com.gabriel.slot.domain.dto.api.SpinRequest;
import com.gabriel.slot.domain.dto.api.SpinResponse;
import com.gabriel.slot.domain.dto.api.StartResponse;
import com.gabriel.slot.domain.model.SpinResult;
import com.gabriel.slot.domain.model.SpinSimulation;
import com.gabriel.slot.domain.model.game.SlotGame;
import com.gabriel.slot.domain.model.mathmodel.MathModel;
import com.gabriel.slot.exception.ResourceNotFoundException;
import com.gabriel.slot.service.SpinService;
import com.gabriel.slot.service.ValidatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Endpoint for Slot
 */
@Tag(name = "SLOT", description = "Operations related to Slot")

@RestController
@RequestMapping("/api/v1/slot")
public class SlotController {

    //Fields
    @Autowired
    private transient Map<Integer, MathModel> mathModels;

    @Autowired
    private transient Map<Integer, SlotGame> gamesCatalog;

    @Autowired
    private transient SpinService spinService;

    @Autowired
    private transient ValidatorService validatorService;

    /**
     * Retrieve reelset
     * @return
     */
    @Operation(
            summary = "Retrieve game settings from an id provided",
            description = "Get the mathmodel details for a game. The response is all the reels from the slot machine.",
            tags = { "reelset", "get" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Success", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Resource Not Found", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "The server encountered a problem.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)) }) })

    @GetMapping(value = "/{gameId}/start", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StartResponse> retrieveSlotDetails(@PathVariable("gameId") int gameId) {

        //Get the slot game from id given
        SlotGame slotGameSettings = gamesCatalog.get(gameId);

        if(slotGameSettings == null) {
            throw new ResourceNotFoundException("Game not found");
        }

        //Get the MM details for the game
        MathModel mathModel = mathModels.get(slotGameSettings.getMathModel());

        //Create the payload
        StartResponse response = new StartResponse();
        response.setGameSettings(slotGameSettings);
        response.setReelSet(mathModel.getReelSets().get(0));
        response.setLines(mathModel.getLines());
        response.setWinLineSet(mathModel.getWinInfo().getWinLineSets().get(0));

        //Return the payload
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * Spin
     * @return
     */
    @Operation(
            summary = "Execute a spin",
            description = "Calculate the line from a spin, retrieving each symbol per reel",
            tags = { "spin", "post" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Spin executed successfully", content = { @Content(schema = @Schema(implementation = SpinResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Resource Not Found", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "The server encountered a problem.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponse.class)) }) })
    @PostMapping(path = "/{gameId}/spin/{spinType}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpinResponse> spin(@PathVariable int gameId,
                                             @PathVariable int spinType,
                                             @Valid @RequestBody SpinRequest spinRequest) {
        MathModel mathModel = mathModels.get(gameId);

        if(mathModel == null){
            throw new ResourceNotFoundException("Game requested not found");
        }

        if(spinType < 0 || spinType > mathModel.getReelSets().size()){
            throw new ResourceNotFoundException("Invalid Spin type");
        }

        //Validate entities
        validatorService.validate(spinRequest.getSpin());
        validatorService.validate(spinRequest.getPlayer());

        //Simulate a Spin
        SpinSimulation spinSimulation = spinService.simulateSpin(mathModel.getReelSets().get(spinType).getReels());

        //Evaluate spin simulated
        SpinResult spinResult = spinService.processSpin(spinSimulation.getBoard(), mathModel.getLines(), mathModel.getWinInfo().getWinLineSets().get(0).getWinLines(), spinRequest.getSpin());

        //Create the payload
        SpinResponse response = new SpinResponse();
        response.setSpinSimulation(spinSimulation);
        response.setSpinResult(spinResult);

        //Retuen the payload
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

