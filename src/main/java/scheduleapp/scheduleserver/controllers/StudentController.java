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
import scheduleapp.scheduleserver.DTOs.StudentDTO;
import scheduleapp.scheduleserver.models.Student;
import scheduleapp.scheduleserver.models.Teacher;
import scheduleapp.scheduleserver.projection_views.StudentView;
import scheduleapp.scheduleserver.services.StudentService;

import java.util.Optional;

// ПРОБЛЕМА С СОХРАНЕНИЕМ СТУДЕНТА,
// ПЕРЕЗАПИСЫВАЮТСЯ ШКОЛЬНЫЕ КЛАССЫ,
// А НУЖНО ПЕРЕДАВАТЬ ТОЛЬКО АЙДИ ДЛЯ ЗАПИСИ
@Tag(name = "Student", description = "The Student API")
@RestController
@RequestMapping("/student")
@Transactional
public class StudentController implements BasicControllerOptions<StudentView, Student> {

    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @Operation(summary = "Gets all students records")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Found all students records",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
    })
    @GetMapping
    public ResponseEntity<Iterable<StudentView>> findAll(){
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Gets the student record by id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "The student record has been found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "The student record has not been found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<StudentView> findById(
            @Parameter(name = "id", description = "Id of the student to find", required = true)
            @PathVariable Long id){

        if(studentService.findById(id).isPresent()){
            return new ResponseEntity<>(studentService.findById(id).get(), HttpStatus.OK);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("response-content", "NULL");

        return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Adds a student record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StudentDTO.class)
                    )
            ))
    @PostMapping
    public void add(@RequestBody Student student){
        studentService.add(student);
    }

    @Operation(
            summary = "Updates the student record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StudentDTO.class)
                    )
            ))
    @PostMapping("/update")
    public void update(@RequestBody Student student){
        studentService.update(student);
    }

    @Operation(
            summary = "Deletes the student record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StudentDTO.class)
                    )
            ))
    @PostMapping("/delete")
    public void delete(@RequestBody Student student){
        studentService.delete(student);
    }

    @Operation(summary = "Deletes the student record by id")
    @PostMapping("/delete/{id}")
    public void deleteById(
            @Parameter(name = "id", description = "Id of the student to delete", required = true)
            @PathVariable Long id){
        studentService.deleteById(id);
    }
}
