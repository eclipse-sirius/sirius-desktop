package org.eclipse.sirius.diagram.ui.tools.internal.editor;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

class Test {
    public static void test() {
        List<Exception> myExceptions = new ArrayList<Exception>();
        Predicate<Exception> isTrue = new Predicate<Exception>() {

            @Override
            public boolean apply(Exception input) {
                return false;
            }
        };
        Iterable<Exception> iterable = Iterables.filter(myExceptions, isTrue);
    }
}