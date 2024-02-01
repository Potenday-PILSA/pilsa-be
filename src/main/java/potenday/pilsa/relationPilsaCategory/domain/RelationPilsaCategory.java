package potenday.pilsa.relationPilsaCategory.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsaCategory.domain.PilsaCategory;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "relationPilsaCategory")
public class RelationPilsaCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pilsa_id")
    private Pilsa pilsa;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_cd")
    private PilsaCategory category;
}