/**
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.diagram.sequence.description.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.sirius.diagram.description.impl.NodeMappingImpl;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.GateMapping;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Gate Mapping</b></em>'. <!-- end-user-doc -->
 *
 * @generated
 */
public class GateMappingImpl extends NodeMappingImpl implements GateMapping {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected GateMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.GATE_MAPPING;
    }

} // GateMappingImpl
