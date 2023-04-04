package com.example.Master.Repository;

import com.example.Master.Domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface ITrackRepository extends MongoRepository<Track, Integer> {
    @Query("{'trackRating':{$gt:4}}")
    List<Track> getFilterRatings();
    @Query("{'trackArtist.artistName':'Justin Bieber'}")
    List<Track> getJustinTracks();
}
