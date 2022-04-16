package cn.nlj.dormitorysystemserver.controller;

import cn.nlj.dormitorysystemserver.common.Common;
import cn.nlj.dormitorysystemserver.entity.Admin;
import cn.nlj.dormitorysystemserver.entity.Student;
import cn.nlj.dormitorysystemserver.entity.User;
import cn.nlj.dormitorysystemserver.mapper.StudentMapper;
import cn.nlj.dormitorysystemserver.service.StudentService;
import cn.nlj.dormitorysystemserver.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;
    @GetMapping("/student/getOneApartmentStudents/{apartment}")
    public ResultVO getOneApartmentStudents(@PathVariable("apartment") String apartment) {
        List<Student> students = studentService.getStudent(Map.of("dormitory_building", apartment));
        return ResultVO.success(Map.of("students", students));
    }
}
