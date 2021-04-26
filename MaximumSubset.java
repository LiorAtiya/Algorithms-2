package Algorithms2;
import java.util.ArrayList;
import java.util.Arrays;
public class MaximumSubset {

    //O(N^2)
    public static int[] Best(int[] arr){
        //If all the numbers in the arrays is positive
        int countPos = 0;
        for(int i=0 ; i < arr.length ; i++){
            if(arr[i] >= 0) countPos++;
            else break;
        }
        if(countPos == arr.length) return arr;

        //-----else-----
        int mat[][] = new int[arr.length][arr.length];
        for(int i=0 ; i < mat.length ; i++){
            mat[i][i] = arr[i];
        }

        for(int i=0 ; i < mat.length ; i++){
            for(int j=i+1 ; j < mat.length ; j++){
                mat[i][j] = mat[i][j-1]+mat[j][j];
            }
        }

        //Extract the subset
        int maxSum = Integer.MIN_VALUE;
        int sub[] = new int[0];
        for(int i=0 ; i < mat.length ; i++){
            for(int j=i+1 ; j < mat.length ; j++){
                if(mat[i][j] > maxSum) {
                    maxSum = mat[i][j];
                    sub = Arrays.copyOfRange(arr,i,j+1);
                }
            }
        }
        return sub;
    }


    //O(N)

//int arr3[] = {7,-9, 2, 1};
    public static int[] Best2(int[] arr){
        int temp[] = new int[arr.length];
        temp[0] = arr[0];
        int maxInd = 0;
        int max = arr[0];
        for(int i=1 ; i < arr.length ; i++){
            int sum = arr[i]+temp[i-1];
            if(sum >= 0){ temp[i] = sum;
            }else{ temp[i] = arr[i]; }

            if(temp[i] > max) {
                max = temp[i];
                maxInd = i;
            }
        }

        int sub[];
        int minInd = maxInd;
        while(minInd >= 0 && temp[minInd] >= 0){
            minInd--;
        }
        sub = Arrays.copyOfRange(arr,minInd+1,maxInd+1);
        return sub;
    }

    //With matrix - O(n^2)
    public static ArrayList<Integer> Cycle_Best1(int[] arr){
//        //If all the numbers in the arrays is positive
//        int countPos = 0;
//        for(int i=0 ; i < arr.length ; i++){
//            if(arr[i] >= 0) {
//                countPos++;
//            }
//            else break;
//        }
//        if(countPos == arr.length) return arr;

        //Fill the matrix diagonal with the array
        int mat[][] = new int[arr.length][arr.length];
        for(int i=0 ; i < mat.length ; i++){
            mat[i][i] = arr[i];
        }

        //Fill the subsets sums in the upper triangle
        for(int i=0 ; i < mat.length ; i++){
            for(int j=i+1 ; j < mat.length ; j++){
                mat[i][j] = mat[i][j-1]+mat[j][j];
            }
        }
        //Fill the subsets sums in the bottom triangle
        for(int i = 1 ; i < mat.length ; i++){
            for(int j = 0 ; j < i ; j++){
                int start = i;
                int end = 0;
                while(start < mat.length){ mat[i][j] += arr[start++]; }
                while(end <= j){ mat[i][j] += arr[end++]; }
            }
        }

        int maxSum = Integer.MIN_VALUE;
        int maxIndRow = 0;
        int maxIndCol = 0;
        for(int i=0 ; i < mat.length ; i++){
            for(int j=0 ; j < mat.length ; j++){
                if(i != j){
                    if(mat[i][j] > maxSum) {
                        maxSum = mat[i][j];
                        maxIndRow = i; maxIndCol = j;
                    }
                }
            }
        }
        ArrayList<Integer> sub = new ArrayList<>();
        if(maxIndRow < maxIndCol){
            while(maxIndRow <= maxIndCol){
                sub.add(arr[maxIndRow++]);
            }
        }else{
            while(maxIndRow < arr.length){
                sub.add(arr[maxIndRow++]);
            }
            int s = 0;
            while(s <= maxIndCol){
                sub.add(arr[s++]);
            }
        }

//        for(int i=0 ; i < mat.length ; i++){
//            System.out.println(Arrays.toString(mat[i]));
//        }

        return sub;
    }

    //Double array - O(n^2)
    public static int[] Cycle_Best2(int[] arr) {
        int temp[] = new int[arr.length];
        int maxSum = 0;
        int maxSub[] = new int[0];
        for(int i=0 ; i < arr.length ; i++){
            int j=i;
            int count = 0;
            while(count < arr.length){
                temp[count++] = arr[j++ % arr.length];
            }
            int sub[] = Best2(temp);
            int sum = 0;
            for(int k = 0 ; k < sub.length; k++){
                sum += sub[k];
            }
            if(sum > maxSum){
                maxSum = sum;
                maxSub = sub;
            }
        }
        return maxSub;
    }

    public static int Cycle_Best3(int[] arr) {
        int negativeArr[] = new int[arr.length];
        int sumArr = 0;
        for(int i = 0 ; i < arr.length ; i++){
            sumArr += arr[i];
            negativeArr[i] = -arr[i];
        }
        int negativeSub[] = Best2(negativeArr);
        int sumNegative = 0;
        for(int i=0 ; i < negativeSub.length ; i++){
            sumNegative += negativeSub[i];
        }
        return sumArr + sumNegative;
    }

    public static void main(String[] args){
        int arr[] = {-10,2,4,3,-3};
        int arr2[] = {1,7,3,-13,2,1,10,-2,1,-20};
        int arr3[] = {7,-9, 2, 1};
        int arr4[] = {7,-2,4,5};
//        System.out.println("Best: "+Arrays.toString(Best(arr)));
//        System.out.println("Best2: "+Arrays.toString(Best2(arr2)));
//        System.out.println("Cycle_Best1: "+Cycle_Best1(arr4));
//        System.out.println("Cycle_Best2: "+Arrays.toString(Cycle_Best2(arr3)));
//        System.out.println("Cycle_Best3: "+Cycle_Best3(arr4));
    }
}
