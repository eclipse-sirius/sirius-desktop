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
package org.eclipse.sirius.table.business.internal.metamodel.description.spec.util;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.description.TableTool;
import org.eclipse.sirius.table.metamodel.table.description.TableVariable;

/**
 * A specific {@link EObjectContainmentEList} for the {@link TableVariable}.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class TableVariableContainmentEList extends EObjectContainmentEList<TableVariable> {

    /**
     * 
     */
    private static final long serialVersionUID = -1416381022166192302L;

    /**
     * The constructor.
     * 
     * @param owner
     *            The owner of this list
     * @param featureID
     *            The feature ID of the elements of this list
     */
    public TableVariableContainmentEList(final InternalEObject owner, final int featureID) {
        super(TableVariable.class, owner, featureID);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.notify.impl.NotifyingListImpl#addUnique(java.lang.Object)
     */
    @Override
    public void addUnique(final TableVariable object) {
        // We remove the default tableVariable if it exists
        final TableVariable tableVariableToAdd = object;
        final String name = tableVariableToAdd.getName();
        final TableVariable variableWithSameName = TableHelper.getVariable((TableTool) owner, name);
        remove(variableWithSameName);
        super.addUnique(object);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.util.BasicEList#add(java.lang.Object)
     */
    @Override
    public boolean add(final TableVariable object) {
        // We remove the default tableVariable if it exists
        final TableVariable tableVariableToAdd = object;
        final String name = tableVariableToAdd.getName();
        final TableVariable variableWithSameName = TableHelper.getVariable((TableTool) owner, name);
        remove(variableWithSameName);
        return super.add(object);
    }

}
