package scheduleapp.scheduleserver.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scheduleapp.scheduleserver.DTOs.ScheduleDTO;
import scheduleapp.scheduleserver.models.Schedule;
import scheduleapp.scheduleserver.models.Timepair;
import scheduleapp.scheduleserver.projection_views.ScheduleView;
import scheduleapp.scheduleserver.services.ScheduleService;

import java.util.*;

@Tag(name = "Schedule", description = "The schedule API")
@RestController
@RequestMapping("/schedule")
@Transactional
public class ScheduleController implements BasicControllerOptions<ScheduleView, Schedule> {
    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    @Operation(summary = "Gets all schedules records")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Found all schedules records",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
    })
    @GetMapping
    public ResponseEntity<Iterable<ScheduleView>> findAll() {
        return new ResponseEntity<>(scheduleService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Gets the schedule record by id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the schedule record by id",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "The schedule record has not been found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleView> findById(
            @Parameter(name = "id", description = "Id of the schedule record to find", required = true)
            @PathVariable Long id) {

        if(scheduleService.findById(id).isPresent()){
            return new ResponseEntity<>(scheduleService.findById(id).get(), HttpStatus.OK);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("response-content", "NULL");

        return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Adds a schedule record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ScheduleDTO.class))
            ))
    @PostMapping
    public void add(@RequestBody Schedule entity) {
        scheduleService.add(entity);
    }

    @Operation(
            summary = "Updates a schedule record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ScheduleDTO.class))
            ))
    @PostMapping("/update")
    public void update(@RequestBody Schedule patchEntity) {
        scheduleService.update(patchEntity);
    }

    @Operation(summary = "Deletes the schedule record by id")
    @PostMapping("/delete/{id}")
    public void deleteById(
            @Parameter(name = "id", description = "Id of the schedule record to delete", required = true)
            @PathVariable Long id) {
        scheduleService.deleteById(id);
    }

    @Operation(
            summary = "Deletes the schedule record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ScheduleDTO.class))
            ))
    @PostMapping("/delete")
    public void delete(@RequestBody Schedule entity) {
        scheduleService.delete(entity);
    }
}
