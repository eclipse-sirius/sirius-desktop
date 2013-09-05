/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.internal.validation.constraints;

import org.eclipse.sirius.description.validation.ERROR_LEVEL;
import org.eclipse.sirius.description.validation.ValidationRule;
import org.eclipse.sirius.tools.api.validation.constraint.AbstractDDiagramConstraint;

/**
 * Constraint for DDiagramElement bridging EMF validation framework and our
 * validation model. This class is provided for the ERROR rules.
 * 
 * @author cbrun
 * 
 */
public class ErrorConstraint extends AbstractDDiagramConstraint {
    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isValid(final ValidationRule rule) {
        return rule.getLevel() == ERROR_LEVEL.ERROR_LITERAL;
    }
}
