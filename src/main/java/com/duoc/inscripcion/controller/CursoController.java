package com.duoc.inscripcion.controller;

import com.duoc.inscripcion.model.Curso;
import com.duoc.inscripcion.service.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    /**
     * GET /api/cursos
     * Retorna la lista de todos los cursos disponibles.
     */
    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> cursos = cursoService.listarCursos();
        return ResponseEntity.ok(cursos);
    }

    /**
     * POST /api/cursos
     * Agrega un nuevo curso a la oferta educativa.
     * Body: { "nombre": "...", "instructor": "...", "duracion": 20, "costo": 49990 }
     */
    @PostMapping
    public ResponseEntity<Curso> agregarCurso(@Valid @RequestBody Curso curso) {
        Curso nuevo = cursoService.agregarCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
}
