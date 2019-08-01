package com.hogwai.p3;

import com.hogwai.p3.joueur.IAHandler;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {

        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        /*IAHandler test = new IAHandler();
        List<String> testList = test.getListFromString("test");
        testList.forEach(System.out::println);*/
        System.getProperty("java.classpath");

    }
}
