package de.uni_hildesheim.mump.dto;

import java.util.List;

public record CourseDto(long id, String name, int rewardPerEvent, List<EventDto> events) {

}
