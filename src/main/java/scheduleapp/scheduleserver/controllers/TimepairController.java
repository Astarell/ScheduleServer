package scheduleapp.scheduleserver.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperties;
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
import scheduleapp.scheduleserver.models.Schedule;
import scheduleapp.scheduleserver.models.Timepair;
import scheduleapp.scheduleserver.projection_views.TimepairView;
import scheduleapp.scheduleserver.services.SubjectService;
import scheduleapp.scheduleserver.services.TimepairService;

import java.util.Optional;

@Tag(name = "Timepair", description = "The Timepair API")
@RestController
@RequestMapping("/timepair")
@Transactional
public class TimepairController implements BasicControllerOptions<TimepairView, Timepair>{

    private final TimepairService timepairService;

    @Autowired
    public TimepairController(TimepairService timepairService){
        this.timepairService = timepairService;
    }

    @Operation(summary = "Gets all timepair")
    @ApiResponse(
            responseCode = "200",
            description = "Found all timepairs",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    @GetMapping
    public ResponseEntity<Iterable<TimepairView>> findAll() {
        return new ResponseEntity<>(timepairService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Gets timepair by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found timepair by id",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Timepair with such id does not exist"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<TimepairView> findById(
            @Parameter(name = "id", description = "Id of the timepair to find", required = true)
            @PathVariable Long id) {

        if(timepairService.findById(id).isPresent()){
            return new ResponseEntity<>(timepairService.findById(id).get(), HttpStatus.OK);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("response-content", "NULL");

        return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Adds a new timepair",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Timepair.class))
            ))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Added new timepair"
            )
    })
    @PostMapping
    public void add(@RequestBody Timepair entity) {
        timepairService.add(entity);
    }

    @Operation(
            summary = "Updates the timepair record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Timepair.class))
    ))
    @PostMapping("/update")
    public void update(@RequestBody Timepair patchEntity) {
        timepairService.update(patchEntity);
    }

    @Operation(summary = "Deletes the timepair record by id")
    @PostMapping("/delete/{id}")
    public void deleteById(
            @Parameter(name = "id", description = "Id of the timepair to delete", required = true)
            @PathVariable Long id) {
        timepairService.deleteById(id);
    }

    @Operation(
            summary = "Deletes the timepair record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Timepair.class))
            ))
    @PostMapping("/delete")
    public void delete(@RequestBody Timepair entity) {
        timepairService.delete(entity);
    }
}
