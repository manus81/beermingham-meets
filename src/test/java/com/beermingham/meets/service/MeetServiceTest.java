package com.beermingham.meets.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.beermingham.meets.application.dto.BeerDTO;
import com.beermingham.meets.application.dto.MeetDTO;
import com.beermingham.meets.application.dto.TemperatureDTO;
import com.beermingham.meets.application.exception.DuplicateEntityException;
import com.beermingham.meets.application.exception.ValidationException;
import com.beermingham.meets.application.service.IMeetService;
import com.beermingham.meets.domain.model.Meet;
import com.beermingham.meets.domain.model.User;
import com.beermingham.meets.domain.repository.IMeetRepository;
import com.beermingham.meets.infrastructure.repository.IMongoMeetRepository;
import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MeetServiceTest {

	@Autowired
	private IMeetService meetService;

	@Autowired
	private IMeetRepository meetRepository;

	@Autowired
	private IMongoMeetRepository iMongoMeetRepository;

	private static final LocalDate DATE_Meet = LocalDate.now();

	@Before
	public void setUp() {
		iMongoMeetRepository.deleteAll();
	}

	@Test
	public void createMeetSuccess() {
		MeetDTO meetDTO = buildMeetDTO();
		Meet newMeet = meetService.createNewMeet(meetDTO);
		Meet meetSaved = getMeetSave();
		Assert.assertEquals(3, newMeet.getGuests().size());
		// Asserts de que la meet up contiene los invitados que se mandaron a guardar
		Assert.assertTrue(meetSaved.getGuests().stream().anyMatch(meetGuest -> meetGuest.getId().equals("2")));
		Assert.assertTrue(meetSaved.getGuests().stream().anyMatch(meetGuest -> meetGuest.getId().equals("3")));
		Assert.assertTrue(meetSaved.getGuests().stream().anyMatch(meetGuest -> meetGuest.getId().equals("4")));
	}

	@Test(expected = DuplicateEntityException.class)
	public void createSameMeetTwice() {
		this.createMeetSuccess();
		this.createMeetSuccess();
	}

	@Test
	public void closeMeetSuccess() {
		this.createMeetSuccess();
		Meet meetSaved = getMeetSave();
		this.meetService.closeMeet(meetSaved.getId());
		meetSaved = getMeetSave();
		Assert.assertTrue(meetSaved.getBeersAmount() != null);
	}

	@Test
	public void getAllMeetsSuccess() {
		this.createMeetSuccess();
		MeetDTO meetDto = buildMeetDTO();
		meetDto.setDate(LocalDate.now().plusDays(1));
		meetService.createNewMeet(meetDto);
		Assert.assertEquals(2, meetRepository.getAll().size());
	}

	@Test
	public void createMeetWithUsersNotFound() {
		// TODO
		// Agrego usuarios que no existen
//		List<String> guestsNotInvited = Lists.newArrayList("2", "3", "4", "11", "12");
//		MeetDTO meetDto = new MeetDTO(DATE_Meet, guestsNotInvited);
//		Meet newMeetResultDto = meetService.createMeet(meetDto);
//		List<Meet> meets = meetRepository.getAll();
//		Assert.assertTrue(meets.stream().filter(meet -> meet.getDate().isEqual(DATE_Meet)).findFirst().isPresent());
//		List<Long> guestIdsNotFound = newMeetResultDto.getDetails().get("The next users dont exist");
//		Assert.assertTrue(guestIdsNotFound.size() == 2);
//		Assert.assertTrue(guestIdsNotFound.contains(11l));
//		Assert.assertTrue(guestIdsNotFound.contains(12l));
	}

	@Test
	public void getBeersBoxAmount() {
		this.closeMeetSuccess();
		Meet meetSaved = getMeetSave();
		Assert.assertTrue(meetSaved.getBeersAmount() == 6);
		BeerDTO beerDto = this.meetService.getBeerBoxesAmount(meetSaved.getId());
		// Al ser 6 birras, se necesita una caja de birras
		Assert.assertEquals(Integer.valueOf(1), beerDto.getBeerBoxes());
	}

	@Test(expected = ValidationException.class)
	public void getBeersBoxAmountWithMeetNotClosed() {
		this.createMeetSuccess();
		Meet meetSaved = getMeetSave();
		this.meetService.getBeerBoxesAmount(meetSaved.getId());
	}

	@Test
	public void getTemperatureTest() {
		this.createMeetSuccess();
		Meet meetSaved = getMeetSave();
		TemperatureDTO temperatureDto = this.meetService.getTemperature(meetSaved.getId());
		Assert.assertEquals(Double.valueOf("28.04"), temperatureDto.getTemperature());
	}

	@Test
	public void joinMeetSuccessful() {
		this.createMeetSuccess();
		Meet meetSaved = getMeetSave();
		// Assert de que la primer meet tiene 3 usuarios guardados
		Assert.assertEquals(3, meetSaved.getGuests().size());
		// Se agrega el usuario con id 5 a la meet
		this.meetService.addUser(meetSaved.getId(), "5");
		// Despues de unirse a la meet, pasa a tener 4 invitados
		meetSaved = getMeetSave();
		Assert.assertEquals(4, meetSaved.getGuests().size());
		Assert.assertTrue(meetSaved.getGuests().stream().anyMatch(meetGuest -> meetGuest.getId().equals("5")));
	}

	@Test
	public void checkinUser() {
		this.createMeetSuccess();

		Meet meetSaved = getMeetSave();
		Assert.assertEquals(3, meetSaved.getGuests().size());

		User user = meetSaved.getGuests().iterator().next();
		Assert.assertFalse(user.isCheckin());

		this.meetService.closeMeet(meetSaved.getId());
		this.meetService.checkinUser(meetSaved.getId(), user.getId());

		meetSaved = getMeetSave();
		Optional<User> userOption = meetSaved.getGuests().stream().filter(u -> u.getId().equals(user.getId()))
				.findFirst();
		Assert.assertTrue(userOption.isPresent());
		Assert.assertTrue(userOption.get().isCheckin());
	}

	private Meet getMeetSave() {
		List<Meet> meets = meetRepository.getAll();
		Meet meetSaved = meets.stream().filter(meet -> meet.getDate().isEqual(DATE_Meet)).findFirst().get();
		return meetSaved;
	}

	private MeetDTO buildMeetDTO() {
		MeetDTO meetDto = new MeetDTO(DATE_Meet, Lists.newArrayList("2", "3", "4"));
		return meetDto;
	}
}
