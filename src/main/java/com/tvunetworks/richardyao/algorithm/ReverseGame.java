package com.tvunetworks.richardyao.algorithm;
/**
 * @author RichardYao richardyao@tvunetworks.com
 * @date Dec 28, 2016 1:23:58 PM
 * 代码用来计算翻烙饼游戏, 游戏具体规则参见<编程之美>1.3节
 */
public class ReverseGame {
	
	public static int[] m_CakeArray;	//烙饼信息数组, 无序数组
	public static int m_nCakeCnt = 0;	//烙饼个数, 数组长度
	public static int m_nMaxSwap = 0;	//最多交换次数, 这里取(m_nCakeCnt-1) * 2
	public static int[] m_SwapArray;	//交换结果数组
	public static int[] m_ReverseCakeArray;	//当前翻转烙饼信息数组
	public static int[] m_ReverseCakeArraySwap;	//当前翻转烙饼交换结果数组
	public static int m_nSearch = 0;	//当前搜索次数
	
	/**
	 * 主程序执行入口
	 * @param pCakeArray 需要操作的数组
	 * @param nCakeCnt 数组元素个数
	 */
	public void runGame(int[] pCakeArray, int nCakeCnt) {
		initGame(pCakeArray, nCakeCnt);
		m_nSearch = 0;
		searchGame(0);
		output();
	}
	
	/**
	 * 初始化相关变量参数
	 * @param pCakeArray
	 * @param nCakeCnt
	 */
	private void initGame(int[] pCakeArray, int nCakeCnt) {
		m_nCakeCnt = nCakeCnt;
		m_CakeArray = new int[m_nCakeCnt];
		for(int i=0;i<m_nCakeCnt;i++) {
			m_CakeArray[i] = pCakeArray[i];
		}
		//设置最多交换次数
		m_nMaxSwap = upperBound(m_nCakeCnt);
		//初始化交换结果数组
		m_SwapArray = new int[m_nMaxSwap+1];
		//初始化中间交换结果信息
		m_ReverseCakeArray = new int[m_nCakeCnt];
		for(int i=0;i<m_nCakeCnt;i++) {
			m_ReverseCakeArray[i] = m_CakeArray[i];
		}
		m_ReverseCakeArraySwap = new int[m_nMaxSwap];
	}
	
	/**
	 * 寻找当前翻转的上限
	 * @param nCakeCnt
	 * @return
	 */
	private int upperBound(int nCakeCnt) {
		return nCakeCnt * 2;
	}
	
	/**
	 * 当前翻转的下界, 理论上的最小值, 实际中并不一定能达到
	 * @param pCakeArray
	 * @param nCakeCnt
	 * @return
	 */
	private int lowerBound(int[] pCakeArray, int nCakeCnt) {
		int t, ret = 0;
		for(int i=1;i<nCakeCnt;i++) {
			t = pCakeArray[i] - pCakeArray[i-1];
			if(t == 1 || t == -1) {
				//相邻的元素大小也相邻
			} else {
				ret++;
			}
		}
		return ret;
	}
	
	/**
	 * 搜索结果算法, 动态规划
	 * 这里会导致数组寻址越界异常, 因为step的值在随着迭代进行而不断递增==
	 * @param step
	 */
	private void searchGame(int step) {
		int i, nEstimate;
		m_nSearch++;
		nEstimate = lowerBound(m_ReverseCakeArray, m_nCakeCnt);
		if(step + nEstimate > m_nMaxSwap) {
			return;
		}
		if(isSorted(m_ReverseCakeArray, m_nCakeCnt)) {
			if(step < m_nMaxSwap) {
				m_nMaxSwap = step;
				for(i=0;i<m_nMaxSwap;i++) {
					m_SwapArray[i] = m_ReverseCakeArraySwap[i];
				}
			}
			return;
		}
		
		for(i=1;i<m_nCakeCnt;i++) {
			reverse(0, i);//对每个烙饼进行两次翻转
			if(step < m_nCakeCnt) {
				m_ReverseCakeArraySwap[step] = i;//递归在深入之后会导致step的值接近递归层级,远大于数组最大范围
			}
			searchGame(step+1);
			reverse(0, i);
		}
	}
	
	/**
	 * 检测数组排序是否已经完成
	 * @param pCakeArray
	 * @param nCakeCnt
	 * @return
	 */
	private boolean isSorted(int[] pCakeArray, int nCakeCnt) {
		for(int i=1;i<nCakeCnt;i++) {
			if(pCakeArray[i-1] > pCakeArray[i]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 进行翻转
	 * @param nBegin
	 * @param nEnd
	 */
	private void reverse(int nBegin, int nEnd) {
		int i,j,t;
		for(i = nBegin, j = nEnd; i < j; i++, j--) {
			t = m_ReverseCakeArray[i];
			m_ReverseCakeArray[i] = m_ReverseCakeArray[j];
			m_ReverseCakeArray[j] = t;
		}
	}
	
	private void output() {
		for(int i=0;i<m_nMaxSwap;i++) {
			System.out.println(m_SwapArray[i]);
		}
		System.out.println("Search Times: "+m_nSearch);
		System.out.println("Total Swap Times: "+m_nMaxSwap);
	}

}
