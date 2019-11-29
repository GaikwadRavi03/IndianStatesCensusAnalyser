package com.bridgelabz;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyserTest {
    @Test
    public void givenTheStatesCSV_fileCheckToEnsure_TheNumberOfRecord_matches() throws IOException {
        int result = StateCensusAnalyser.findStateCount();
        Assert.assertEquals(29, result);
    }
}
