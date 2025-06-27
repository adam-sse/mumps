package de.uni_hildesheim.mump.dto;

import java.time.ZonedDateTime;
import java.util.List;

public record EventDto(long id, ZonedDateTime startTime, List<String> visitors) {

}
