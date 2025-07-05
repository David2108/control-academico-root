package com.web.app.controlacademico.academic.core.service;

import com.web.app.controlacademico.academic.core.dto.SubjectRequest;
import com.web.app.controlacademico.academic.core.dto.SubjectUpdateRequest;
import com.web.app.controlacademico.academic.core.entity.SubjectEntity;
import com.web.app.controlacademico.academic.core.mapper.ISubjectMapper;
import com.web.app.controlacademico.academic.core.repository.ISubjectRepository;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceTest {

    @Mock
    private ISubjectRepository repository;
    @Mock
    private ISubjectMapper mapper;

    @InjectMocks
    private SubjectServiceImpl service;

    private Faker faker;

    @BeforeEach
    void setup() {
        faker = new Faker();
    }

    @Test
    void shouldFindAllSubjectsSuccessfully() {

        List<SubjectEntity> entities = List.of(
                SubjectEntity.builder()
                        .code(faker.regexify("[A-Z]{3}[0-9]{3}"))
                        .name(faker.educator().course())
                        .credits(1)
                        .build()
        );

        when(repository.findAll()).thenReturn(entities);

        List<SubjectEntity> response = service.findAll();

        assertNotNull(response);
        assertEquals(entities.size(), response.size());
    }

    @Test
    void shouldFindSubjectByIdSuccessfully() {

        SubjectEntity entity = SubjectEntity.builder()
                .id(1L)
                .code(faker.regexify("[A-Z]{3}[0-9]{3}"))
                .name(faker.educator().course())
                .credits(1)
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        SubjectEntity response = service.findById(1L);

        assertNotNull(response);
        assertEquals(entity.getId(), response.getId());
    }

    @Test
    void shouldCreateSubjectSuccessfully() {

        SubjectRequest request = SubjectRequest.builder()
                .code(faker.regexify("[A-Z]{3}[0-9]{3}"))
                .name(faker.educator().course())
                .credits(1)
                .build();

        SubjectEntity entity = new SubjectEntity();
        entity.setCode(request.getCode());
        entity.setName(request.getName());
        entity.setCredits(request.getCredits());

        when(mapper.toEntity(request)).thenReturn(entity);
        when(repository.existsByCode(anyString())).thenReturn(false);
        when(repository.save(entity)).thenReturn(entity);

        SubjectEntity response = service.save(request);

        assertNotNull(response);
        assertEquals(request.getCode(), response.getCode());

    }

    @Test
    void shouldUpdateSubjectSuccessfully() {

        SubjectUpdateRequest request = SubjectUpdateRequest.builder()
                .code(faker.regexify("[A-Z]{3}[0-9]{3}"))
                .name(faker.educator().course())
                .credits(1)
                .build();

        SubjectEntity entity = new SubjectEntity();
        entity.setCode(request.getCode());
        entity.setName(request.getName());
        entity.setCredits(request.getCredits());

        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(entity);

        SubjectEntity response = service.update(request, 1L);

        assertNotNull(response);
        assertEquals(request.getCode(), response.getCode());

    }

}
