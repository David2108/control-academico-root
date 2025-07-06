package com.web.app.controlacademico.academic.core.service;

import com.web.app.controlacademico.academic.core.entity.ClassroomEntity;
import com.web.app.controlacademico.academic.core.entity.CourseClassroomScheduleEntity;
import com.web.app.controlacademico.academic.core.entity.CourseEntity;
import com.web.app.controlacademico.academic.core.repository.ICourseClassroomScheduleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseClassroomScheduleServiceImplTest {

    @Mock
    private ICourseClassroomScheduleRepository courseClassroomScheduleRepository;

    @InjectMocks
    private CourseClassroomScheduleServiceImpl courseClassroomScheduleServiceImpl;

    @Test
    void shouldAddClassroomToCourse() {
        CourseEntity course = new CourseEntity();
        ClassroomEntity classroom = new ClassroomEntity();

        when(courseClassroomScheduleRepository.save(any(CourseClassroomScheduleEntity.class)))
                .thenReturn(new CourseClassroomScheduleEntity());

        courseClassroomScheduleServiceImpl.addClassroomToCourse(course, classroom);

        verify(courseClassroomScheduleRepository, times(1))
                .save(any(CourseClassroomScheduleEntity.class));
    }

}
