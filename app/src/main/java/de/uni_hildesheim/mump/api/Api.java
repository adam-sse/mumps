package de.uni_hildesheim.mump.api;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import de.uni_hildesheim.mump.dto.CourseDto;
import de.uni_hildesheim.mump.dto.UserDto;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Api {

    public static final Api INSTANCE = new Api("https://praktikum.sse.uni-hildesheim.de/mumps");

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeTypeAdapter())
            .create();

    private static final Type COURSE_LIST = new TypeToken<List<CourseDto>>() {}.getType();

    private String baseUrl;

    public Api(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<CourseDto> getAllCourses() throws IOException {
        return get("/course", COURSE_LIST);
    }

    public CourseDto getCourse(long id) throws IOException {
        return get("/course/" + id, CourseDto.class);
    }

    public List<UserDto> getAllUsers() throws IOException {
        return List.of(
                new UserDto("adam", "a@dam", false, 1252, Set.of()),
                new UserDto("marcel", "a@dam", false, 235, Set.of()),
                new UserDto("michael", "a@dam", false, 2342, Set.of()),
                new UserDto("ani", "a@dam", false, 3534, Set.of())
        );
    }

    private <T> T get(String endpoint, Type resultType) throws IOException {
        URL url = new URL(baseUrl + endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("accept", "application/json");

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                .collect(Collectors.joining("\n"));

        return GSON.fromJson(output, resultType);
    }

    private <T> T post(String endpoint, Object data, Type resultType) throws IOException {
        URL url = new URL(baseUrl + endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestProperty("content-type", "application/json");
        con.setRequestProperty("accept", "application/json");
        con.getOutputStream().write(GSON.toJson(data).getBytes(StandardCharsets.UTF_8));
        con.getOutputStream().close();
        return GSON.fromJson(new InputStreamReader(con.getInputStream()), resultType);
    }

}
