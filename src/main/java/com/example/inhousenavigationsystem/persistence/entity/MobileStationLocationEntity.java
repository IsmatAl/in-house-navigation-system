package com.example.inhousenavigationsystem.persistence.entity;

import com.example.inhousenavigationsystem.persistence.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "MOBILE_STATION_LOCATION")
@SequenceGenerator(name = MobileStationLocationEntity.SEQUENCE_GENERATOR_NAME, sequenceName = "SEQ_MSL", allocationSize = 1)
public class MobileStationLocationEntity extends Auditable {
    public static final String SEQUENCE_GENERATOR_NAME = "mobileStationLocationSequenceGenerator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR_NAME)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "X is required")
    @Column(name = "X")
    private String x;

    @NotBlank(message = "Y is required")
    @Column(name = "Y")
    private String y;
}
