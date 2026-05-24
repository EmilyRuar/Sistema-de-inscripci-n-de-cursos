package com.duoc.inscripcion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoResumen {
    private Long id;
    private String nombre;
    private String instructor;
    private Integer duracion;
    private Double costo;
}
