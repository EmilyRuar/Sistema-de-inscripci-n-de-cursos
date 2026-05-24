package com.duoc.inscripcion.service;

import com.duoc.inscripcion.model.Curso;
import com.duoc.inscripcion.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    /**
     * Retorna todos los cursos disponibles.
     */
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    /**
     * Persiste un nuevo curso en Oracle Cloud y lo retorna.
     */
    public Curso agregarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    /**
     * Busca un curso por ID. Lanza excepción si no existe.
     */
    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));
    }
}
