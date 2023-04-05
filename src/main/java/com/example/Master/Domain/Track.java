package com.example.Master.Domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Document
@EqualsAndHashCode
public class Track {
    @Id
    private int trackId;
    private String trackName;
    private int trackRating;
    private Artist trackArtist;
}
