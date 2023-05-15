package com.gabriel.slot.controller;

import com.gabriel.slot.domain.api.SpinRequest;
import com.gabriel.slot.domain.api.SpinResponse;
import com.gabriel.slot.domain.model.MathModel;
import com.gabriel.slot.domain.model.SpinResult;
import com.gabriel.slot.domain.model.SpinSimulation;
import com.gabriel.slot.exception.ResourceNotFoundException;
import com.gabriel.slot.service.SpinService;
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
    private transient SpinService spinService;

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
            @ApiResponse(responseCode = "500", description = "The server encountered a problem.", content = @Content) })

    @GetMapping(value = "/{gameId}/start", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MathModel> retrieveSlotDetails(@PathVariable int gameId) {
        return new ResponseEntity<>(mathModels.get(gameId), HttpStatus.OK);
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
            @ApiResponse(responseCode = "500", description = "The server encountered a problem.", content = @Content) })
    @PostMapping(path = "/{id}/spin/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpinResponse> spin(@PathVariable int gameId, @PathVariable int spinType, @RequestBody @Valid SpinRequest spinRequest) {
        MathModel mathModel = mathModels.get(gameId);

        if(mathModel == null){
            throw new ResourceNotFoundException("Game requested not found");
        }
        if (mathModel.getReelSets().get(spinType) == null) {
            throw new ResourceNotFoundException("Spin type not found");
        }

        SpinSimulation spinSimulation = spinService.simulateSpin(mathModel.getReelSets().get(spinType).getReels());
        SpinResult spinResult = spinService.processSpin(spinSimulation, spinRequest.getSpin());

        SpinResponse response = new SpinResponse();
        response.setSpinResult(spinResult);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

