package com.web.app.controlacademico.academic.core.entity;

import com.web.app.controlacademico.academic.core.enums.StatusClassroomEnum;
import com.web.app.controlacademico.academic.core.enums.TypeClassroomEnum;
import com.web.app.controlacademico.shared.core.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "classrooms")
public class ClassroomEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @NotNull
    @Min(value = 1)
    @Column(nullable = false)
    private Integer capacity;

    @NotEmpty
    @Column(nullable = false)
    private String location;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeClassroomEnum type;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusClassroomEnum status;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ClassroomEntity that = (ClassroomEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
