package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Ordering;

class Test {
    public static void main(String[] args) {
        new Test().test();
    }

    public void test() {
        List<String> param = List.of("Aaa", "Aaaa", "Cccef", "Z", "Ab", "Bbbbdfgfg"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
        System.out.println("----   GUAVA   ----"); //$NON-NLS-1$
        for (String string : testGuava(param)) {
            System.out.println("- " + string); //$NON-NLS-1$
        }
        System.out.println("---- NO GUAVA ----"); //$NON-NLS-1$
        for (String string : testNoGuava(param)) {
            System.out.println("- " + string); //$NON-NLS-1$
        }
    }

    public ImmutableSortedSet<String> testGuava(List<String> input) {
        Function<String, Integer> getValueToCompareFunction = new Function<String, Integer>() {
            @Override
            public Integer apply(String from) {
                return from.length();
            }
        };
        Ordering<String> ordering = Ordering.natural().onResultOf(getValueToCompareFunction);
        return ImmutableSortedSet.orderedBy(ordering).addAll(input).build();
    }

    public SortedSet<String> testNoGuava(List<String> input) {
        java.util.function.Function<String, Integer> getValueToCompareFunction = new java.util.function.Function<String, Integer>() {
            @Override
            public Integer apply(String from) {
                return from.length();
            }
        };
        SortedSet<String> result = new TreeSet<String>(Comparator.comparing(getValueToCompareFunction, Comparator.naturalOrder()));
        result.addAll(input);
        return result;
    }

}