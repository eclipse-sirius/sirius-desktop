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
package org.eclipse.sirius.viewpoint.description.style.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.business.internal.color.DefaultColorStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyles;
import org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleFactory;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.style.TooltipStyleDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class StyleFactoryImpl extends EFactoryImpl implements StyleFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static StyleFactory init() {
        try {
            StyleFactory theStyleFactory = (StyleFactory) EPackage.Registry.INSTANCE.getEFactory(StylePackage.eNS_URI);
            if (theStyleFactory != null) {
                return theStyleFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new StyleFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public StyleFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION:
            return createBasicLabelStyleDescription();
        case StylePackage.LABEL_STYLE_DESCRIPTION:
            return createLabelStyleDescription();
        case StylePackage.LABEL_BORDER_STYLES:
            return createLabelBorderStyles();
        case StylePackage.LABEL_BORDER_STYLE_DESCRIPTION:
            return createLabelBorderStyleDescription();
        case StylePackage.TOOLTIP_STYLE_DESCRIPTION:
            return createTooltipStyleDescription();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public BasicLabelStyleDescription createBasicLabelStyleDescription() {
        BasicLabelStyleDescriptionImpl basicLabelStyleDescription = new BasicLabelStyleDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(basicLabelStyleDescription);
        return basicLabelStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public LabelStyleDescription createLabelStyleDescription() {
        LabelStyleDescriptionImpl labelStyleDescription = new LabelStyleDescriptionImpl();
        new DefaultColorStyleDescription().setDefaultColors(labelStyleDescription);
        return labelStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public LabelBorderStyles createLabelBorderStyles() {
        LabelBorderStylesImpl labelBorderStyles = new LabelBorderStylesImpl();
        return labelBorderStyles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public LabelBorderStyleDescription createLabelBorderStyleDescription() {
        LabelBorderStyleDescriptionImpl labelBorderStyleDescription = new LabelBorderStyleDescriptionImpl();
        return labelBorderStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TooltipStyleDescription createTooltipStyleDescription() {
        TooltipStyleDescriptionImpl tooltipStyleDescription = new TooltipStyleDescriptionImpl();
        return tooltipStyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public StylePackage getStylePackage() {
        return (StylePackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static StylePackage getPackage() {
        return StylePackage.eINSTANCE;
    }

} // StyleFactoryImpl
