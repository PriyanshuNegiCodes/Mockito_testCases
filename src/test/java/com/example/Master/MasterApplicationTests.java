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
import org.springframework.boot.test.context.SpringBootTest;

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
		track=new Track(101, "song1", 4, artist);
		artist=new Artist(100, "Name1");
	}

	@AfterEach
	public void tearDown(){
		track=null;
		artist=null;
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
}
