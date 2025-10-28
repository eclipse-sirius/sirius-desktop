package org.eclipse.sirius.business.api.action;

import java.util.function.Predicate;

public class Test4 {
    public static final Predicate<Object> A_PREDICATE_DECLARE_IN_A_CLASS_IN_ANOTHER_MODULE = new Predicate<Object>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean test(Object input) {
            return false;
        }
    };
}
