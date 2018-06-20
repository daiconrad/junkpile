package in.digo.stream;

import static java.util.Objects.requireNonNull;

import java.util.regex.Pattern;
import java.util.regex.MatchResult;
import java.util.stream.Stream;

public class Matches {
    private final Pattern pattern;

    public Matches(String pattern) {
        this.pattern = Pattern.compile(requireNonNull(pattern));
    }

    public static Matches of(String pattern) {
        return new Matches(pattern);
    }

    public Stream<String> on(String haystack) {
        return pattern.matcher(haystack).results().map(MatchResult::group);
    }
}
