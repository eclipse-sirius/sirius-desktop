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
import org.eclipse.sirius.sample.basicfamily.Woman;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Woman</b></em>'. <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class WomanImpl extends PersonImpl implements Woman {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected WomanImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BasicfamilyPackage.Literals.WOMAN;
    }

} // WomanImpl
