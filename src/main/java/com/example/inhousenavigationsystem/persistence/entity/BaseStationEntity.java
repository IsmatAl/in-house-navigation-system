package com.example.inhousenavigationsystem.persistence.entity;

import com.example.inhousenavigationsystem.persistence.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
@Table(name = "BASE_STATION")
@SequenceGenerator(name = BaseStationEntity.SEQUENCE_GENERATOR_NAME, sequenceName = "SEQ_BS", allocationSize = 1)
public class BaseStationEntity extends Auditable {
    public static final String SEQUENCE_GENERATOR_NAME = "baseStationSequenceGenerator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR_NAME)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 512, message = "Name max length is 512 characters")
    @Column(name = "NAME", unique = true)
    private String name;

    @NotBlank(message = "Coordinate is required")
    @Column(name = "X", unique = true)
    private String x;

    @NotBlank(message = "Coordinate  is required")
    @Column(name = "Y", unique = true)
    private String y;

    @NotNull(message = "Detection radius is required")
    @Min(value = 0, message = "Detection radius cannot be negative")
    @Column(name = "DETECTION_RADIUS_IN_METER")
    private float detectionRadiusInMeters;
}
