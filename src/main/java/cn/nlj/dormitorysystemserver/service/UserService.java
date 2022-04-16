package cn.nlj.dormitorysystemserver.service;

import cn.nlj.dormitorysystemserver.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getUser(Map map);
}
