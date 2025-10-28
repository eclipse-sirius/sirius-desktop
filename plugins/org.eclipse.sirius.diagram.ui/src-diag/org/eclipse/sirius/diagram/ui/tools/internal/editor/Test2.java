package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import java.util.function.Predicate;

public class Test2 {
    public static final Predicate<Object> A_PREDICATE_DECLARE_IN_A_CLASS_IN_SAME_PACKAGE = new Predicate<Object>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean test(Object input) {
            return false;
        }

    };
}
