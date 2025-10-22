package film_service.controller;

import film_service.dto.AttoreDto;
import film_service.service.AttoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attore")
public class AttoreController {

    private final AttoreService attoreService;

    @GetMapping
    @PreAuthorize("hasAuthority('VIEW')")
    public ResponseEntity<List<AttoreDto>> getAllAttori() {

        try {
            List<AttoreDto> attori = attoreService.getAllAttori();
            return ResponseEntity.ok(attori);
        }catch (RuntimeException e){
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VIEW')")
    public ResponseEntity<AttoreDto> getAttoreById(@PathVariable long id) {

        try {
            AttoreDto attore = attoreService.getAttoreById(id);
            return ResponseEntity.ok(attore);
        }catch (RuntimeException e){
            return ResponseEntity.noContent().build();
        }

    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<AttoreDto> saveAttore(@RequestBody @Valid AttoreDto attoreDto) {

        try {
            AttoreDto attore = attoreService.addAttore(attoreDto);
            return ResponseEntity.ok(attore);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<AttoreDto> updateAttore(@RequestBody @Valid AttoreDto attoreDto, @PathVariable long id) {

        try {
            AttoreDto attore = attoreService.updateAttore(attoreDto, id);
            return ResponseEntity.ok(attore);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<AttoreDto> deleteAttore(@PathVariable long id) {

        try {
            AttoreDto attore = attoreService.deleteAttore(id);
            return ResponseEntity.ok(attore);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().build();
        }

    }

}
