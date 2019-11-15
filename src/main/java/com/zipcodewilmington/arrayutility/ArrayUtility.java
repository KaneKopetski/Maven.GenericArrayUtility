package com.zipcodewilmington.arrayutility;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<T> {
    private T[] array;

    public ArrayUtility(T[] inputArray) {
        this.array = inputArray;
    }

    public Integer countDuplicatesInMerge(T[] arrayToMerge, T valueToEvaluate) {
        this.array = mergeArray(arrayToMerge);
        return countDuplicates(valueToEvaluate);
    }

    public T[] removeValue(T valueToRemove) {
        return Arrays.stream(this.array)
                .filter(item -> !item.equals(valueToRemove))
                .toArray(this::getNewArray);
    }

    private T[] getNewArray(Integer size){
        return (T[])Array.newInstance(this.array.getClass().getComponentType(), size);
    }

    private Integer countDuplicates(T valueToEvaluate){
        return (int)Arrays.stream(this.array)
                .filter(item -> item.equals(valueToEvaluate))
                .count();
    }

    private T[] mergeArray(T[] arrayToMerge){
        return Stream.concat(Arrays.stream(this.array), Arrays.stream(arrayToMerge))
                .toArray(this::getNewArray);
    }

    private int getMaxDuplicateCount(){
        return Arrays.stream(this.array)
                .map(item -> countDuplicates(item))
                .max(Comparator.naturalOrder()).orElse(0);
    }

    public Integer getNumberOfOccurrences(T value) {
        return (int)Arrays.stream(this.array)
                .filter(item -> item.equals(value))
                .count();
    }

    public T getMostCommonFromMerge(T[] arrayToMerge) {
        this.array = mergeArray(arrayToMerge);
        Integer max = getMaxDuplicateCount();
        return Arrays.stream(array)
                .filter(item -> getNumberOfOccurrences(item) >= max)
                .findFirst().orElse(null);
    }

}
