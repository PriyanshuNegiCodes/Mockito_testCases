package com.example.Master.Domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@EqualsAndHashCode

public class Artist {
    private int artistId;
    private String artistName;
}
