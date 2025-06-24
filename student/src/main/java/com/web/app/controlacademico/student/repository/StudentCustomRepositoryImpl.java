package com.web.app.controlacademico.student.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class StudentCustomRepositoryImpl implements IStudentCustomRepository{

    @PersistenceContext
    private EntityManager em;

}
