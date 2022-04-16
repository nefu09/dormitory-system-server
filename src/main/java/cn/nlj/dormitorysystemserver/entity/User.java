package cn.nlj.dormitorysystemserver.entity;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {

    private String id;
    private String userName;
    private String password;
    private boolean isStudent;
}
