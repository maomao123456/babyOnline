package com.example.lei;
/**
 * 此类用于暂存跳转数据  及列表位置的临时保存
 * @author lenovo
 *
 */
public class CanshuSave {
	/**
	 * 页面的位置
	 */
	public int yemian;
	/**
	 * 列表项上次的位置
	 */
	public int liebiao;
	public int getYemian() {
		return yemian;
	}
	public void setYemian(int yemian) {
		this.yemian = yemian;
	}
	public int getLiebiao() {
		return liebiao;
	}
	public void setLiebiao(int liebiao) {
		this.liebiao = liebiao;
	}
	
}
