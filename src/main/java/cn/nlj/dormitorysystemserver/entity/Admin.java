package cn.nlj.dormitorysystemserver.entity;

import lombok.*;


@Data
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Admin {
    //管理员号
    private String adminNumber;
    //管理员姓名
    private String name;
    //管理员管理公寓楼
    private Integer dormitoryBuilding;
}