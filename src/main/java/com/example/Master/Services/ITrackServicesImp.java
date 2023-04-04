package com.example.Master.Services;

import com.example.Master.Domain.Track;
import com.example.Master.Exceptions.TrackAlreadyExistException;
import com.example.Master.Exceptions.TrackNotFoundExceptoin;
import com.example.Master.Repository.ITrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ITrackServicesImp implements ITrackServices{
    ITrackRepository iTrackRepository;

    @Autowired
    public ITrackServicesImp(ITrackRepository iTrackRepository) {
        this.iTrackRepository = iTrackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistException {
        if(iTrackRepository.findById(track.getTrackId()).isEmpty()){
            return iTrackRepository.insert(track);
        }else{
            throw new TrackAlreadyExistException();
        }
    }

    @Override
    public boolean deleteTrack(int id) throws TrackNotFoundExceptoin {
        if(iTrackRepository.findById(id).isEmpty()){
            throw new TrackNotFoundExceptoin();
        }else {
            return true;
        }
    }

    @Override
    public List<Track> getAllTrack() {
        return iTrackRepository.findAll();
    }

    @Override
    public List<Track> getFilterData() {
        return iTrackRepository.getFilterRatings();
    }

    @Override
    public List<Track> getJustin() {
        return iTrackRepository.getJustinTracks();
    }
}
