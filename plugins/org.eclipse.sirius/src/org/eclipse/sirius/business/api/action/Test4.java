package org.eclipse.sirius.business.api.action;

import com.google.common.base.Predicate;

public class Test4 {
    public static final Predicate<Object> A_PREDICATE_DECLARE_IN_A_CLASS_IN_ANOTHER_MODULE = new Predicate<Object>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean apply(Object input) {
            return false;
        }
    };
}
