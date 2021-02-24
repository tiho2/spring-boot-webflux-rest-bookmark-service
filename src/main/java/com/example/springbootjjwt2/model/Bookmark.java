package com.example.springbootjjwt2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Bookmark {
    private String url;
    private Boolean shared = Boolean.FALSE;
}
