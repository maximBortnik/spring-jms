package com.springjms.springjms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessedComment {
    private Comment comment;
    private Date date;
    private boolean confirm;
}
