package com.example.inhousenavigationsystem.persistence.audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable {
	@NotNull(message = "Timestamp of creation is required")
	@PastOrPresent(message = "Timestamp must be valid")
	@CreatedDate
	@Column(name = "CREATED_AT")
	private Instant createdAt;

	@NotNull(message = "Timestamp of last modification is required")
	@PastOrPresent(message = "Timestamp must be valid")
	@LastModifiedDate
	@Column(name = "MODIFIED_AT")
	private Instant modifiedAt;
}
