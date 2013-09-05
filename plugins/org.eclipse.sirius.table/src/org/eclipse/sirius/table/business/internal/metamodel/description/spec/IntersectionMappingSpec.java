/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
