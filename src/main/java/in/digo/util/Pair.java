package in.digo.util;

import static java.util.Objects.requireNonNull;

import java.util.function.BiConsumer;

public class Pair<T, U> {
    public final T _1;
    public final U _2;

    private Pair(T t, U u) {
        this._1 = requireNonNull(t);
        this._2 = requireNonNull(u);
    }

    public static <T, U> Pair<T, U> of(T t, U u) {
        return new Pair<>(t, u);
    }

    public void accept(BiConsumer<T, U> c) {
        c.accept(_1, _2);
    }

    @Override
    public int hashCode() {
        return (_1.hashCode() << 13) ^ _1.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || other.getClass() != this.getClass()) return false;
        Pair<?, ?> that = (Pair<?, ?>) other;
        return this._1.equals(that._1) && this._2.equals(that._2);
    }

    @Override
    public String toString() {
        return "(:" + _1 + "," + _2 + ":)";
    }
}
