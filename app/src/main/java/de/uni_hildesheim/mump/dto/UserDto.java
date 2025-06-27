package de.uni_hildesheim.mump.dto;

import java.util.Set;

public record UserDto(String userID, String eMail, boolean isLecturer, int points, Set<CourseDto> enlistedCourses) {

}
