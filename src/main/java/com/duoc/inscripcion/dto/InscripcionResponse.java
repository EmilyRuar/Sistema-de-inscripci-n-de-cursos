package com.duoc.inscripcion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InscripcionResponse {
    private Long inscripcionId;
    private Long estudianteId;
    private List<CursoResumen> cursos;
    private Double totalPagar;
    private String fechaInscripcion;
}
