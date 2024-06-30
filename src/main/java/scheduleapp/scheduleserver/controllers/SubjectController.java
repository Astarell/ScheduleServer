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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import scheduleapp.scheduleserver.models.Schedule;
import scheduleapp.scheduleserver.models.Subject;
import scheduleapp.scheduleserver.models.Teacher;
import scheduleapp.scheduleserver.projection_views.SubjectView;
import scheduleapp.scheduleserver.services.SubjectService;

import java.util.Optional;

@Tag(name = "Subject", description = "The Subject API")
@RestController
@RequestMapping(value = "/subject")
@Transactional
public class SubjectController implements BasicControllerOptions<SubjectView, Subject>{

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService){
        this.subjectService = subjectService;
    }

    @Operation(summary = "Gets all subject records")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Found subject records",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
    })
    @GetMapping
    public ResponseEntity<Iterable<SubjectView>> findAll() {
        return new ResponseEntity<>(subjectService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Gets the subject record by id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "The subject record has been found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "The subject record has not been found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<SubjectView> findById(
            @Parameter(name = "id", description = "Id of the subject to find", required = true)
            @PathVariable Long id) {

        if(subjectService.findById(id).isPresent()){
            return new ResponseEntity<>(subjectService.findById(id).get(), HttpStatus.OK);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("response-content", "NULL");

        return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Adds a subject record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Subject.class)
                    )
            ))
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody Subject entity) {
        subjectService.add(entity);
    }

    @Operation(
            summary = "Updates the subject record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Subject.class)
                    )
            )
    )
    @PostMapping("/update")
    public void update(@RequestBody Subject patchEntity) {
        subjectService.update(patchEntity);
    }

    @Operation(summary = "Deletes the subject record by id")
    @PostMapping("/delete/{id}")
    public void deleteById(
            @Parameter(name = "id", description = "Id of the subject to delete", required = true)
            @PathVariable Long id) {
        subjectService.deleteById(id);
    }

    @Operation(
            summary = "Deletes the subject record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Subject.class)
                    )
            ))
    @PostMapping("/delete")
    public void delete(@RequestBody Subject entity) {
        subjectService.delete(entity);
    }
}
