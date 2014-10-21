/**
 * Copyright (c) 2014 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Obeo - initial API and implementation
 */
package org.eclipse.sirius.sample.basicfamily.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.sirius.sample.basicfamily.BasicfamilyPackage;
import org.eclipse.sirius.sample.basicfamily.Man;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Man</b></em>'. <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ManImpl extends PersonImpl implements Man {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ManImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BasicfamilyPackage.Literals.MAN;
    }

} // ManImpl
