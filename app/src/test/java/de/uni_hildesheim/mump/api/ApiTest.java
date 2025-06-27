package de.uni_hildesheim.mump.api;

import org.junit.Test;

import java.io.IOException;

import de.uni_hildesheim.mump.dto.CourseDto;

public class ApiTest {

    @Test
    public void test() throws IOException {
        System.out.println(Api.INSTANCE.getCourse(1));
    }
}
