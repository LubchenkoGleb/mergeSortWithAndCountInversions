package com.prometeus.glubchenko.quickSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by gleb on 1/2/2016.
 */
public class FilmsDataBase {

    private int[][] dataBase;
    private int[] inversionsMatrix;
    private int amountOfUsers, amountOfFilms, checkedUser;


    public FilmsDataBase(String pathTiInputFile){
        try {
            Scanner scanner = new Scanner(new File(pathTiInputFile));
            checkedUser = Integer.parseInt(pathTiInputFile.replaceAll("\\D+",""));
            amountOfUsers = scanner.nextInt();
            amountOfFilms = scanner.nextInt();
            inversionsMatrix = new int[amountOfUsers];
            dataBase = new int[amountOfUsers][amountOfFilms];

            for (int i = 0; i < amountOfUsers; i++) {
                scanner.nextInt();
                for (int j = 0; j < amountOfFilms; j++)
                    dataBase[i][j] = scanner.nextInt();
            }

        }catch (FileNotFoundException e){
            System.err.println("check the path to input file");
        }
    }

    public void countInversionsInDataBase(){
        int[] compereFirstAndSecondUser = new int[amountOfFilms];

        for (int i = 0; i < amountOfUsers; i++) {
            for (int j = 0; j < amountOfFilms; j++)
                compereFirstAndSecondUser[ dataBase[checkedUser - 1][j] - 1 ] = dataBase[i][j] - 1;
            inversionsMatrix[i] = mergeSort(compereFirstAndSecondUser, 0, compereFirstAndSecondUser.length - 1);
        }
    }

    private int merge(int []A, int first, int middle, int last){
        int n1 = middle - first + 1, n2 = last - middle;
        int L[] = new int[n1 + 1], R[] = new int[n2 + 1];

        for (int i = 0; i < n1; i++)
            L[i] = A[first + i];
        for (int i = 0; i < n2; i++)
            R[i] = A[middle + i + 1];

        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;
        int i = 0, j = 0;

        int counter = 0;
        for(int k = first; k <= last; k++){
            if(L[i] <= R[j]){
                A[k] = L[i];
                i++;
            }
            else {
                A[k] = R[j];
                j++;
                counter += n1 - i;
            }
        }
        return counter;
    }

    public int mergeSort(int []A, int left, int right){
        if(left >= right)
            return 0;

        int middle = (left + right) / 2;
        return mergeSort(A, left, middle) + mergeSort(A, middle + 1, right) + merge(A, left, middle, right);
    }

    public void sortInversionsAndWriteToFile() {
        countInversionsInDataBase();
        LinkedList<int[]> tempInversionsMatrix = new LinkedList<>();
        for (int i = 0; i < inversionsMatrix.length; i++)
            tempInversionsMatrix.add(new int[]{i, inversionsMatrix[i]});

        mergeSort(inversionsMatrix, 0, amountOfUsers - 1);


        for (int i = 0; i < amountOfUsers; i++)
            for (int j = 0; j < tempInversionsMatrix.size(); j++) {
                if (inversionsMatrix[i] == tempInversionsMatrix.get(j)[1] && inversionsMatrix[i] != 0) {
                    System.out.println((tempInversionsMatrix.get(j)[0]+1)  + " " + inversionsMatrix[i]);
                    tempInversionsMatrix.remove(j);
                    break;
                }
            }

        System.out.println();
    }

//        try{
//            for (int i = 1; i <= treeMap.size(); i++) {
//                System.out.println(treeMap.get(i));
//
//                /*PrintWriter writer = new PrintWriter(new File("is41_lubchenko_02_output.txt"), "UTF-8");
//                writer.println(checkedUser);
//                writer.println(i + 1 + " " + counter);
//                writer.close();
//                System.out.println();*/
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//            System.err.println("problems with ");
//        }
//    }

    public void printDataBase(){
        for (int i = 0; i < amountOfUsers; i++) {
            for (int j = 0; j < amountOfFilms; j++) {
                System.out.print(dataBase[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
