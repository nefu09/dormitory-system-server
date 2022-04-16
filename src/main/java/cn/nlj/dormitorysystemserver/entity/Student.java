package cn.nlj.dormitorysystemserver.entity;


import lombok.*;

@Data
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Student {
    //学生学号
    private String studentNumber;
    //学生姓名
    private String name;
    //学生公寓楼
    private String dormitoryBuilding;
    //学生寝室号
    private Integer dormitoryNumber;
}
