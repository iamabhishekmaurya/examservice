package com.exam.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    private String title;
    private String description;
    private Integer maxMark;
    private Integer numberOfQuestion;
    private Long catId;
    private Long userId;
}
