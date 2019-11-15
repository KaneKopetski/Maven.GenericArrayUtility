package com.zipcodewilmington.arrayutility;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<E> {
    List<E> inputList;
    List<E> listToMerge;
    List<E> mergedList;

    public ArrayUtility(E[] inputArray) {
        this.inputList = Arrays.asList(inputArray);
    }

    public Integer countDuplicatesInMerge(E[] arrayToMerge, E valueToEvaluate) {
        listToMerge = Arrays.asList(arrayToMerge);
        mergeLists();
        Integer count = 0;
        for (E element : mergedList) {
            if (element.equals(valueToEvaluate)) {
                count++;
            }
        }
        return count;
    }

    public List<E> mergeLists(){
        mergedList = Stream.of(inputList, listToMerge)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());
        return mergedList;
    }

    public void printLists() {
        for (E element1 : inputList) {
            System.out.println(element1);
        }

        for (E element2 : listToMerge) {
            System.out.println(element2);
        }

        for (E element3 : mergedList) {
            System.out.println(element3);
        }
    }
}
