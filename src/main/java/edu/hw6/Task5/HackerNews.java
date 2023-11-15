package edu.hw6.Task5;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static java.net.http.HttpClient.newHttpClient;
import static java.time.temporal.ChronoUnit.SECONDS;

public final class HackerNews {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String API_TOP_STORIES_HANDLE = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final String API_STORY_HANDLE_TEMPLATE = "https://hacker-news.firebaseio.com/v0/item/%d.json";
    private static final Pattern JSON_PATTERN = Pattern.compile("\"title\":\"(.*?)\"");
    private static final Duration TIMEOUT_DURATION_SEC = Duration.of(10, SECONDS);
    private static final long[] NO_NEWS = {};
    private static final String NO_NEWS_NAME = "";

    private HackerNews() {
        throw new UnsupportedOperationException("This is a utility class");
    }

    private static long[] parseArray(String s) {
        String withoutBrackets = s.substring(1, s.length() - 1);
        String[] numbersString = withoutBrackets.split(",");
        return Stream.of(numbersString).mapToLong(Long::valueOf).toArray();
    }

    private static String parseNewsJSON(String s) {
        Matcher matcher = JSON_PATTERN.matcher(s);
        return matcher.find() ? matcher.group(1) : NO_NEWS_NAME;
    }

    public static long[] hackerNewsTopStories() {
        final HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(API_TOP_STORIES_HANDLE))
            .GET()
            .timeout(TIMEOUT_DURATION_SEC)
            .build();
        try (var client = newHttpClient()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return parseArray(response.body());
        } catch (Exception e) {
            LOGGER.error("Error occurred while sending top stories request: " + e);
            return NO_NEWS;
        }
    }

    public static String news(long id) {
        String url = String.format(API_STORY_HANDLE_TEMPLATE, id);
        final HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .timeout(TIMEOUT_DURATION_SEC)
            .build();
        try (var client = newHttpClient()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return parseNewsJSON(response.body());
        } catch (Exception e) {
            LOGGER.error("Error occurred while sending news name: " + e);
            return NO_NEWS_NAME;
        }
    }
}
