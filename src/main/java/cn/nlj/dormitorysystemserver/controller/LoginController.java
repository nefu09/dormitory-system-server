package cn.nlj.dormitorysystemserver.controller;

import cn.nlj.dormitorysystemserver.common.Common;
import cn.nlj.dormitorysystemserver.entity.Admin;
import cn.nlj.dormitorysystemserver.entity.Student;
import cn.nlj.dormitorysystemserver.entity.User;
import cn.nlj.dormitorysystemserver.security.EncryptComponent;
import cn.nlj.dormitorysystemserver.service.AdminService;
import cn.nlj.dormitorysystemserver.service.StudentService;
import cn.nlj.dormitorysystemserver.service.impl.UserServiceImpl;
import cn.nlj.dormitorysystemserver.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@Slf4j
@RequestMapping("/api/")
public class LoginController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EncryptComponent encryptComponent;

    @PostMapping("/login")
    public ResultVO login(@RequestBody User user) {
        User record = userService.getUser(Map.of("user_name", user.getUserName())).get(0);
        if (record == null) {
            return ResultVO.error(Common.NoExistError.getCode(), Common.NoExistError.getMsg());
        }
        if (!passwordEncoder.matches(user.getPassword(), record.getPassword())) {
            return ResultVO.error(Common.LoginError.getCode(), Common.LoginError.getMsg());
        }
        String token = encryptComponent.encrypt(Map.of("id", record.getId()));
        if (record.isStudent()) {
            Student student = studentService.getStudent(Map.of("student_number", user.getUserName())).get(0);
            return ResultVO.success(Map.of("token", token, "isStudent", true, "studentInfo", student));
        } else {
            Admin admin = adminService.getAdmin(Map.of("admin_number", user.getUserName())).get(0);
            return ResultVO.success(Map.of("token", token, "isStudent", false, "adminInfo", admin));
        }
    }

    @GetMapping("/password/{password}")
    public String getPassword(@PathVariable("password") String password) {
        String result = passwordEncoder.encode(password);
        log.debug(result);
        return result;
    }
}
