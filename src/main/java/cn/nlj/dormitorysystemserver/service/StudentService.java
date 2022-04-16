package cn.nlj.dormitorysystemserver.service;

import cn.nlj.dormitorysystemserver.entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    List<Student> getStudent(Map map);

    List<Student> getOneApartmentStudentsByStudentNumber(String studentNumber);
}
