package com.duoc.inscripcion.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InscripcionRequest {

    @NotNull(message = "El ID del estudiante es obligatorio")
    private Long estudianteId;

    @NotEmpty(message = "Debe seleccionar al menos un curso")
    private List<Long> cursosIds;
}
