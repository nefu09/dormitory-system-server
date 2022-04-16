package cn.nlj.dormitorysystemserver.mapper;

import cn.nlj.dormitorysystemserver.entity.Student;
import cn.nlj.dormitorysystemserver.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
