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
package org.eclipse.sirius.properties.core.internal.migration;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.migration.AbstractVSMMigrationParticipant;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.osgi.framework.Version;

/**
 * This migration participant is responsible for the migration of the fontSize
 * and labelFontSize properties of the widget style, text widget style, label
 * widget style, hyperlink widget style and group style.
 * 
 * @author sbegaudeau
 */
public class FontSizeMigrationParticipant extends AbstractVSMMigrationParticipant {

    private static final Version MIGRATION_VERSION = new Version("11.0.0.201609011200"); //$NON-NLS-1$

    /**
     * The name of the property label font size.
     */
    private static final String LABEL_FONT_SIZE = "labelFontSize"; //$NON-NLS-1$

    /**
     * The name of the property font size.
     */
    private static final String FONT_SIZE = "fontSize"; //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    public EStructuralFeature getAttribute(EClass eClass, String name, String loadedVersion) {
        if (Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION) < 0) {
            EAttribute eAttribute = null;

            if (PropertiesPackage.Literals.WIDGET_STYLE.isSuperTypeOf(eClass) && LABEL_FONT_SIZE.equals(name)) {
                eAttribute = PropertiesPackage.Literals.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION;
            } else if (PropertiesPackage.Literals.TEXT_WIDGET_STYLE.equals(eClass) && FONT_SIZE.equals(name)) {
                eAttribute = PropertiesPackage.Literals.TEXT_WIDGET_STYLE__FONT_SIZE_EXPRESSION;
            } else if (PropertiesPackage.Literals.LABEL_WIDGET_STYLE.equals(eClass) && FONT_SIZE.equals(name)) {
                eAttribute = PropertiesPackage.Literals.LABEL_WIDGET_STYLE__FONT_SIZE_EXPRESSION;
            } else if (PropertiesPackage.Literals.HYPERLINK_WIDGET_STYLE.equals(eClass) && FONT_SIZE.equals(name)) {
                eAttribute = PropertiesPackage.Literals.HYPERLINK_WIDGET_STYLE__FONT_SIZE_EXPRESSION;
            } else if (PropertiesPackage.Literals.GROUP_STYLE.equals(eClass) && FONT_SIZE.equals(name)) {
                eAttribute = PropertiesPackage.Literals.GROUP_STYLE__FONT_SIZE_EXPRESSION;
            }

            if (eAttribute != null) {
                return eAttribute;
            }
        }
        return super.getAttribute(eClass, name, loadedVersion);
    }

    @Override
    public Object getValue(EObject object, EStructuralFeature feature, Object value, String loadedVersion) {
        boolean isFontSizeFeature = false;
        isFontSizeFeature = isFontSizeFeature || PropertiesPackage.Literals.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION.equals(feature);
        isFontSizeFeature = isFontSizeFeature || PropertiesPackage.Literals.TEXT_WIDGET_STYLE__FONT_SIZE_EXPRESSION.equals(feature);
        isFontSizeFeature = isFontSizeFeature || PropertiesPackage.Literals.LABEL_WIDGET_STYLE__FONT_SIZE_EXPRESSION.equals(feature);
        isFontSizeFeature = isFontSizeFeature || PropertiesPackage.Literals.HYPERLINK_WIDGET_STYLE__FONT_SIZE_EXPRESSION.equals(feature);
        isFontSizeFeature = isFontSizeFeature || PropertiesPackage.Literals.GROUP_STYLE__FONT_SIZE_EXPRESSION.equals(feature);

        if (isFontSizeFeature && value != null) {
            return value.toString();
        }
        return super.getValue(object, feature, value, loadedVersion);
    }
}
