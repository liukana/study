package com.example.admin.accessibilityservicetest.designModel;

/**
 * Created by liukan on 2018/3/29.
 */

public class SingletonMode {

    private static volatile SingletonMode singletonMode;

    private SingletonMode(){}

    public static SingletonMode getInstance(){
        if(singletonMode == null){
            synchronized (SingletonMode.class){
                if(singletonMode == null){
                    singletonMode = new SingletonMode();
                }
            }
        }
        return singletonMode;
    }

    public static void main(String[] args) {
        int[] posAry = new int[]{5, 4, 6, 11, 11, 12, 14, 5, 7, 5, 15, 4, 6, 10, 3, 7, 1, 13, 10, 0, 8};
        char[] websiteAry = new char[]{'3', 'o', '1', 'w', 'h', 't', 'w', 'm', '6', 'c', ':', 'n', 'p', '/', '.', 'w'};
        System.out.print("程序猿专属解忧网站：");
        for(int i=0;i<posAry.length;i++){
            System.out.print(websiteAry[i%2==0 ? posAry[i]-1 : posAry[i]+1]);
        }
    }
}
