package com.duoc.inscripcion.controller;

import com.duoc.inscripcion.dto.InscripcionRequest;
import com.duoc.inscripcion.dto.InscripcionResponse;
import com.duoc.inscripcion.service.InscripcionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inscripciones")
@RequiredArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;

    /**
     * POST /api/inscripciones
     * Inscribe a un estudiante en uno o más cursos.
     * Body: { "estudianteId": 1, "cursosIds": [1, 2, 3] }
     * Respuesta: resumen con cursos, costo individual y total a pagar.
     */
    @PostMapping
    public ResponseEntity<InscripcionResponse> inscribir(
            @Valid @RequestBody InscripcionRequest request) {

        InscripcionResponse response = inscripcionService.inscribir(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
