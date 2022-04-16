package cn.nlj.dormitorysystemserver.common;

public enum Common {
    NoExistError("账号不存在,请联系管理员",1001),
    LoginError("账号或密码错误",1002),
    NotLoginError("未登录",1003);
    private String msg;
    private int code;

    Common(String msg,int code){
       this.msg=msg;
       this.code=code;
    }
    public String getMsg(){
        return msg;
    }
    public int getCode(){
        return code;
    }
}
