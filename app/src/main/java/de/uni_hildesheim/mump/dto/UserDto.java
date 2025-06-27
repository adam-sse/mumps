package de.uni_hildesheim.mump.dto;

import java.util.List;

public record UserDto(String userID, String eMail, int points, List<Long> enlistedCourses) {

}
