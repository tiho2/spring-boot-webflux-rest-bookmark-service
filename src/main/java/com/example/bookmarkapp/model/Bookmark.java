package com.example.bookmarkapp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Bookmark {

    @Id
    private String id;
    private String url;
    private Boolean shared;
    @Getter @Setter
    private String owner;
}
