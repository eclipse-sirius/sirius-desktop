/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.api;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * This class is used to convert the style of the widgets.
 * 
 * @author sbegaudeau
 * 
 * @param <SIRIUS>
 *            The type of the Sirius EObject
 */
public class DefaultStyleDescriptionConverter<SIRIUS extends EObject> extends DefaultDescriptionConverter<SIRIUS> {

    /**
     * The constructor.
     * 
     * @param siriusClass
     *            The class of the Sirius EObject
     * @param eefEClass
     *            The EClass of the EEF EObject
     */
    public DefaultStyleDescriptionConverter(Class<SIRIUS> siriusClass, EClass eefEClass) {
        super(siriusClass, eefEClass);
    }

    @Override
    protected void convertEAttribute(SIRIUS siriusEObject, EObject eefEObject, EAttribute eAttribute, Map<String, Object> parameters) {
        if (eAttribute.getName().equals("fontFormat")) { //$NON-NLS-1$
            Object fontFormat = siriusEObject.eGet(eAttribute);
            EStructuralFeature fontStyleExpressionEAttribute = eefEObject.eClass().getEStructuralFeature("fontStyleExpression"); //$NON-NLS-1$
            eefEObject.eSet(fontStyleExpressionEAttribute, this.getFontStyleExpression(fontFormat));
        } else if (eAttribute.getName().equals("labelFontFormat")) { //$NON-NLS-1$
            Object fontFormat = siriusEObject.eGet(eAttribute);
            EStructuralFeature fontStyleExpressionEAttribute = eefEObject.eClass().getEStructuralFeature("labelFontStyleExpression"); //$NON-NLS-1$
            eefEObject.eSet(fontStyleExpressionEAttribute, this.getFontStyleExpression(fontFormat));
        } else {
            super.convertEAttribute(siriusEObject, eefEObject, eAttribute, parameters);
        }
    }

    @Override
    protected void convertEReference(SIRIUS siriusEObject, EObject eefEObject, EReference eReference, Map<String, Object> parameters, DescriptionCache cache) {
        if (eReference.getEReferenceType().getInstanceClass().equals(ColorDescription.class)) {
            EStructuralFeature eefEStructuralFeature = eefEObject.eClass().getEStructuralFeature(eReference.getName() + "Expression"); //$NON-NLS-1$
            if (eefEStructuralFeature.getEType().getInstanceClass().equals(String.class)) {
                Object siriusColorDescription = siriusEObject.eGet(eReference);
                if (siriusColorDescription instanceof ColorDescription) {
                    String colorExpression = this.getColorExpression((ColorDescription) siriusColorDescription, parameters);
                    eefEObject.eSet(eefEStructuralFeature, colorExpression);
                }
            }
        } else {
            super.convertEReference(siriusEObject, eefEObject, eReference, parameters, cache);
        }
    }

    /**
     * Returns the font style expression.
     * 
     * @param fontFormats
     *            The font formats
     * @return The font style expression
     */
    private String getFontStyleExpression(Object fontFormats) {
        String fontFormat = fontFormats.toString();
        String fontStyle = fontFormat.substring(1, fontFormat.length() - 1);
        if ("".equals(fontStyle)) { //$NON-NLS-1$
            return null;
        }
        return fontStyle;
    }
}
