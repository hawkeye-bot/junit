package com.hoeckxer.runner;

import com.hoeckxer.ThrowingInitRunBefores;
import org.junit.Before;
import org.junit.internal.runners.statements.RunBefores;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import java.util.List;

/**
 * Created by erwinhoeckx on 28/04/16.
 */
public class MyExceptionRunner extends BlockJUnit4ClassRunner {
    public MyExceptionRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    @Override
    protected Statement withBefores(FrameworkMethod method, Object target, Statement statement) {
        System.out.println("Executing before methods");

        List<FrameworkMethod> befores = getTestClass().getAnnotatedMethods(
                Before.class);
        return befores.isEmpty() ? statement : new ThrowingInitRunBefores(statement,
                befores, target);
    }
}
