package com.week5.Homework.Sohel_Week5_Homework.advices;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {


//    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime timestamp;
    private T data;
    private ApiError error;


    public ApiResponse(){
        this.timestamp = LocalDateTime.now();
    }
    public ApiResponse(T data){
        this();
        this.data = data;
    }

    public ApiResponse(ApiError err){
        this();
        this.error = err;
    }

}
