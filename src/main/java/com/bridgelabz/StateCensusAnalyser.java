package com.bridgelabz;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StateCensusAnalyser {
    private static int count = 0;
    private static String STRING_ARRAY_SAMPLE = "/home/admin141/IdeaProjects/IndianStatesCensusAnalyser/src/main/resources/allStateData.csv";

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


    public static String getStateCount(int expected, String CSV_PATH_NAME1, String className1, String CSV_PATH_NAME2, String className2) throws StateCensusAnalyserException, IOException {
        List<StateCensusData> stateCensusDataObjectList = new ArrayList<>();
        CsvToBean<CSVStateCensus> csvToBean1 = openCSVBuilder(CSV_PATH_NAME1, className1);
        Iterator<CSVStateCensus> csvStateIterator1 = csvToBean1.iterator();

        while (csvStateIterator1.hasNext()) {
            CSVStateCensus csvStateCensus = csvStateIterator1.next();


            CsvToBean<CSVStateCode> csvToBean2 = openCSVBuilder(CSV_PATH_NAME2, className2);
            Iterator<CSVStateCode> csvUserIterator2 = csvToBean2.iterator();
            while (csvUserIterator2.hasNext()) {
                CSVStateCode csvStateCode = csvUserIterator2.next();

                if (csvStateCode.getState().equals(csvStateCensus.getState())) {
                    System.out.println("SrNo : " + csvStateCode.getSrNo());
                    System.out.println("State : " + csvStateCode.getState());
                    System.out.println("TIN : " + csvStateCode.getTIN());
                    System.out.println("StateCode : " + csvStateCode.getStateCode());
                    System.out.println("Population : " + csvStateCensus.getPopulation());
                    System.out.println("AreaInSqKm : " + csvStateCensus.getAreaInSqKm());
                    System.out.println("DensityPerSqKm : " + csvStateCensus.getDensityPerSqKm());
                    System.out.println("==========================");
                    count++;
                    StateCensusData obj = new StateCensusData();
                    obj.setSrNo(csvStateCode.getSrNo());
                    obj.setState(csvStateCode.getState());
                    obj.setTIN(csvStateCode.getTIN());
                    obj.setStateCode(csvStateCode.getStateCode());
                    obj.setPopulation(csvStateCensus.getPopulation());
                    obj.setAreaInSqKm(csvStateCensus.getAreaInSqKm());
                    obj.setDensityPerSqKm(csvStateCensus.getDensityPerSqKm());
                    stateCensusDataObjectList.add(obj);
                    break;
                }
            }
        }
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(STRING_ARRAY_SAMPLE));
        ) {
            CSVWriter csvWriter = new CSVWriter(writer,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            String[] headerRecord = {"SrNo", "StateName", "StateCode", "TIN", "Population", "AreaInSqKm", "DensityPerSqKm"};
            csvWriter.writeNext(headerRecord);
            for (int i = 0; i < stateCensusDataObjectList.size(); i++) {
                csvWriter.writeNext(new String[]{stateCensusDataObjectList.get(i).getSrNo(), stateCensusDataObjectList.get(i).getState(), stateCensusDataObjectList.get(i).getStateCode(), String.valueOf(stateCensusDataObjectList.get(i).getTIN()), String.valueOf(stateCensusDataObjectList.get(i).getPopulation()), String.valueOf(stateCensusDataObjectList.get(i).getAreaInSqKm()), String.valueOf(stateCensusDataObjectList.get(i).getDensityPerSqKm())});

            }
            System.out.println(count);
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
}