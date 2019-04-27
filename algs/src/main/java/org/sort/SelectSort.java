package org.sort;

/**
 * 选择排序
 *
 * @author zhou
 */
public class SelectSort {
    public static int[] sort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {//趟数
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}
