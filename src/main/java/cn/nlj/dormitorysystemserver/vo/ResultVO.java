package cn.nlj.dormitorysystemserver.vo;

public class ResultVO<T> {
    private int code;
    private String message;
    public T date;


    public ResultVO(int code, String message, T date) {
        this.code = code;
        this.message = message;
        this.date = date;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getDate() {
        return date;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(T date) {
        this.date = date;
    }
}
