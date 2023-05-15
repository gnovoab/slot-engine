package com.gabriel.slot.controller;

import com.gabriel.slot.domain.api.SpinRequest;
import com.gabriel.slot.domain.api.SpinResponse;
import com.gabriel.slot.domain.model.Reel;
import com.gabriel.slot.domain.model.SpinResult;
import com.gabriel.slot.domain.model.SpinSimulation;
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
    private transient Map<Integer, Reel> reelSet;

    @Autowired
    private transient SpinService spinService;

    /**
     * Retrieve reelset
     * @return
     */
    @Operation(
            summary = "Retrieve the slot reelset",
            description = "Get the generated reelset at starup. The response is all the reels from the slot machine.",
            tags = { "reelset", "get" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Success", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class)) }),
            @ApiResponse(responseCode = "500", description = "The server encountered a problem.", content = @Content) })

    @GetMapping(value = "/reels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map> retrieveReelSet() {
        return new ResponseEntity<>(reelSet, HttpStatus.OK);
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
    @PostMapping(path = "/spin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpinResponse> spin(@RequestBody @Valid SpinRequest spinRequest) {
        SpinSimulation spinSimulation = spinService.simulateSpin();
        SpinResult spinResult = spinService.processSpin(spinSimulation, spinRequest.getSpin());

        SpinResponse response = new SpinResponse();
        response.setSpinResult(spinResult);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

