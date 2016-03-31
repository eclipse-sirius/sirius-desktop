/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.sirius.diagram.BeginLabelStyle;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.description.style.BeginLabelStyleDescription;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.impl.BasicLabelStyleImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Begin Label Style</b></em>'. <!-- end-user-doc -->
 *
 * @generated
 */
public class BeginLabelStyleImpl extends BasicLabelStyleImpl implements BeginLabelStyle {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected BeginLabelStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.BEGIN_LABEL_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public BasicLabelStyleDescription getDescription() {
        return ((EdgeStyleDescription) ((EdgeStyle) eContainer()).getDescription()).getBeginLabelStyleDescription();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public void setDescription(BasicLabelStyleDescription description) {
        ((EdgeStyleDescription) ((EdgeStyle) eContainer()).getDescription()).setBeginLabelStyleDescription((BeginLabelStyleDescription) description);
    }

} // BeginLabelStyleImpl
