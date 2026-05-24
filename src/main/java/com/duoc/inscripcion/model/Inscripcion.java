package com.duoc.inscripcion.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "INSCRIPCIONES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ESTUDIANTE_ID", nullable = false)
    private Long estudianteId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "INSCRIPCION_CURSOS",
        joinColumns = @JoinColumn(name = "inscripcion_id"),
        inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private List<Curso> cursos;

    @Column(name = "TOTAL_PAGAR", nullable = false)
    private Double totalPagar;

    @Column(name = "FECHA_INSCRIPCION", nullable = false)
    private LocalDateTime fechaInscripcion;

    @PrePersist
    public void prePersist() {
        this.fechaInscripcion = LocalDateTime.now();
    }
}
