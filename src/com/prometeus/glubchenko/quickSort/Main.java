package com.prometeus.glubchenko.quickSort;

import java.io.IOException;

/**
 * Created by gleb on 1/2/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        FilmsDataBase base = new FilmsDataBase("input_06.txt");
        base.sortInversionsAndWriteToFile();
    }
}
