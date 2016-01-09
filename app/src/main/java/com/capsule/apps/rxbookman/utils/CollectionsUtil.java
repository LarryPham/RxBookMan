package com.capsule.apps.rxbookman.utils;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * @Author: larry
 * History: 12/15/15.
 */
public class CollectionsUtil {

    private CollectionsUtil() {
        throw new AssertionError("Cannot instantiate new instance");
    }

    /**
     * Creates an empty {@code ArrayList} instance
     * Note: <p>If you need an immutable empty list, use {@link java.util.Collections#emptyList()}</p>
     * @return a newly-created, initially-empty {@code ArrayList}
     */
    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<E>();
    }

    /**
     * Creates an empty {@code LinkedList} instance.
     * Note: <p>If you need an <i>immutable</i> empty list, use {@link java.util.Collections#emptyList()}</p>
     * @return a newly-created, initially-empty {@code LinkedList}
     */
    public static <E> LinkedList<E> newLinkedList() {
        return new LinkedList<E>();
    }

    public static <K,V> HashMap<K,V> newHashMap() {
        return new HashMap<K,V>();
    }

    public static <K,V> Hashtable<K,V> newHashTable() {
        return new Hashtable<K,V>();
    }

    public static <K,V> ArrayMap<K,V> newArrayMap() {
        return new ArrayMap<K,V>();
    }
}
