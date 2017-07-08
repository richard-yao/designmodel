package com.tvunetworks.richardyao.algorithm;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Dec 28, 2016 2:18:59 PM
 */
public class TestMain {
	
	public static void main(String[] args) {
		ReverseGame reverseGame = new ReverseGame();
		int[] inputArray = {3,2,1,6,5,4,9,8,7,0};
		reverseGame.runGame(inputArray, 10);
		//diguiMethod(0);
	}
	
	public static void diguiMethod(int step) {
		if(step > 20) {
			System.out.println(step+"");
			return;
		}
		diguiMethod(step+1);
		System.out.println(step+"");
	}

}
