package com.example.inhousenavigationsystem.persistence.entity;

import com.example.inhousenavigationsystem.domain.model.MobileStationModel;
import com.example.inhousenavigationsystem.domain.model.MobileStationStatus;
import com.example.inhousenavigationsystem.persistence.audit.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "MOBILE_STATION")
@SequenceGenerator(name = MobileStationEntity.SEQUENCE_GENERATOR_NAME, sequenceName = "SEQ_MS", allocationSize = 1)
public class MobileStationEntity extends Auditable {
    public static final String SEQUENCE_GENERATOR_NAME = "mobileStationSequenceGenerator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR_NAME)
    @Column(name = "ID")
    private Long id;

    @Builder.Default
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "mobile_station_id")
    private Set<MobileStationLocationEntity> locations = new HashSet<>();

    @NotBlank(message = "Name is required")
    @Size(max = 512, message = "Name max length is 512 characters")
    @Column(name = "NAME", unique = true)
    private String name;

    @NotNull(message = "Model is required")
    @Column(name = "MODEL")
    @Enumerated(EnumType.STRING)
    private MobileStationModel model;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private MobileStationStatus status;
}
