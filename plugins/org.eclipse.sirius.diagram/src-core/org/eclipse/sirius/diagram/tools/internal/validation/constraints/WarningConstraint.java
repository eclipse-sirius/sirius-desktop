/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.validation.constraints;

import org.eclipse.sirius.diagram.tools.api.validation.constraint.AbstractDDiagramConstraint;
import org.eclipse.sirius.viewpoint.description.validation.ERROR_LEVEL;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;

/**
 * Constraint for DDiagramElement bridging EMF validation framework and our
 * validation model. This class is provided for the WARNING rules.
 * 
 * @author cbrun
 * 
 */
public class WarningConstraint extends AbstractDDiagramConstraint {

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isValid(final ValidationRule rule) {
        return rule.getLevel() == ERROR_LEVEL.WARNING_LITERAL;
    }

}
