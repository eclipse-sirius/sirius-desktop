/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.business.internal.metamodel.description.spec;

import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.table.metamodel.table.description.impl.IntersectionMappingImpl;

/**
 * Specific implementation for model instances.
 * 
 * @author cbrun
 * 
 */
public class IntersectionMappingSpec extends IntersectionMappingImpl {
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String getLabelComputationExpression() {
        return getLabelExpression();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public CreateCellTool getCreateCell() {
        return getCreate();
    }
}
