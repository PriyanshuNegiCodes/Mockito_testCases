package com.example.Master.Services;

import com.example.Master.Domain.Track;
import com.example.Master.Exceptions.TrackAlreadyExistException;
import com.example.Master.Exceptions.TrackNotFoundExceptoin;
import com.example.Master.Repository.ITrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface ITrackServices {
    Track saveTrack(Track track) throws TrackAlreadyExistException;
    boolean deleteTrack(int id) throws TrackNotFoundExceptoin;
    List<Track> getAllTrack();
    List<Track> getFilterData();
    List<Track> getJustin();
}
