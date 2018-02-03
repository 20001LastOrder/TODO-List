package ca.mcgill.ecse321.eventregistration.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.Participant;
import ca.mcgill.ecse321.eventregistration.model.Registration;
import ca.mcgill.ecse321.eventregistration.model.RegistrationManager;
import ca.mcgill.ecse321.eventregistration.persistence.PersistenceXStream;

@Service
public class EventRegistrationService {
	private RegistrationManager rm;

	public EventRegistrationService(RegistrationManager rm) {
		this.rm = rm;
	}

	// Service for Participants
	public Participant createParticipant(String name) throws InvalidInputException {
		if (name == null || name.trim().length() == 0) {
			throw new InvalidInputException("Participant name cannot be empty!");
		}
		Participant p = new Participant(name);
		rm.addParticipant(p);
		PersistenceXStream.saveToXMLwithXStream(rm);
		return p;
	}

	public List<Participant> findAllParticipants() {
		return rm.getParticipants();
	}

	public List<Event> getEventsForParticipant(Participant p) {
		List<Registration> registations = rm.getRegistrations();
		List<Event> events = new ArrayList<Event>();
		for (Registration r : registations) {
			if (r.getParticipant().equals(p)) {
				events.add(r.getEvent());
			}
		}
		return events;
	}

	public Participant getParticipantByName(String name) throws InvalidInputException{
		List<Participant> participants = rm.getParticipants();
		
		for(Participant participant : participants) {
			if(participant.getName().equals(name)) {
				return participant;
			}
		}
		throw new InvalidInputException("Participant does not exist!!");
	}

	// Service for Events
	public Event createEvent(String name, Date date, Time startTime, Time endTime) throws InvalidInputException {
		if (name == null || date == null || startTime == null || endTime == null ) 
		{
			throw new InvalidInputException("Event name cannot be empty! Event date cannot be empty! Event start time cannot be empty! Event end time cannot be empty!");
		}
		else if(name.trim().length() == 0)
		{
			throw new InvalidInputException("Event name cannot be empty!");
		}
		else if(startTime.after(endTime))
		{
			throw new InvalidInputException("Event end time cannot be before event start time!");
		}
		
		Event e = new Event(name, date, startTime, endTime);
		rm.addEvent(e);
		PersistenceXStream.saveToXMLwithXStream(rm);
		return e;
	}
	
	public List<Event> findAllEvents(){
		return rm.getEvents();
	}
	
	public Event getEventByName(String name) throws InvalidInputException{
		List<Event> events = rm.getEvents();
		
		for(Event event : events) {
			if(event.getName().equals(name)) {
				return event;
			}
		}
		throw new InvalidInputException("Event does not exist!!");
	}

	//service for Registration
	public Registration register(Participant participant, Event event) throws InvalidInputException
	{
		if(participant == null || event == null) 
		{
			throw new InvalidInputException("Participant needs to be selected for registration! Event needs to be selected for registration!");
		}
		else if(!rm.getParticipants().contains(participant) || !rm.getEvents().contains(event))
		{
			throw new InvalidInputException("Participant does not exist! Event does not exist!");
		}
	
		Registration r = new Registration(participant, event);
		rm.addRegistration(r);
		PersistenceXStream.saveToXMLwithXStream(rm);
		return r;
	}




}
