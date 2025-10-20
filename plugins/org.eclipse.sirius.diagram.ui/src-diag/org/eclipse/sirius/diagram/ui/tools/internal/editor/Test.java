package org.eclipse.sirius.diagram.ui.tools.internal.editor;

import org.eclipse.sirius.business.api.action.Test4;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.Test3;

class Test {
    public static void test() {
        new Test().isDetached(new Object());
    }

    private boolean isDetached(Object object) {
        boolean detached = false;
        if (Test2.A_PREDICATE_DECLARE_IN_A_CLASS_IN_SAME_PACKAGE.apply(object)) {
            detached = true;
        }
        if (Test3.A_PREDICATE_DECLARE_IN_A_CLASS_IN_ANOTHER_PACKAGE.apply(object)) {
            detached = true;
        }
        if (Test4.A_PREDICATE_DECLARE_IN_A_CLASS_IN_ANOTHER_MODULE.apply(object)) {
            detached = true;
        }
        return detached;
    }
}
