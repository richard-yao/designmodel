package com.tvunetworks.richardyao.algorithm;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Dec 29, 2016 11:14:55 AM
 */
public class StatisticSameLevel {
	
	public static int[] paraArray = {1,2,3,4,5,6,7,8,9};
	
	public static void statisticSpecifyLevel(int level, int[] paras) {
		int sum = 0;
		for(int i=0;i<paras.length;i++) {
			if(paras[i] >= level) {
				sum += paras[i];
			}
		}
		float temp = Float.parseFloat(sum+"")/Float.parseFloat(sumVal(paras)+"");
		double val = Math.floor(temp*100)/100;
		System.out.println(val);
	}
	
	public static int sumVal(int[] paras) {
		int count = 0;
		for(int i=0;i<paras.length;i++) {
			count += paras[i];
		}
		return count;
	}
	
	public static void main(String[] args) {
		statisticSpecifyLevel(5, paraArray);
	}

}
