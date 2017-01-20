/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

/**
 * Stub constraint for tests.
 * 
 * @author dlecan
 */
public class ConstraintStub extends AbstractModelConstraint {

    private static boolean called = false;

    private static String eClassName = "";

    /**
     * {@inheritDoc}
     */
    @Override
    public IStatus validate(IValidationContext ctx) {

        IStatus result = ctx.createSuccessStatus();

        EObject eObject = ctx.getTarget();
        if (eObject instanceof EClass) {
            EClass eClass = (EClass) eObject;
            if (eClass.getName() != null && eClass.getName().equals(eClassName)) {
                called = true;
                result = ctx.createFailureStatus(new Object[] { eClass.getName() });
            }
        }

        return result;
    }

    /**
     * Returns the called.
     * 
     * @return The called.
     */
    public static boolean hasBeenCalled() {
        return called;
    }

    /**
     * Sets the value of called to called.
     * 
     * @param called
     *            The called to set.
     */
    public static void setCalled(boolean called) {
        ConstraintStub.called = called;
    }

    /**
     * Sets the value of eClassName to eClassName.
     * 
     * @param eClassName
     *            The eClassName to set.
     */
    public static void setEClassName(String eClassName) {
        ConstraintStub.eClassName = eClassName;
    }
}
