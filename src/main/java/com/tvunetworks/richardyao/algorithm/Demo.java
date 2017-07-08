package com.tvunetworks.richardyao.algorithm;

/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Feb 16, 2017 11:41:34 AM
 */
public class Demo {

	public static void main(String[] args) {
		int[] array = new int[] { 1, 2, 3, 4, 7, 8, 9, 10, 14, 16 };
		MaxHeap heap = new MaxHeap(array);
		System.out.println("执行最大堆化前堆的结构：");
		printHeapTree(heap.heap);
		heap.buildMaxHead();
		System.out.println("执行最大堆化后堆的结构：");
		printHeapTree(heap.heap);
		heap.increaseValue(7, 17);
		System.out.println("替换指定位置值后最大堆的结构：");
		printHeapTree(heap.heap);
		heap.heapSort();
		System.out.println("执行堆排序后数组的内容");
		printHeap(heap.heap);
	}

	private static void printHeapTree(int[] array) {
		for (int i = 1; i < array.length; i = i * 2) {
			for (int k = i - 1; (k < 2 * i - 1) && (k < array.length); k++) {
				System.out.print(array[k] + " ");
			}
			System.out.println();
		}
	}

	private static void printHeap(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}
}
