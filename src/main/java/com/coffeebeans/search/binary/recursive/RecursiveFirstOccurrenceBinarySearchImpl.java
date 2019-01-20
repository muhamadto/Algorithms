package com.coffeebeans.search.binary.recursive;

import com.coffeebeans.search.binary.BinarySearch;

 class RecursiveFirstOccurrenceBinarySearchImpl<T extends Comparable<T>> implements BinarySearch<T> {

    @Override
    public int search(T t, T[] values) {
        if (values == null || t == null) {
            return -1;
        }

        return findFirstOccurrence(t, values, 0, values.length - 1);
    }

    private int findFirstOccurrence(final T t, final T[] values, final int start, final int end) {
        if (start > end) {
            return -1;
        }

        int retval = -1;

        // Unsigned right shit, shifting to the right with zero.
        int mid = (start + end) >>> 1; // ((start + end) / 2) may produce overflow exception, example start = (Integer.MAX_VALUE - 9) and end = Integer.MAX_VALUE

        int possibleValue;
        if (t.compareTo(values[mid]) == 0) {
            retval = mid;
            possibleValue =  findFirstOccurrence(t, values, start, mid - 1);
        } else if (t.compareTo(values[mid]) > 0) {
            possibleValue =  findFirstOccurrence(t, values, mid + 1, end);
        } else {
            possibleValue =  findFirstOccurrence(t, values, start, mid - 1);
        }

        return possibleValue == -1 ? retval : possibleValue;
    }
}
