package Algorithms2;
import java.util.ArrayList;
import java.util.Arrays;

class SubArray {
    int sum = 0;
    int arr[];
    int indStart, indEnd;

    public int getSum(){
        for(int i = 0 ; i < arr.length ; i++){
            sum += arr[i];
        }
        return sum;
    }
}
public class E_4_5_MaximumSubset {

    //Option 1
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
        int maxSum = arr[0];
        int sub[] = {arr[0]};
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

    //Option 2 - The best
    //O(N)
    public static SubArray Best2(int[] arr){
        int temp[] = new int[arr.length];
        temp[0] = arr[0];
        int maxInd = 0;
        int max = arr[0];
        for(int i=1 ; i < arr.length ; i++){
            int sum = arr[i]+temp[i-1];
            if(sum > 0){
                temp[i] = sum;
            }else{
                temp[i] = arr[i];
            }

            if(temp[i] > max) {
                max = temp[i];
                maxInd = i;
            }
        }

//        int sub[];
        SubArray sub = new SubArray();
        int minInd = maxInd;
        while(minInd >= 0 && temp[minInd] >= 0){
            minInd--;
        }
        sub.arr = Arrays.copyOfRange(arr,minInd+1,maxInd+1);
        sub.indStart = minInd+1;
        sub.indEnd = maxInd;
        return sub;
    }

    //Option3 - Best o(n)
    public static int[] bestLinear(int[] arr){
        int sum=0;
        int temp_sum=0;
        int end_index = 0;
        int start_index = 0;
        int temp_start = 0;

        for(int i=0 ; i < arr.length; i++){
            temp_sum += arr[i];
            if(temp_sum > sum){
                sum = temp_sum;
                end_index = i;
                start_index = temp_start;
            }else if(temp_sum == sum && (end_index-start_index > i - temp_start)){
                start_index = temp_start;
                end_index = i;
            }else if(temp_sum < 0){
                temp_start = i + 1;
                temp_sum = 0;
            }
        }

        int[] solution = new int[3];
        solution[0] = sum;
        solution[1] = start_index;
        solution[2] = end_index;
        return solution;
    }

    //Option 1
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

        return sub;
    }

    //Option 2
    //Double array - O(n^2)
    public static int[] Cycle_Best2(int[] arr) {
        int temp[] = new int[arr.length];
        int maxSum = 0;
        SubArray maxSub = new SubArray();
        for(int i=0 ; i < arr.length ; i++){
            int j=i;
            int count = 0;
            while(count < arr.length){
                temp[count++] = arr[j++ % arr.length];
            }

            SubArray sub = Best2(temp);
            if(sub.getSum() > maxSum){
                maxSum = sub.sum;
                maxSub = sub;
            }
        }
        return maxSub.arr;
    }

    //Option 3 - The best
    //O(n)
    public static SubArray Cycle_Best3(int[] arr) {
        int negativeArr[] = new int[arr.length];
        int sumArr = 0;
        for(int i = 0 ; i < arr.length ; i++){
            sumArr += arr[i];
            negativeArr[i] = -arr[i];
        }

        SubArray negativeSub = Best2(negativeArr);
        SubArray arrSub = Best2(arr);

        if(negativeSub.getSum() + sumArr < arrSub.getSum()){
            return arrSub;
        }else{
            int[] cycleSub = new int[arr.length - negativeSub.arr.length];
            int ind = 0;
            negativeSub.indEnd++;
            while(negativeSub.indEnd < arr.length){
                cycleSub[ind++] = arr[negativeSub.indEnd++];
            }
            int s = 0;
            while(s < negativeSub.indStart){
                cycleSub[ind++] = arr[s++];
            }
            negativeSub.arr = cycleSub;
            return negativeSub;
        }
    }

    public static int[] superBest(int[][] matrix){
        int m = Math.min(matrix.length,matrix[0].length);
        int n = Math.max(matrix.length,matrix[0].length);

        int[] temp;
        int startR = 0,startC = 0, endR = 0, endC = 0, max_sum = 0;

        for(int i= 0 ; i < m ; i++){
            temp = new int[n];
            for(int j=i ; j < m ; j++){
                for(int k=0 ; k < n ; k++){
                    if(matrix.length < matrix[0].length){
                        temp[k] += matrix[j][k];
                    }else{
                        temp[k] += matrix[k][j];
                    }
                }
                int[] temp_best = bestLinear(temp);
                if(temp_best[0] > max_sum){
                    max_sum = temp_best[0];
                    startR = i;
                    startC = temp_best[1];
                    endR = j;
                    endC = temp_best[2];
                }
            }
        }
        int[] ans = {max_sum,startR,startC,endR,endC};
        return ans;
    }

    public static void main(String[] args){
        int arr[] = {-10,2,4,3,-3};
        int arr2[] = {1,7,3,-13,2,1,10,-2,1,-20};
        int arr3[] = {7,-9, 2, 1};
        int arr4[] = {7,-2,-4,5};
        int arr5[] = {-2,2,-1,4};
        int arr6[] = {6,-9,1,2,3,-10,-100};
//        System.out.println("best linear: "+Arrays.toString(bestLinear(arr2)));
//        System.out.println("Best2: "+Arrays.toString(Best2(arr5).arr));
//        System.out.println("Best: "+Arrays.toString(Best(arr4)));
//        System.out.println("Best2: "+Arrays.toString(Best2(arr4).arr));
//        System.out.println("Cycle_Best1: "+Cycle_Best1(arr4));
//        System.out.println("Cycle_Best2: "+Arrays.toString(Cycle_Best2(arr4)));
//        System.out.println("Cycle_Best3: "+Arrays.toString(Cycle_Best3(arr4).arr));

        int[][] matrix = {{2,0,2,-3},
                           {1,6,-2,3},
                           {-3,3,-1,1},
                           {-4,4,4,0},
                           {5,1,-5,3}};

        int[][] matrix2 = {{2,1,-3,-4,5},{0,6,3,4,1},{2,-2,-1,4,-5},{-3,3,1,0,3}};
        int[][] matrix3 = {{2,3},{4,5},{6,7}};
        System.out.println(Arrays.toString(superBest(matrix3)));
//        System.out.println(Arrays.toString(superBest(matrix)));
//        System.out.println(Arrays.toString(superBest(matrix2)));
    }
}
