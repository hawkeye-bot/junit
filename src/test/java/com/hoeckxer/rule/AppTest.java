package com.hoeckxer.rule;

import com.hoeckxer.App;
import com.hoeckxer.runner.MyExceptionRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Rule
    public MyExceptionRule rule = new MyExceptionRule();

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
//        throw new RuntimeException("Some other error");
    }
}
