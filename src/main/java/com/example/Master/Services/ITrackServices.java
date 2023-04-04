package com.example.Master.Services;

import com.example.Master.Domain.Track;
import com.example.Master.Repository.ITrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface ITrackServices {
    Track saveTrack(Track track);
    boolean deleteTrack(int id);
    List<Track> getAllTrack();
    List<Track> getFilterData();
    List<Track> getJustin();
}
