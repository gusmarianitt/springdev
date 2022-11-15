package com.springtest.dev2.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import com.springtest.dev2.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>{

    @Query(value = "select student.* from Student student", nativeQuery = true)
    List<Student> findByNativeQuery();
    
    @Query(value = "select student.* from Student student where student.ages >= ?1", nativeQuery = true)
    List<Student> findByAgeGreaterThanEqual(Integer age);
    
    @Transactional
    @Modifying(flushAutomatically = true)
//    @QueryHints(value = { @QueryHint(name = org.hibernate.annotations.QueryHints.FLUSH_MODE, value = "COMMIT") })
    @Query(value = "update Student student set student.name = ?1 where student.id = ?2")
    void updateStudentById(String name, String id);
}
