package de.uni_hildesheim.mump.dto;

import java.util.List;

public record CourseDto(long id, String name, String owner, int rewardPerEvent, List<EventDto> events) {
    
}
