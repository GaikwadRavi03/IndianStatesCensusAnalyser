package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyserTest {
    @Test
    public void givenTheStatesCSV_fileCheckToEnsure_TheNumberOfRecord_matches() {

        try {
            String result = StateCensusAnalyser.findStateCount(29);
            Assert.assertEquals("HAPPY", result);
        } catch (StateCensusAnalyserException e) {
        }
    }

    @Test
    public void givenStateCSV_FileIfIncorrectReturns_CustomException() {

        try {
            String result = StateCensusAnalyser.findStateCount(29);
            Assert.assertEquals("HAPPY", result);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals("Please Valid File", e.getMessage());
        }
    }
}
