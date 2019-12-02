package com.bridgelabz;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {
    private static int count = 0;

    public static String findStateCount(int expected, String CSV_PATH_NAME, String className) throws StateCensusAnalyserException {
        try {
            CsvToBean<CSVStateCensus> csvToBean = openCSVBuilder(CSV_PATH_NAME, className);

            Iterator<CSVStateCensus> csvStateIterator = csvToBean.iterator();

            while (csvStateIterator.hasNext()) {
                CSVStateCensus csvState = csvStateIterator.next();
                count++;
            }
            if (expected == count)
                return "HAPPY";
            else
                return "SAD";
        } catch (NullPointerException e) {
            throw new StateCensusAnalyserException("delimiter", StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException("Please Enter Valid File path", StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        }
    }

    public static String findStateCodeCount(int expected, String CSV_PATH_NAME, String className) throws StateCensusAnalyserException {
        try {

            CsvToBean<CSVStateCode> csvToBean = openCSVBuilder(CSV_PATH_NAME, className);

            Iterator<CSVStateCode> csvUserIterator = csvToBean.iterator();

            while (csvUserIterator.hasNext()) {
                CSVStateCode csvStateCode = csvUserIterator.next();
                count++;
            }
            if (expected == count)
                return "HAPPY";
            else
                return "SAD";
        } catch (NullPointerException e) {
            throw new StateCensusAnalyserException("delimiter", StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException("Please Enter Valid File", StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        }
    }

    public static <T> CsvToBean openCSVBuilder(String filename, String classname) {
        CsvToBean<T> csvToBean;
        try {
            Class classTemp = Class.forName(classname);
            Reader reader = Files.newBufferedReader(Paths.get(filename));
            csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Class.forName(classname))
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}