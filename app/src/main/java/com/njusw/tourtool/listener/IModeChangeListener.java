package com.njusw.tourtool.listener;

public interface IModeChangeListener {

	/**
	 * onModeChanged 跟UI说界面需要修改
	 *
	 * @param action 返回处理不同UI的action
	 */
	public void onModeChanged(int action, Object... values);

}
