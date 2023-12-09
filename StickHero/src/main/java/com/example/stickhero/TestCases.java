package com.example.stickhero;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestCases {
    @Test
    public void test1(){
        ResestGlobal test = new ResestGlobal();
        boolean testcase = test.reset();
        assertEquals(true,testcase);
    }
    @Test
    public void test2(){
        ResestGlobal test = new ResestGlobal();
        boolean testcase = test.reviveReset();
        assertEquals(true,testcase);
    }

}