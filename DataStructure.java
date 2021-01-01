/*Shivangi_Parashar(AI20MTECH14012)
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
//Import all libraries
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 2-SUM problem: given an array of n integers  with repetitions,and a
target integer t we   need to find if there exist two distinct elements x, y in the array such that "x + y = t".
Here  solution to this problem is obtained by getting n^2logn time complexity.
Logic used:
Here first input is sorted using merge sort ......T(c)=O(nlogn).
After that search is performed such that we get "x + y = t".
Space  complexity is o(n) for  merge sort.
*/

public class DataStructure {
    //Implementation of  merge sort for  sorting.
    private static void myMergeSort(int[] array) {
        if (array.length <= 1) {
            return;
        } /*
		Divide procedure....................
		Here we go on dividing the unsorted array  into n subarray, till we reach down to 1 single element in the array.
        Merge procedure.........................
        Here we merge the sorted lists until we get a single  sorted  list.
			 */
        int middle = array.length / 2;
        int left = middle;
        int right = array.length - middle;
        //Creating 2   temporary arrays
        int[] leftArr = new int[left];
        int[] rightArr = new int[right];


        //we are copying the elements in these temporary arrays.

        for (int i = left; i < array.length; i ++) {
            rightArr[i - left] = array[i];
        }
        for (int i = 0; i < left; i ++) {
            leftArr[i] = array[i];
        }
        myMergeSort(leftArr);//sort left half recursively
        myMergeSort(rightArr);//sort right half recursively

        int i = 0;
        int j = 0;
        //procedure for merge step
        while (i < left && j < right) {
            if (leftArr[i] <= rightArr[j]) {
                array[i + j] = leftArr[i];
                i++;
            } else {
                array[i + j] = rightArr[j];
                j++;
            }
        }
        //when one of the left or right list get exhausted
        while (i < left) {
            array[i + j] = leftArr[i];
            i++;
        }
        while (j < right) {
            array[i + j] = rightArr[j];
            j++;
        }
    }

    //call to merge sort for sorting .
    private static int[] sortArray(int[] arr) {
        myMergeSort(arr);
        return arr;
    }


    //Based on query  we will compute two distinct elements x, y in the array such that "x + y = t"
    private static int twoSumCheck(String a, String b,MyArrayList da) {
        int[] arr =new int[da.table_size()];//create new array
        int count=0;

        for(int i=0;i<= arr.length-1;i++)
        {
            arr[i]= (int) da.get(i);
        }
        int[] sortedArray=sortArray(arr);//call for sorting the input

        //Take 2 distinct elements from user
        int x=Integer.parseInt(a);
        int y=Integer.parseInt(b);
        //we will compute distinct pairs whose sum is equal to target where target ranges from x and y
        for(int i=x;i<=y;i++)
        {
            int l=0;
            int r=sortedArray.length-1;//here we have used the concept of binary search
            while(l<r)
            {
                if(sortedArray[l]+sortedArray[r]==i)//If target is found increment the value of count
                {

                    count=count+1;
                    break;

                }
                else if(sortedArray[l]+sortedArray[r]<i)
                {

                    l++;
                }
                else
                {

                    r--;
                }
            }
        }

        return count;
    }



    public static void main(String[] args) throws IOException {
        /*
         * Logic for taking input from user . Each operation is specified by two lines:
         * one giving the command (a single character), and second giving the argument.
         * All inputs are separated by a newline.
         */
        InputStreamReader read =new InputStreamReader(System.in);
        BufferedReader input =new BufferedReader(read);
        MyArrayList da =new MyArrayList();

        String line;
        while((line=input.readLine())!=null)
        {
            String[] typesOfOperations=line.split("\\s");//we are splitting the string at every letter.
            for(String s:typesOfOperations)
            {
                switch (s)
                {
                    //Command  I is used to add element in array.....Type I and in next line number  you want to add.
                    case "I":
                        da.add(Integer.parseInt(input.readLine()));
                        break;
                    //Command  D  is used to delete element from  array.....Type D and in next line number  you want to delete.
                    case "D":

                        da.removeElement(Integer.parseInt(input.readLine()));
                        da.change_size();


                        break;
                    /*
                     * Command Q is used to query ......Arguments to query are separated by a single
                     * space For every Query Q in the input stream,we are outputting  immediately  a
                     * single integer to standard output specifying the answer to that query.
                     */
                    case "Q":
                        StringTokenizer tokens= new StringTokenizer(input.readLine());
                        while(tokens.hasMoreTokens())
                        {

                            String a=tokens.nextToken();
                            String b=tokens.nextToken();
                            int output=twoSumCheck(a,b,da);
                            System.out.println(output);
                        }
                        break;
                    //command E is used to terminate the program.
                    case "E":
                        System.exit(0);
                }
            }
        }

    }



}


