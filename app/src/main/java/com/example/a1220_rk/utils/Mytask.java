package com.example.a1220_rk.utils;

import android.content.Context;
import android.os.AsyncTask;

public class Mytask<T> extends AsyncTask<T,T,String> {
    String mPath;
    Context context;
    String mRam;
    private TaskListeners taskListener;
    //获取接口
    public Mytask setTaskListener(TaskListeners taskListener){
        this.taskListener = taskListener;
        return this;
    }

    public Mytask(String mPath, Context context, String mRam) {
        this.mPath = mPath;
        this.context = context;
        this.mRam = mRam;

    }

    @Override
    protected String doInBackground(T... ts) {

        return MyHttp.getHttpCon(context,mPath,mRam);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
       if (s!=null){
           taskListener.result(s);
       }
    }
   //定义接口
    public interface TaskListeners{
        void result(String t);
   }


}
