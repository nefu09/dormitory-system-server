package cn.nlj.dormitorysystemserver.service.impl;

import cn.nlj.dormitorysystemserver.entity.Student;
import cn.nlj.dormitorysystemserver.entity.User;
import cn.nlj.dormitorysystemserver.mapper.StudentMapper;
import cn.nlj.dormitorysystemserver.mapper.UserMapper;
import cn.nlj.dormitorysystemserver.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> getStudent(Map query) {
        return studentMapper.selectByMap(query);
    }

    @Override
    public List<Student> getOneApartmentStudentsByStudentNumber(String studentNumber) {
        Student student = studentMapper.selectByMap(Map.of("student_number",studentNumber)).get(0);
        List<Student> students =  studentMapper.selectByMap(Map.of("dormitory_building",student.getDormitoryBuilding()));
        return students;
    }
}
