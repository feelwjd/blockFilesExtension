package com.example.blockfilesextension.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ResponseBody<T> {
    private T data;
}
