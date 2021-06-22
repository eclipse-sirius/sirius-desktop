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

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.table.business.internal.metamodel.description.spec.util.TableVariableContainmentEList;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.TableVariable;
import org.eclipse.sirius.table.metamodel.table.description.impl.CreateLineToolImpl;

/**
 * Specific implementation for model instances.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class CreateLineToolSpec extends CreateLineToolImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.CreateToolImpl#getVariables()
     */
    @Override
    public EList<TableVariable> getVariables() {
        if (variables == null) {
            variables = new TableVariableContainmentEList(this, DescriptionPackage.CREATE_TOOL__VARIABLES);
        }
        return variables;
    }
}
