package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

class Test {
    public static void test() {
        Predicate<Object> result = Predicates.and(new Predicate<Object>() {
            @Override
            public boolean apply(Object input) {
                return false;
            }
        }, new Predicate<Object>() {
            @Override
            public boolean apply(Object input) {
                return false;
            }
        });
    }
}
