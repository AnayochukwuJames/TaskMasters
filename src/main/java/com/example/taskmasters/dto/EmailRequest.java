package com.example.taskmasters.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailRequest {
    private String recipient;
    private String messageBody;
    private String subject;
    private String attachment;
}
