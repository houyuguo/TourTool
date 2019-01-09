package com.njusw.tourtool.controller;


import com.njusw.tourtool.listener.IModeChangeListener;

public abstract class BaseController {


    protected IModeChangeListener mListener;

    public void setIModeChangeListener(IModeChangeListener listener) {
        mListener=listener;
    }


    public void sendAsyncMessage(final int action,final Object... values){
        new Thread(){
            public void run() {
                handleMessage(action, values);

            }
        }.start();
    }

    /**
     * 子类处理具体的需求的业务代码
     */
    protected abstract void handleMessage(int action,Object... values);


}
