package com.web.app.controlacademico.academic.core.service;

import com.web.app.controlacademico.academic.core.dto.ClassroomRequest;
import com.web.app.controlacademico.academic.core.dto.ClassroomResumeResponse;
import com.web.app.controlacademico.academic.core.entity.ClassroomEntity;
import com.web.app.controlacademico.academic.core.enums.StatusClassroomEnum;
import com.web.app.controlacademico.academic.core.enums.TypeClassroomEnum;
import com.web.app.controlacademico.academic.core.mapper.IClassroomMapper;
import com.web.app.controlacademico.academic.core.repository.IClassroomRepository;
import com.web.app.controlacademico.shared.exception.InvalidMappingException;
import com.web.app.controlacademico.shared.exception.NotFoundException;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClassroomServiceTest {

    @Mock
    private IClassroomRepository classroomRepository;
    @Mock
    private IClassroomMapper classroomMapper;

    @InjectMocks
    private ClassroomServiceImpl classroomService;

    private Faker faker;

    @BeforeEach
    void setup(){
        faker = new Faker();
    }

    @Test
    void shouldGetClassroomById(){
        Long id = 1L;
        ClassroomEntity entity = new ClassroomEntity();
        entity.setId(id);
        when(classroomRepository.findById(id)).thenReturn(Optional.of(entity));
        ClassroomEntity response = classroomService.getById(id);
        assertNotNull(response);
        assertEquals(id, response.getId());
    }

    @Test
    void shouldThrownNotFoundExceptionWhenGetClassroomByIdIfNotExistData(){
        Long id = 1L;
        when(classroomRepository.findById(id)).thenThrow(new NotFoundException("Classroom not found"));
        assertThrows(NotFoundException.class, () -> classroomService.getById(id));
    }

    @Test
    void shouldCreateClassroom(){

        ClassroomRequest request = new ClassroomRequest(
                faker.regexify("[A-Z]{3}[0-9]{2}"),
                faker.educator().course(),
                faker.number().numberBetween(1, 50),
                faker.address().fullAddress(),
                TypeClassroomEnum.values()[faker.number().numberBetween(0, TypeClassroomEnum.values().length - 1)],
                null
        );

        ClassroomEntity entity = new ClassroomEntity();
        entity.setCode(request.getCode());
        entity.setName(request.getName());
        entity.setCapacity(request.getCapacity());
        entity.setLocation(request.getLocation());
        entity.setType(request.getType());
        entity.setStatus(StatusClassroomEnum.AVAILABLE);

        ClassroomResumeResponse responseService = ClassroomResumeResponse.builder()
                .code(request.getCode())
                .name(request.getName())
                .capacity(faker.number().numberBetween(1, 50))
                .location(faker.address().fullAddress())
                .type(request.getType())
                .status(StatusClassroomEnum.AVAILABLE)
                .build();

        when(classroomMapper.toEntity(request)).thenReturn(entity);
        when(classroomMapper.toDtoResponse(entity)).thenReturn(responseService);
        when(classroomRepository.save(any(ClassroomEntity.class))).thenReturn(entity);

        ClassroomEntity response = classroomService.save(request);

        assertNotNull(response);
        assertEquals(responseService.getCode(), response.getCode());
        assertEquals(responseService.getName(), response.getName());
        assertEquals(StatusClassroomEnum.AVAILABLE, response.getStatus());

    }

    @Test
    void shouldThrownExceptionWhenCreateClassroom(){

        ClassroomRequest request = new ClassroomRequest(
                faker.regexify("[A-Z]{3}[0-9]{2}"),
                faker.educator().course(),
                faker.number().numberBetween(1, 50),
                faker.address().fullAddress(),
                TypeClassroomEnum.values()[faker.number().numberBetween(0, TypeClassroomEnum.values().length - 1)],
                null
        );

        ClassroomEntity entity = new ClassroomEntity();
        entity.setCode(request.getCode());
        entity.setName(request.getName());
        entity.setCapacity(request.getCapacity());
        entity.setLocation(request.getLocation());
        entity.setType(request.getType());
        entity.setStatus(StatusClassroomEnum.AVAILABLE);

        when(classroomMapper.toEntity(request)).thenReturn(entity);
        when(classroomRepository.save(any(ClassroomEntity.class))).thenThrow(new DataIntegrityViolationException("Error"));

        assertThrows(DataIntegrityViolationException.class, () -> classroomService.save(request));

    }

    @Test
    void shouldThrownExceptionWhenCreateClassroomAndThereIsErrorInCastWithMapper(){

        ClassroomRequest request = new ClassroomRequest(
                faker.regexify("[A-Z]{3}[0-9]{2}"),
                faker.educator().course(),
                faker.number().numberBetween(1, 50),
                faker.address().fullAddress(),
                TypeClassroomEnum.values()[faker.number().numberBetween(0, TypeClassroomEnum.values().length - 1)],
                null
        );

        ClassroomEntity entity = new ClassroomEntity();
        entity.setCode(request.getCode());
        entity.setName(request.getName());
        entity.setCapacity(request.getCapacity());
        entity.setLocation(request.getLocation());
        entity.setType(request.getType());
        entity.setStatus(StatusClassroomEnum.AVAILABLE);

        when(classroomMapper.toEntity(request)).thenThrow(new InvalidMappingException("Error"));

        assertThrows(InvalidMappingException.class, () -> classroomService.save(request));

    }

    @Test
    void shouldGetClassroomList(){
        List<ClassroomEntity> classroomList = List.of(
                new ClassroomEntity(1L, "BIO101", "Aula 101", 50, "Edificion A", TypeClassroomEnum.PHYSICAL, StatusClassroomEnum.AVAILABLE, List.of(), List.of()),
                new ClassroomEntity(2L, "BIO102", "Aula 102", 50, "Edificion A", TypeClassroomEnum.VIRTUAL, StatusClassroomEnum.AVAILABLE, List.of(), List.of())
        );
        when(classroomRepository.findAll()).thenReturn(classroomList);
        List<ClassroomResumeResponse> response = classroomService.findAll();
        assertNotNull(response);
        assertEquals(classroomList.size(), response.size());
    }

    @Test
    void shouldUpdateClassroomSuccess() {

        ClassroomRequest request = ClassroomRequest.builder()
                .code("BIO101")
                .name("Aula 101")
                .capacity(50)
                .location("Edificion A")
                .type(TypeClassroomEnum.PHYSICAL)
                .status(StatusClassroomEnum.AVAILABLE)
                .build();

        Long id = 1L;

        ClassroomEntity entityUpdate = new ClassroomEntity();
        entityUpdate.setId(1L);
        entityUpdate.setCode("BIO101");
        entityUpdate.setName("Aula 101");
        entityUpdate.setCapacity(50);
        entityUpdate.setLocation("Edificion A");
        entityUpdate.setType(TypeClassroomEnum.PHYSICAL);
        entityUpdate.setStatus(StatusClassroomEnum.AVAILABLE);

        when(classroomRepository.findById(id)).thenReturn(Optional.of(entityUpdate));
        when(classroomRepository.save(any(ClassroomEntity.class))).thenReturn(entityUpdate);

        ClassroomEntity response = classroomService.update(request, id);

        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(request.getLocation(), response.getLocation());

    }

    @Test
    void shouldDeleteByIdSuccess() {

        Long id = 1L;

        assertDoesNotThrow(() -> classroomService.deleteById(id));

        verify(classroomRepository, times(1)).deleteById(id);

    }

    @Test
    void shouldThrownExceptionWhenDeleteByIdIfNotExistData() {

        Long id = 0L;

        assertThrows(IllegalArgumentException.class, () -> classroomService.deleteById(id));

        verify(classroomRepository, times(0)).deleteById(id);

    }
}
