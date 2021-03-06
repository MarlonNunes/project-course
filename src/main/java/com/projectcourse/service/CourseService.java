package com.projectcourse.service;

import com.projectcourse.dto.post.CoursePostDTO;
import com.projectcourse.model.Course;
import com.projectcourse.model.Teacher;
import com.projectcourse.repository.CourseRepository;
import com.projectcourse.repository.StudentRepository;
import com.projectcourse.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public List<Course> findAll() {

        return courseRepository.findAll();
    }

    public Course findById(Integer id){
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
    }

    public Course save(CoursePostDTO coursePostDTO){
        Course course = Course.builder().name(coursePostDTO.getName())
                .teacher(teacherRepository.findById(coursePostDTO.getTeacher().getIdTeacher()).get())
                .students(coursePostDTO.getStudents()).startDate(LocalDate.now()).build();

        return courseRepository.save(course);
    }

    public void delete(Integer id){
        courseRepository.deleteById(id);
    }

    public boolean verifyExists(Integer id){
        return courseRepository.existsById(id);
    }

    public Course Replace (Course course){

        return courseRepository.save(course);
    }

    public List<Course> findAllCoursesByTeacher(Integer idTeacher) {
        return teacherRepository.findById(idTeacher).get().getCourses();
    }

    public List<Course> findAllCoursesByStudent(Integer idStudent) {
        return studentRepository.findById(idStudent).get().getCourses();
    }
}

