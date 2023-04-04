package com.example.Master.Services;

import com.example.Master.Domain.Track;
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
    public Track saveTrack(Track track) {
        return iTrackRepository.save(track);
    }

    @Override
    public boolean deleteTrack(int id) {
        iTrackRepository.deleteById(id);
        return true;
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
