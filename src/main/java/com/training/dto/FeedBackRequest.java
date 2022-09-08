package com.training.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class FeedBackRequest {

    private int productId;
    private String comment;
    private double ratings;
}
