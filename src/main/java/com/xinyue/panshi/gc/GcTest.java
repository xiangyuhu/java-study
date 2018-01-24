package com.xinyue.panshi.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hxy
 * @time 2018/1/20
 * @desc
 */
public class GcTest {
    static List list = new ArrayList<String>();

    /**
     * https://liuchi.coding.me/2017/08/05/%E6%B7%B1%E5%85%A5%E8%A7%A3%E6%9E%90Java%E5%9E%83%E5%9C%BE%E5%9B%9E%E6%94%B6%E6%9C%BA%E5%88%B6/
     * GC Root a
     * 虚拟机栈（栈帧中的本地变量表）中的引用的对象， a = new Object();
     * 方法区中的类静态属性引用的对象 static a = new Object();
     * 方法区中的常量引用的对象 string a = "1";
     * 本地方法栈中JNI（Native方法）的引用对象
     * GC什么时候发生：
     * 1.年老代（Tenured）被写满
     * 2.持久代（Perm）被写满
     * 3.System.gc()被显示调用
     * @param args
     */
    public static void main(String[] args) {
        /**
         *   JVM堆内存包括年轻代，老年代和持久代。
         *   其中年轻代又包括一个Eden区和两个Survivor区, 默认比例 8：1
         *   Eden--(gc)--> 如果存活--->Survivor
         *   -Xmn500M表示年轻代大小是500M，
         *   -XX:SurvivorRatio=3表示Eden（伊甸园）区与两个Survivor区的大小比值为3：1：1，故Eden区的大小为300M
         *   -Xms1G -Xmx2G -Xmn500M -XX:MaxPermSize=64M -XX:+UseConcMarkSweepGC -XX:SurvivorRatio=3
         *   Xmx2G: 最大可用内存
         *   -XX:MaxPermSize=64m:设置持久代大小为64m。
         *   -XX:+UseConcMarkSweepGC
         *   Xms1G：初始化堆内存，
         *   -Xmx2G:最大堆大小
         */
        // new Object() 首先判断该类是否被加载了
        Object a = new Object();
        Object b = new Object();

        a = b;
        // 此时b不可 回收，因为 a 依赖b
        b = null;

        for (int i = 1; i<100; i++)
        {
           String str = "3";
            list.add(str);
            str = null;
        }
        // 知识告诉虚拟机， 并不一定执行
        System.gc();
    }


}
