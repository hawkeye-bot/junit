package com.hoeckxer;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.util.List;

/**
 * Created by erwinhoeckx on 28/04/16.
 */
public class ThrowingInitRunBefores extends Statement{
        private final Statement next;

        private final Object target;

        private final List<FrameworkMethod> befores;

        public ThrowingInitRunBefores(Statement next, List<FrameworkMethod> befores, Object target) {
            this.next = next;
            this.befores = befores;
            this.target = target;
        }

        @Override
        public void evaluate() throws Throwable {
            for (FrameworkMethod before : befores) {
                try {
                    before.invokeExplosively(target);
                }
                catch(Exception e)
                {
                    throw new OnInitException("Exception failed while running a before method", e);
                }
            }
            next.evaluate();
        }
}
