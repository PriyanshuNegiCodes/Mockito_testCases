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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;


@ExtendWith(MockitoExtension.class)
class MasterApplicationTests {

	@Mock
	private ITrackRepository iTrackRepository;

	@InjectMocks
	private ITrackServicesImp iTrackServices;

	private Track track;
	private Artist artist;
	@BeforeEach
	public void setUp(){

		track=new Track(102, "song1", 4, new Artist(101, "Justin Beiber"));

	}

	@AfterEach
	public void tearDown(){
		track=null;
		artist=null;
	}

	@Test
	public void testAddTrackSuccess() throws TrackAlreadyExistException {
		when(iTrackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(null));
		when(iTrackRepository.insert(track)).thenReturn(track);
		Track insertedTrack=iTrackServices.saveTrack(track);
		assertEquals("inserted",insertedTrack, track);
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
		assertEquals("deleted",true, result);
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
	public void justinSuccess() throws TrackNotFoundExceptoin {
		iTrackRepository.insert(track);
		when(iTrackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(null));
//		when(iTrackRepository.getJustinTracks()).then();
		List<Track> fetchList=iTrackRepository.getJustinTracks();
		assertEquals("inserted","Justin Beiber", fetchList.get(0).getTrackArtist().getArtistName());

	}
}
