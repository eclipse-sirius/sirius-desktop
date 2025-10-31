package org.eclipse.sirius.diagram.ui.tools.internal.edit.command;

import java.util.function.Predicate;

public class Test3 {
    public static final Predicate<Object> A_PREDICATE_DECLARE_IN_A_CLASS_IN_ANOTHER_PACKAGE = new Predicate<Object>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean test(Object input) {
            return false;
        }

    };
}
