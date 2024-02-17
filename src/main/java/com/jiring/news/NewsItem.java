package com.jiring.news;

import lombok.AllArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
@AllArgsConstructor
public class NewsItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String headline;
    private int priority;


}