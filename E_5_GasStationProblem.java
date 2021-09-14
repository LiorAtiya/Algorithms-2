//package Algorithms2;
//
//import java.util.Arrays;
//
//public class E_GasStationProblem {
//
//    public static int gas_station_problem(int[] station, int[] cost){
//        int sumStation, sumCost;
//        sumStation = sumCost = 0;
//        int[] diff = new int[station.length];
//        for(int i=0 ; i < station.length ; i++){
//            sumStation += station[i];
//            sumCost += cost[i];
//            diff[i] = station[i] - cost[i];
//        }
//        if(sumStation < sumCost) return -1;
//
//        SubArray sub = MaximumSubset.Best2(diff);
//        SubArray cycleSub = MaximumSubset.Cycle_Best3(diff);
//        sub.sum = 0;
//        cycleSub.sum = 0;
//        if(cycleSub.getSum() > sub.getSum()){
//            return cycleSub.indStart;
//        }
//        return sub.indStart;
//    }
//
//    public static void main(String[] args){
//        int A[] = {3,6,2,8};
//        int B[] = {5,4,3,4};
//
//        System.out.println("The number of start station is "+(gas_station_problem(A,B)+1));
//    }
//}
