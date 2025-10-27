package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.google.common.base.Predicate;

class Test {
    public static void main(String[] args) {
        Set<Object> set = new HashSet<>();
        set.add(new String("First")); //$NON-NLS-1$
        set.add(new String("Second")); //$NON-NLS-1$
        set.add(new String("Third")); //$NON-NLS-1$
        set.add(new String("Fourth")); //$NON-NLS-1$
        Predicate<Object> isNotNull = Objects::nonNull;
        Set<Object> result = test(set, isNotNull);
        Set<Object> result2 = test2(set, isNotNull);
        System.out.println(result);
        System.out.println(result2);
        
        Set<Object> set2 = new TreeSet<>();
        set2.add(new String("First")); //$NON-NLS-1$
        set2.add(new String("Second")); //$NON-NLS-1$
        set2.add(new String("Third")); //$NON-NLS-1$
        set2.add(new String("Fourth")); //$NON-NLS-1$
        result = test(set2, isNotNull);
        result2 = test2(set2, isNotNull);
        Set<Object> result3 = test3(set2, isNotNull);
        System.out.println(result);
        System.out.println(result2);
        System.out.println(result3);

    }

    public static Set<Object> test(Set<Object> set, Predicate<Object> isNotNull) {
        return set.stream().filter(isNotNull).collect(Collectors.toSet());
    }

    public static Set<Object> test2(Set<Object> set, Predicate<Object> isNotNull) {
        return set.stream().filter(isNotNull).collect(Collectors.toSet());
    }

    public static SortedSet<Object> test3(Set<Object> set, Predicate<Object> isNotNull) {
        return set.stream().filter(isNotNull).collect(Collectors.toCollection(TreeSet::new));
    }
}
