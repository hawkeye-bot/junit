package com.hoeckxer.rule;

import com.hoeckxer.OnInitException;
import org.junit.Before;
import org.junit.internal.runners.statements.RunBefores;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;

import java.util.List;

/**
 * Created by erwinhoeckx on 28/04/16.
 */
public class MyExceptionRule implements TestRule {
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    base.evaluate();
                } catch (Throwable e) {
                    //Dit is allemaal te lelijk en moet je echt niet willen
                    if (base instanceof RunBefores) {
                        StackTraceElement[] trace = ((Exception)e).getStackTrace();
                        StackTraceElement root = trace[0];

                        Class<?> clazz = description.getTestClass();
                        TestClass testClass = new TestClass(clazz);
                        List<FrameworkMethod> befores = testClass.getAnnotatedMethods(
                                Before.class);
                        for(FrameworkMethod before : befores) {
                            if(before.getMethod().getName().equals(root.getMethodName())) {
                                throw new OnInitException("Exception during init-method", e);
                            }
                        }
                    }
                    throw e;
                }
            }
        };
    }
}
