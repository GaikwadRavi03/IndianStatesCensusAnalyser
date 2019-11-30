package com.bridgelabz;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import sun.security.krb5.internal.crypto.EType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {
    private static final String SAMPLE_CSV_STATE_CODE_PATH = "/home/admin141/IdeaProjects/IndianStatesCensusAnalyser/src/main/resources/StateCode.csv";
    private static final String SAMPLE_CSV_STATE_CENSUS_DATA_PATH = "/home/admin141/IdeaProjects/IndianStatesCensusAnalyser/src/main/resources/StateCensusData.csv";
    private static int count = 0;

    public static String findStateCount(int expected) throws StateCensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_STATE_CODE_PATH));
            CsvToBean<CSVState> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CSVState.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            Iterator<CSVState> csvStateIterator = csvToBean.iterator();

            while (csvStateIterator.hasNext()) {
                CSVState csvState = csvStateIterator.next();
                System.out.println("State : " + csvState.getState());
                System.out.println("Population : " + csvState.getPopulation());
                System.out.println("AreaInSqKm : " + csvState.getAreaInSqKm());
                System.out.println("DensityPerSqKm : " + csvState.getDensityPerSqKm());
                System.out.println("==========================");
                count++;
            }

            if (expected == count)
                return "HAPPY";
            else
                return "SAD";
        } catch (NoSuchFileException e) {
            throw new StateCensusAnalyserException("Please Enter Valid File");
        } catch (NullPointerException e) {
            throw new StateCensusAnalyserException("Please Enter Valid file path", StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException("Please Enter Valid File path", StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
