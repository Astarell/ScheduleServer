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
import scheduleapp.scheduleserver.models.Teacher;
import scheduleapp.scheduleserver.projection_views.TeacherView;
import scheduleapp.scheduleserver.services.SubjectService;
import scheduleapp.scheduleserver.services.TeacherService;

import java.util.Optional;

@Tag(name = "Teacher", description = "The Teacher API")
@RestController
@RequestMapping("/teacher")
@Transactional
public class TeacherController implements BasicControllerOptions<TeacherView, Teacher> {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @Operation(summary = "Gets all teacher records")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Found teacher records",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
    })
    @GetMapping
    public ResponseEntity<Iterable<TeacherView>> findAll() {
        return new ResponseEntity<>(teacherService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Gets the teacher record by id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "The teacher record has been found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "The teacher record has not been found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<TeacherView> findById(
            @Parameter(name = "id", description = "Id of the teacher to find", required = true)
            @PathVariable Long id) {

        if(teacherService.findById(id).isPresent()){
            return new ResponseEntity<>(teacherService.findById(id).get(), HttpStatus.OK);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("response-content", "NULL");

        return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Adds a teacher record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Teacher.class)
                    )
            ))
    @PostMapping
    public void add(@RequestBody Teacher entity) {
        teacherService.add(entity);
    }

    @Operation(
            summary = "Updates the teacher record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Teacher.class)
                    )
            ))
    @PostMapping("/update")
    public void update(@RequestBody Teacher patchEntity) {
        teacherService.update(patchEntity);
    }

    @Operation(summary = "Deletes the teacher record by id")
    @PostMapping("/delete/{id}")
    public void deleteById(
            @Parameter(name = "id", description = "Id of the teacher to delete", required = true)
            @PathVariable Long id) {
        teacherService.deleteById(id);
    }

    @Operation(
            summary = "Deletes the teacher record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Teacher.class)
                    )
            ))
    @PostMapping("/delete")
    public void delete(@RequestBody Teacher entity) {
        teacherService.delete(entity);
    }
}
