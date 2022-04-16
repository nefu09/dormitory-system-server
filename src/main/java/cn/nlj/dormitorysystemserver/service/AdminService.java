package cn.nlj.dormitorysystemserver.service;

import cn.nlj.dormitorysystemserver.entity.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {

    List<Admin> getAdmin(Map map);
}
