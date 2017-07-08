package com.tvunetworks.richardyao.algorithm;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Feb 16, 2017 11:09:03 AM
 */
public class MaxHeap {
	
	int heapSize;
	int[] heap;
	
	public MaxHeap(int[] array) {
		this.heap = array;
		this.heapSize = array.length;
	}
	
	/**
	 * 构建最大堆数据结构
	 */
	public void buildMaxHead() {
		for(int i = heapSize/2 - 1; i >= 0; i--) {
			maxify(i);
		}
	}
	
	/**
	 * 进行堆排序
	 * 排序时将根节点移到最后,堆的大小减一, 并重新构建最大堆
	 */
	public void heapSort() {
		for(int i=0;i<heap.length;i++) {
			int temp = heap[0];
			heap[0] = heap[heapSize - 1];
			heap[heapSize - 1] = temp;
			heapSize--;
			maxify(0);
		}
	}
	
	/**
	 * 依次向上将当前子树最大堆化, 当前节点分别和节点左右子树相比较,最大值为根节点
	 * 直至largest的左右节点是叶子节点为止
	 * @param i
	 */
	public void maxify(int i) {
		int left = left(i);
		int right = right(i);
		int largest;
		
		if(left < heapSize && heap[left] > heap[i]) {
			largest = left;
		} else {
			largest = i;
		}
		if(right < heapSize && heap[right] > heap[largest]) {
			largest = right;
		}
		if(largest == i || largest >= heapSize) { //largest等于i说明i是最大元素
			return;
		}
		int temp = heap[i];
		heap[i] = heap[largest];
		heap[largest] = temp;
		maxify(largest);
	}
	
	/**
	 * 在堆中替换某个元素后重新构建最大堆
	 * @param i
	 * @param value
	 */
	public void increaseValue(int i, int value) {
		heap[i] = value;
		if(i >= heapSize || i <= 0) {
			return;
		}
		int p = parent(i);
		if(heap[p] >= value) {
			return;
		}
		heap[i] = heap[p];
		increaseValue(p, value);
	}
	
	private int parent(int i) {
		return (i-1)/2;
	}
	
	private int left(int i) {
		return 2*(i+1)-1;
	}
	
	private int right(int i) {
		return 2*(i+1);
	}

}
