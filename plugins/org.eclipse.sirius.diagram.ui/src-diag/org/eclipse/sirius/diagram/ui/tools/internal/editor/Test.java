package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

class Test {
    public static void test() {
        final List<ClassCastException> result = new ArrayList<ClassCastException>();
        List<Exception> myExceptions = new ArrayList<Exception>();
        result.addAll(Sets.newHashSet(Iterables.filter(myExceptions, ClassCastException.class)));
    }
}