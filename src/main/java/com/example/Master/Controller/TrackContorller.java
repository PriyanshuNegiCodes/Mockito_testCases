package com.example.Master.Controller;

import com.example.Master.Domain.Track;
import com.example.Master.Exceptions.TrackAlreadyExistException;
import com.example.Master.Exceptions.TrackNotFoundExceptoin;
import com.example.Master.Services.ITrackServices;
import com.example.Master.Services.ITrackServicesImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/Track/v1")
public class TrackContorller {
    ITrackServices iTrackServices;

    @Autowired
    public TrackContorller(ITrackServicesImp iTrackServicesImp) {
        this.iTrackServices = iTrackServicesImp;
    }
    @PostMapping("/addTrack")
    public ResponseEntity<?> addTrack(@RequestBody Track track) throws TrackAlreadyExistException {
        return new ResponseEntity<>(iTrackServices.saveTrack(track), HttpStatus.CREATED);
    }
    @DeleteMapping("/deleteTrack/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable int id) throws TrackNotFoundExceptoin {
        return new ResponseEntity<>(iTrackServices.deleteTrack(id), HttpStatus.OK);
    }
    @GetMapping("/tracks")
    public ResponseEntity<?> getAllTrack(){
        return new ResponseEntity<>(iTrackServices.getAllTrack(), HttpStatus.OK);
    }
    @GetMapping("/filterRatings")
    public ResponseEntity<?> filterRatings(){
        return new ResponseEntity<>(iTrackServices.getFilterData(), HttpStatus.OK);
    }
    @GetMapping("/JustinTracks")
    public ResponseEntity<?> getJustinTracks(){
        return new ResponseEntity<>(iTrackServices.getJustin(), HttpStatus.OK);
    }
}
