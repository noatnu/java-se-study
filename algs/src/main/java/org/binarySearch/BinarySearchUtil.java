package org.binarySearch;

import zch.pojo.User;

public class BinarySearchUtil {

    /**
     * 向后查找
     * @param arr
     * @param any
     * @return
     */
    public static Integer binarySearch_backward(int[] arr,int any){
        int low = 0,high = arr.length-1;

        while (low <= high ){
            int mid = (low+high) / 2;
            if (arr[mid] != any){
                low = mid +1;
            }else {
                return mid;
            }
        }
        return null;
    }

    /**
     * 向前查找
     * @param arr
     * @param any
     * @return
     */
    public static Integer binarySearch_forward(int[] arr,int any){
        int low = 0,high = arr.length-1;
        int flag;//debug 专用
        int mid = (low+high)/2;
        while (low <= high){
            flag = arr[mid];//debug 专用
            if (arr[mid] != any){
                mid--;
            }else {
                return mid;
            }
        }
        return null;
    }

    /**
     * 必须是要查找的数据已经排好序
     * @param arr
     * @param any
     * @return
     */
    public static Integer binarySearch(int[] arr,int any){
        int low = 0,high = arr.length-1;
        while (low <= high ){
            int mid = (low+high)/2;
            if (arr[mid]==any){
                return mid;
            }else if (any<arr[mid]){
                high = mid -1;
            }else {
                low = mid+1;
            }
        }
        return null;
    }

    public static Integer binarySearch(User[] arr,User user){
        int low = 0,high = arr.length-1;
        while (low<=high){
            int mid = (low+high)/2;
            if (arr[mid].compareTo(user)>0){
                high = mid - 1;
            }else if (arr[mid].compareTo(user)<0){
                low = mid + 1;
            }else {
                return mid;
            }
        }
        return null;
    }
}
