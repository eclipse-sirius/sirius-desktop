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
