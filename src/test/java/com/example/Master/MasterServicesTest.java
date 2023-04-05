package com.example.Master;

import com.example.Master.Domain.Artist;
import com.example.Master.Domain.Track;
import com.example.Master.Repository.ITrackRepository;
import com.example.Master.Services.ITrackServicesImp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class MasterServicesTest {
    @Autowired
    private ITrackRepository iTrackRepository;

    private Track track;
    private Artist artist;
    private Track track1;
    private Artist artist1;
    @BeforeEach
    public void setUp(){
        track=new Track(101, "song1", 4, artist);
        artist=new Artist(100, "Name1");
        track1=new Track(102, "song2", 4, artist1);
        artist1=new Artist(101, "Justin Bieber");
    }

    @AfterEach
    public void tearDown(){
        track=null;
        artist=null;
        track1=null;
        artist1=null;
        iTrackRepository.deleteAll();
    }

    @Test
    public void testAddTrackSuccess(){
        assertEquals("Inserted", iTrackRepository.save(track), track);
    }
    @Test
    public void testAddTrackFail(){
        assertNotEquals("Inserted", iTrackRepository.save(track), track1);
    }
    @Test
    public void deleteTrackSuccess(){
        iTrackRepository.insert(track);
        iTrackRepository.insert(track1);
        iTrackRepository.deleteById(track.getTrackId());
        List<Track> fetchTracks=iTrackRepository.findAll();
        assertEquals("Deleted",1,fetchTracks.size() );
    }
    @Test
    public void deleteTrackFail(){
        iTrackRepository.insert(track);
        iTrackRepository.insert(track1);
        iTrackRepository.deleteById(track.getTrackId());
        List<Track> fetchTracks=iTrackRepository.findAll();
        assertEquals("Deleted",1,fetchTracks.size() );
    }
    @Test
    public void getByName(){
        iTrackRepository.insert(track);
        iTrackRepository.insert(track1);
        List<Track> fetchTracks=iTrackRepository.getJustinTracks();
        String temp=fetchTracks.get(0).getTrackArtist().getArtistName();
        assertEquals("Fetched","Justin Bieber",temp);

    }


}
