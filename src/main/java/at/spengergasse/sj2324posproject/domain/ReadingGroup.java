package at.spengergasse.sj2324posproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
//@Builder

@Entity
@Table(name="readingGroups")
public class ReadingGroup extends AbstractPersistable<Long> {
}
