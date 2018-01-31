package com.xinyue.panshi.structanddata.dynamicProgramming;

import java.util.List;

/**
 * @author hxy
 * @time 2018/1/30
 * @desc
 */
public class ZuiYouJie {
    int totalWeight;
    int currentWeight;

    List<Zone> zoneAllList;
    int zuiYouValue [][] = new  int[100][100];

    public ZuiYouJie(int totalWeight,List<Zone> zoneAllList){
        this.totalWeight = totalWeight;
        this.zoneAllList = zoneAllList;
    }

    public  void getZuiYou(){

        int size = zoneAllList.size();
        //当i=0的时候
        for(int i=0;i<totalWeight;i++){
            zuiYouValue[0][i] = 0;
        }

        for(int i=0;i<size;i++){
            zuiYouValue[i][0] = 0;
        }

        for(int currentWeight=1;currentWeight<=totalWeight;currentWeight++){
            for(int i=1;i<=size;i++){
                Zone currentZone = zoneAllList.get(i-1);
                if(currentWeight<zoneAllList.get(i-1).getWeight()){
                    zuiYouValue[i][currentWeight]=zuiYouValue[i-1][currentWeight];
                }
                else{
                    //如果
                    if(zuiYouValue[i-1][currentWeight-currentZone.getWeight()]+currentZone.getValue()>zuiYouValue[i-1][currentWeight]){
                        zuiYouValue[i][currentWeight] =  zuiYouValue[i-1][currentWeight-currentZone.getWeight()]+currentZone.getValue();
                    }else{
                        zuiYouValue[i][currentWeight] = zuiYouValue[i-1][currentWeight];
                    }
                }
            }
        }
        int currentWeightForCompare = totalWeight;
        for(int k = size;k>0;k--){
            if(zuiYouValue[k][currentWeightForCompare]>zuiYouValue[k-1][currentWeightForCompare]){
                zoneAllList.get(k-1).setForZuiYou(true);
                currentWeightForCompare = currentWeightForCompare-zoneAllList.get(k-1).getWeight();
            }
            if(currentWeightForCompare==0){
                break;
            }
        }
    }
}
