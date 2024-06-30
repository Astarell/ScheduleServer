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
import scheduleapp.scheduleserver.models.Schedule;
import scheduleapp.scheduleserver.models.SchoolClass;
import scheduleapp.scheduleserver.models.Teacher;
import scheduleapp.scheduleserver.models.Timepair;
import scheduleapp.scheduleserver.projection_views.SchoolClassView;
import scheduleapp.scheduleserver.repositories.SchoolClassRepository;
import scheduleapp.scheduleserver.services.SchoolClassService;

import java.util.Optional;

@Tag(name = "SchoolClass", description = "The school-class API")
@RestController
@RequestMapping("/sch-class")
@Transactional
public class SchoolClassController implements BasicControllerOptions<SchoolClassView, SchoolClass> {

    private final SchoolClassService schoolClassService;

    @Autowired
    public SchoolClassController(SchoolClassService schoolClassService){
        this.schoolClassService = schoolClassService;
    }

    @Operation(summary = "Gets all school-class records")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Found school-class records",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
    })
    @GetMapping
    public ResponseEntity<Iterable<SchoolClassView>> findAll() {
        return new ResponseEntity<>(schoolClassService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Gets the school-class record by id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "The school-class record has been found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "The school-class record has not been found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<SchoolClassView> findById(
            @Parameter(name = "id", description = "Id of the school-class to find", required = true)
            @PathVariable Long id) {

        if(schoolClassService.findById(id).isPresent()){
            return new ResponseEntity<>(schoolClassService.findById(id).get(), HttpStatus.OK);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("response-content", "NULL");

        return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Adds a school-class record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Timepair.class)
                    )
            ))
    @PostMapping
    public void add(@RequestBody SchoolClass entity) {
        schoolClassService.add(entity);
    }

    @Operation(
            summary = "Updates the school-class record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Timepair.class)
                    )
            ))
    @PostMapping("/update")
    public void update(@RequestBody SchoolClass patchEntity) {
        schoolClassService.update(patchEntity);
    }

    @Operation(summary = "Deletes the school-class record by id")
    @PostMapping("/delete/{id}")
    public void deleteById(
            @Parameter(name = "id", description = "Id of the school-class to delete", required = true)
            @PathVariable Long id) {
        schoolClassService.deleteById(id);
    }

    @Operation(
            summary = "Deletes the school-class record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Timepair.class)
                    )
            ))
    @PostMapping("/delete")
    public void delete(@RequestBody SchoolClass entity) {
        schoolClassService.delete(entity);
    }
}
