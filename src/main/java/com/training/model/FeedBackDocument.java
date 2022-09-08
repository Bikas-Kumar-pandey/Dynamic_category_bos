package com.training.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Document(collection = "feedback")
@Data
@NoArgsConstructor
public class FeedBackDocument {
    @Id
    private String id;

    private int productId;
    private String comment;
    private double ratings;
//    private Date  date = new Date();
}
