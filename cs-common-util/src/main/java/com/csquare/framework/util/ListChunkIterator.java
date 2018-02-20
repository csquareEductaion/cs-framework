package com.csquare.framework.util;

import java.util.List;


public class ListChunkIterator<T> {

    /** Constants for CHUNK_LISTSIZE */
    private int chunkSize = 20;

    /** Constants for START_INDEX */
    private int fromIndex = 0;

    /** variable to hold dataList List<T>. */
    private final List<T> dataList;

    /** variable to hold dataListSize */
    private int dataListSize = 0;

    /**
     * @param totalList List
     */
    public ListChunkIterator(List<T> totalList, int chunkSize) {
        this.chunkSize = chunkSize;
        this.dataList = totalList;
        if (null == this.dataList) {
            dataListSize = 0;
            return;
        }
        dataListSize = this.dataList.size();
    }

    /**
     * Method Checks for Next Element present or not
     *
     * @return boolean of hasNext
     */
    public boolean hasNext() {

        if (0 == dataListSize) {
            return false;
        }

        if (fromIndex < dataListSize) {
            return true;
        }
        return false;

    }

    /**
     * Method returns the chunk of list elements
     *
     * @return List of Next set of Elements ArrayList
     */
    public List<T> getNext() {

        if (0 == dataListSize) {
            return null;
        }

        List<T> chunkOfPrimaryKeyRecordList = null;
        if (fromIndex < dataListSize) {
            int toIndex = fromIndex + chunkSize;
            if (toIndex > dataListSize) {
                toIndex = dataListSize;
            }
            chunkOfPrimaryKeyRecordList = dataList.subList(fromIndex, toIndex);

            fromIndex += chunkSize;
        }
        return chunkOfPrimaryKeyRecordList;

    }
}