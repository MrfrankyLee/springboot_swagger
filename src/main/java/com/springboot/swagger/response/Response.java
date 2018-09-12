package com.springboot.swagger.response;

        import java.io.Serializable;

/**
 * @author lixiaole
 * @date 2018/9/12 14:40
 */
public final class Response<T> implements Serializable {

    private static final long serialVersionUID = -3185905635633652543L;

    private  Boolean isSuccees;
    private  T date;

    public Boolean getSuccees() {
        return isSuccees;
    }

    public void setSuccees(Boolean succees) {
        isSuccees = succees;
    }

    public  T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Response{" +
                "isSuccees=" + isSuccees +
                ", date=" + date +
                '}';
    }
}
