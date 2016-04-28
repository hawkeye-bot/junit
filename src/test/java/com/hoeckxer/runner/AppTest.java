package com.hoeckxer.runner;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.is;

import com.hoeckxer.App;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * Unit test for simple App.
 */
@RunWith(MyExceptionRunner.class)
public class AppTest 
{
    @Before
    public void init()
    {
        System.out.println("Some exotic initialization happening...");
        throw new RuntimeException("Failed to do the magic initialization");
    }

    @org.junit.Test
    public void testDoSomething()
    {
        //prepare
        App app = new App();

        //execute
        String result = app.doSomething();

        //verify
        assertThat(result,is("Hello world"));
    }
}
