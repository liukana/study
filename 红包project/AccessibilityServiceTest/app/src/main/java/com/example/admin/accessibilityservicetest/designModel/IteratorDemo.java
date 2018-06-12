package com.example.admin.accessibilityservicetest.designModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList 使用迭代器遍历，修改数据源，不会产生ConcurrentModificationException
 * Created by liukan on 2018/5/19.
 */

public class IteratorDemo {

    public static void main(String[] args){

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("lk");
        list.add("aa");
        list.add("bb");
        list.add("cc");
        list.add("dd");

        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            Object o = iterator.next();
            if(o.equals("cc")){
                list.remove(o);
            }
        }
        System.out.println(list);

        // 第二种方式，使用 ListIterator 遍历列表
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("qw");
        arrayList.add("we");
        arrayList.add("er");
        arrayList.add("rt");
        ListIterator listIterator = arrayList.listIterator();
        while (listIterator.hasNext()){
            if (listIterator.next().equals("we")){
                listIterator.remove();
                if(listIterator.hasPrevious()){
                    listIterator.previous();
                    listIterator.remove();
                }
            }

        }
        System.out.println(arrayList);

        // 二分查找
//        Collections.binarySearch(list,"");

    }
}
