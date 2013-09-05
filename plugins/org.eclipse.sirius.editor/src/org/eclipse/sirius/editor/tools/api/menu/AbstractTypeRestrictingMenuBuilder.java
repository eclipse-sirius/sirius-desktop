/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.api.menu;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;

/**
 * A menu builder able to restrict child actions using types.
 * 
 * @author cbrun
 * 
 */
public abstract class AbstractTypeRestrictingMenuBuilder extends AbstractMenuBuilder {

    private final Collection<EClassifier> valids;

    private final Collection<EClassifier> restricted;

    /**
     * Create a new menu builder restricting actions with types.
     */
    public AbstractTypeRestrictingMenuBuilder() {
        valids = new HashSet<EClassifier>();
        restricted = new HashSet<EClassifier>();
    }

    /**
     * add a valid type in the builder.
     * 
     * @param clazz
     *            valid type to add.
     */
    protected void addValidType(final EClassifier clazz) {
        valids.add(clazz);
    }

    /**
     * consider this type as a restricted type.
     * 
     * @param clazz
     *            type to ignore.
     */
    protected void addRestrictedType(final EClassifier clazz) {
        restricted.add(clazz);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isMine(final CommandParameter object) {
        if (object.getEReference() != null && (valids.size() + restricted.size() > 0)) {
            final EObject value = (EObject) object.getValue();
            return isMyTargetType(value);
        }
        return true;
    }

    private boolean isMyTargetType(final EObject value) {
        boolean isContainedInValid = false;
        boolean isContainedInRestritected = false;
        for (final EClassifier valid : valids) {
            if (valid instanceof EClass) {
                if (instance((EClass) valid, value)) {
                    isContainedInValid = true;
                }
            }

        }
        if (isContainedInValid) {
            for (final EClassifier valid : restricted) {
                if (valid instanceof EClass) {
                    if (instance((EClass) valid, value)) {
                        isContainedInRestritected = true;
                    }
                }

            }
        }
        return isContainedInValid && !isContainedInRestritected;
    }

    private boolean instance(final EClass valid, final EObject type) {
        return type.eClass() == valid || valid.isSuperTypeOf(type.eClass());
    }

}
