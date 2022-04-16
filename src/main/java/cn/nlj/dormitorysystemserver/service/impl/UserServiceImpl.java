package cn.nlj.dormitorysystemserver.service.impl;

import cn.nlj.dormitorysystemserver.entity.User;
import cn.nlj.dormitorysystemserver.mapper.UserMapper;
import cn.nlj.dormitorysystemserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getUser(Map query) {
        return userMapper.selectByMap(query);
    }
}
