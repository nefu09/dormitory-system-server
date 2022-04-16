package cn.nlj.dormitorysystemserver.mapper;

import cn.nlj.dormitorysystemserver.entity.Admin;
import cn.nlj.dormitorysystemserver.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
}
