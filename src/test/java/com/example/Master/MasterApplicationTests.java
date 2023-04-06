package com.example.Master;

import com.example.Master.Domain.Artist;
import com.example.Master.Domain.Track;
import com.example.Master.Exceptions.TrackAlreadyExistException;
import com.example.Master.Exceptions.TrackNotFoundExceptoin;
import com.example.Master.Repository.ITrackRepository;
import com.example.Master.Services.ITrackServices;
import com.example.Master.Services.ITrackServicesImp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MasterApplicationTests {

	@Mock
	private ITrackRepository iTrackRepository;

	@InjectMocks
	private ITrackServicesImp iTrackServices;

	private Track track;
	private Artist artist;
	private Track track1;
	@BeforeEach
	public void setUp(){

		track=new Track(102, "song1", 4, new Artist(101, "Justin Bieber"));
		track1=new Track(103, "song2", 4, new Artist(101, "Random-Name"));

	}

	@AfterEach
	public void tearDown(){
		track=null;
		artist=null;
		track1=null;
	}

	@Test
	public void testAddTrackSuccess() throws TrackAlreadyExistException {
		when(iTrackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(null));
		when(iTrackRepository.insert(track)).thenReturn(track);
		Track insertedTrack=iTrackServices.saveTrack(track);
		assertEquals(insertedTrack, track);
		verify(iTrackRepository, times(1)).findById(track.getTrackId());
		verify(iTrackRepository, times(1)).insert(track);
	}

	@Test
	public void testAddTrackFailure() throws TrackAlreadyExistException{
		when(iTrackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(track));
		assertThrows(TrackAlreadyExistException.class, ()->iTrackServices.saveTrack(track));

	}
	@Test
	public void testDeleteTrackSuccess() throws TrackNotFoundExceptoin {
		when(iTrackRepository.findById(track.getTrackId())).thenReturn(Optional.of(track));
//		when(iTrackRepository.deleteById(track.getTrackId())).thenReturn(true);
		boolean result=iTrackServices.deleteTrack(track.getTrackId());
		assertEquals(true, result);
		verify(iTrackRepository,times(1)).findById(track.getTrackId());
		verify(iTrackRepository,times(0)).deleteById(track.getTrackId());
	}
	@Test
	public void testDeleteTrackFailureCase() throws TrackNotFoundExceptoin {
		when(iTrackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(null));
		assertThrows(TrackNotFoundExceptoin.class, () -> iTrackServices.deleteTrack(track.getTrackId()));
		verify(iTrackRepository,times(1)).findById(track.getTrackId());
		verify(iTrackRepository,times(0)).deleteById(track.getTrackId());
	}
	@Test
	public void justinSuccess(){
		List<Track> tracks = new ArrayList<>();
		tracks.add(track);
		when(iTrackRepository.getJustinTracks()).thenReturn(tracks);
		List<Track> fetchList=iTrackServices.getJustin();
		assertEquals("Justin Bieber", fetchList.get(0).getTrackArtist().getArtistName());
	}
	@Test
	public void justinFail(){
		List<Track> tracks = new ArrayList<>();
		tracks.add(track1);
		when(iTrackRepository.getJustinTracks()).thenReturn(tracks);
		List<Track> fetchList=iTrackServices.getJustin();
		assertNotEquals("Justin Bieber", fetchList.get(0).getTrackArtist().getArtistName());
	}
}
