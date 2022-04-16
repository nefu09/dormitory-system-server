package cn.nlj.dormitorysystemserver.service.impl;

import cn.nlj.dormitorysystemserver.entity.Admin;
import cn.nlj.dormitorysystemserver.mapper.AdminMapper;
import cn.nlj.dormitorysystemserver.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    @Override
    public List<Admin> getAdmin(Map query) {
        return adminMapper.selectByMap(query);
    }
}
