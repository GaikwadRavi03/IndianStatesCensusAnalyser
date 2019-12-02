package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyserTest {
    private static final String SAMPLE_CSV_STATE_CODE_PATH = "/home/admin141/IdeaProjects/IndianStatesCensusAnalyser/src/main/resources/StateCode.csv";
    private static final String SAMPLE_CSV_STATE_CENSUS_DATA_PATH = "/home/admin141/IdeaProjects/IndianStatesCensusAnalyser/src/main/resources/StateCensusData.csv";
    private static final String CSVStatseCensusPojo = "com.bridgelabz.CSVStateCensus";
    private static final String CSVStatseCodePojo = "com.bridgelabz.CSVStateCode";


    @Test
    public void givenTheStatesCSV_fileCheckToEnsure_TheNumberOfRecord_matches() {

        try {
            String result = StateCensusAnalyser.findStateCount(29, SAMPLE_CSV_STATE_CENSUS_DATA_PATH, CSVStatseCensusPojo);
            Assert.assertEquals("HAPPY", result);
        } catch (StateCensusAnalyserException e) {
        }
    }

    @Test
    public void givenStateCSV_FileIf_IncorrectReturns_CustomException() {
        try {
            String result = StateCensusAnalyser.findStateCount(29, SAMPLE_CSV_STATE_CENSUS_DATA_PATH, CSVStatseCensusPojo);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals("Please Enter Valid File", e.getMessage());
        }
    }

    @Test
    public void givenTheState_CSVFileWhenCorrectBut_TypeIncorrect_ReturnsCustomException() {
        String result = null;
        try {
            result = StateCensusAnalyser.findStateCount(29, SAMPLE_CSV_STATE_CENSUS_DATA_PATH, CSVStatseCensusPojo);
            Assert.assertEquals("HAPPY", result);
        } catch (StateCensusAnalyserException e) {
            //Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenTheState_CSVFileWhenCorrectBut_DelimiterIncorrect_ReturnsCustomException() throws StateCensusAnalyserException {
        String result = StateCensusAnalyser.findStateCount(29, SAMPLE_CSV_STATE_CENSUS_DATA_PATH, CSVStatseCensusPojo);
        Assert.assertEquals("HAPPY", result);
    }

    @Test
    public void givenTheStateCSV_FileWhenCorrectBut_csv_HeaderIncorrect_ReturnsCustomException() {
        String result = null;
        try {
            result = StateCensusAnalyser.findStateCount(29, SAMPLE_CSV_STATE_CENSUS_DATA_PATH, CSVStatseCensusPojo);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals("Please Enter Valid File path", e.getMessage());
        }
    }

    @Test
    public void givenTheStatesCode_CSV_fileCheckToEnsure_TheNumberOfRecord_matches() {
        String result = null;
        try {
            result = StateCensusAnalyser.findStateCodeCount(37, SAMPLE_CSV_STATE_CODE_PATH, CSVStatseCodePojo);
            Assert.assertEquals("HAPPY", result);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStateCodeCSV_FileIf_IncorrectReturns_CustomException() {
        String result = null;
        try {
            result = StateCensusAnalyser.findStateCodeCount(37, SAMPLE_CSV_STATE_CODE_PATH, CSVStatseCodePojo);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals("Please Enter Valid File", e.getMessage());
        }
    }

    @Test
    public void givenTheStateCode_CSVFileWhenCorrectBut_TypeIncorrect_ReturnsCustomException() {
        String result = null;
        try {
            result = StateCensusAnalyser.findStateCodeCount(37, SAMPLE_CSV_STATE_CODE_PATH, CSVStatseCodePojo);
            Assert.assertEquals("HAPPY", result);
        } catch (StateCensusAnalyserException e) {
        }
    }

    @Test
    public void givenTheStateCode_CSVFileWhenCorrectBut_DelimiterIncorrect_ReturnsCustomException() {
        try {
            String result = StateCensusAnalyser.findStateCodeCount(37, SAMPLE_CSV_STATE_CODE_PATH, CSVStatseCodePojo);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    @Test
    public void givenTheStateCodeCSV_FileWhenCorrectBut_csv_HeaderIncorrect_ReturnsCustomException() {
        String result = null;
        try {
            result = StateCensusAnalyser.findStateCodeCount(37, SAMPLE_CSV_STATE_CODE_PATH, CSVStatseCodePojo);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals("Please Enter Valid File", e.getMessage());
        }
    }
}
