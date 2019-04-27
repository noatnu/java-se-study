package org.sort;

/**
 * @author zhou
 * 冒泡排序
 */
public class BubbleSort {

    public static int[] sort(int[] arr) {
        int temp;
        for (int i = arr.length - 1; i > 0; i--) {// 趟数  (第一次选出最大的)
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    @Deprecated
    public static int[] sortBubble(int[] arr) {
        int temp;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j > i; j++) {
                if (arr[j - 1] > arr[j]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}
