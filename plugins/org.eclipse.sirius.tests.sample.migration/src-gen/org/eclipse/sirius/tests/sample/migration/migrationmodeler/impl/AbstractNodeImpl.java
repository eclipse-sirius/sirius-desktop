/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNode;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Abstract Node</b></em>'. <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class AbstractNodeImpl extends GraphicalElementImpl implements AbstractNode {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AbstractNodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MigrationmodelerPackage.Literals.ABSTRACT_NODE;
    }

} // AbstractNodeImpl
