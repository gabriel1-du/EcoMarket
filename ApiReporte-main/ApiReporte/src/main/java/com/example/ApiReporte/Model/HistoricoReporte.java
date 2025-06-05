package com.example.ApiReporte.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "historicoreporte")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoReporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hist_id")
    private Integer histId;

    @ManyToOne
    @JoinColumn(name = "reporte_id", nullable = false)
    private DefinicionReporte reporte; //Cracion de la coneccion con la tabla reporte id

    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;

    @Column(name = "ejecutado_en", nullable = false)
    private LocalDateTime ejecutadoEn = LocalDateTime.now(); //Local date Time para que se ponga la fecha de cuando se cree
}
