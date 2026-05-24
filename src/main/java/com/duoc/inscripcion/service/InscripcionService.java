package com.duoc.inscripcion.service;

import com.duoc.inscripcion.dto.CursoResumen;
import com.duoc.inscripcion.dto.InscripcionRequest;
import com.duoc.inscripcion.dto.InscripcionResponse;
import com.duoc.inscripcion.model.Curso;
import com.duoc.inscripcion.model.Inscripcion;
import com.duoc.inscripcion.repository.InscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final CursoService cursoService;

    /**
     * Inscribe a un estudiante en los cursos indicados,
     * calcula el total y persiste en Oracle Cloud.
     */
    public InscripcionResponse inscribir(InscripcionRequest request) {

        // 1. Obtener los cursos seleccionados
        List<Curso> cursos = request.getCursosIds().stream()
                .map(cursoService::buscarPorId)
                .collect(Collectors.toList());

        // 2. Calcular total a pagar
        double total = cursos.stream()
                .mapToDouble(Curso::getCosto)
                .sum();

        // 3. Crear y persistir la inscripción
        Inscripcion inscripcion = Inscripcion.builder()
                .estudianteId(request.getEstudianteId())
                .cursos(cursos)
                .totalPagar(total)
                .build();

        Inscripcion guardada = inscripcionRepository.save(inscripcion);

        // 4. Armar respuesta con resumen
        List<CursoResumen> resumen = cursos.stream()
                .map(c -> CursoResumen.builder()
                        .id(c.getId())
                        .nombre(c.getNombre())
                        .instructor(c.getInstructor())
                        .duracion(c.getDuracion())
                        .costo(c.getCosto())
                        .build())
                .collect(Collectors.toList());

        return InscripcionResponse.builder()
                .inscripcionId(guardada.getId())
                .estudianteId(guardada.getEstudianteId())
                .cursos(resumen)
                .totalPagar(total)
                .fechaInscripcion(guardada.getFechaInscripcion()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
                .build();
    }
}
