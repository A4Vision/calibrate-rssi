package com.backtolife.survey.util;

// adapted from http://stackoverflow.com/a/3646398/1591777
public class ComparablePair<FIRST extends Comparable<FIRST>, SECOND extends Comparable<SECOND>>
        implements Comparable<ComparablePair<FIRST, SECOND>> {

    public final FIRST first;
    public final SECOND second;

    private ComparablePair(FIRST first, SECOND second) {
        this.first = first;
        this.second = second;
    }

    public static <FIRST extends Comparable<FIRST>, SECOND extends Comparable<SECOND>>
    ComparablePair<FIRST, SECOND> of(FIRST first, SECOND second) {
        return new ComparablePair<>(first, second);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(ComparablePair<FIRST, SECOND> o) {
        int cmp = compare(first, o.first);
        return cmp == 0 ? compare(second, o.second) : cmp;
    }


    private <T extends Comparable<T>> int compare(T o1, T o2) {
        if (o1 == null) {
            if (o2 == null) {
                return 0;
            } else {
                return -1;
            }
        } else if (o2 == null) {
            return +1;
        } else {
            return o1.compareTo(o2);
        }
    }

    @Override
    public int hashCode() {
        return 31 * hashcode(first) + hashcode(second);
    }

    // todo move this to a helper class.
    private static int hashcode(Object o) {
        return o == null ? 0 : o.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ComparablePair)) {
            return false;
        }
        //noinspection SimplifiableIfStatement
        if (this == obj) {
            return true;
        }
        return equal(first, ((ComparablePair) obj).first)
                && equal(second, ((ComparablePair) obj).second);
    }

    // todo move this to a helper class.
    private boolean equal(Object o1, Object o2) {
        return o1 == null ? o2 == null : (o1 == o2 || o1.equals(o2));
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ')';
    }
}