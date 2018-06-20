package in.digo.stream;

import static java.util.Spliterator.NONNULL;
import static java.util.Spliterator.ORDERED;
import static java.util.Spliterator.SIZED;

import in.digo.util.Pair;

import java.util.List;
import java.util.ListIterator;
import java.util.Spliterators.AbstractSpliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Zip<T> extends AbstractSpliterator<Pair<Integer, T>> {
    private final ListIterator<T> iter;

    private Zip(List<T> list) {
        super(list.size(), ORDERED | NONNULL | SIZED);
        iter = list.listIterator();
    }

    public static <T> Stream<Pair<Integer, T>> withIndex(List<T> list) {
        return StreamSupport.stream(new Zip<>(list), false);
    }

    public static <T> Stream<Pair<Integer, T>> withIndexParallel(List<T> list) {
        return StreamSupport.stream(new Zip<>(list), true);
    }

    @Override
    public boolean tryAdvance(Consumer<? super Pair<Integer, T>> sink) {
        if (iter.hasNext()) {
            sink.accept(Pair.of(iter.nextIndex(), iter.next()));
            return true;
        }
        return false;
    }
}
