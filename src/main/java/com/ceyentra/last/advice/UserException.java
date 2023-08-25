package com.ceyentra.last.advice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserException extends RuntimeException{
    private int status;
    private String msg;



    public UserException(String message) {
        super(message);
        msg = message;
    }

    public UserException(String message, int status) {
        super(message);
        this.status = status;
        this.msg = message;
    }


}
