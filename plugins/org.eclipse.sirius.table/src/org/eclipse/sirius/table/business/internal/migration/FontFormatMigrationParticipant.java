/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.business.internal.migration;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.metamodel.helper.FontFormatHelper;
import org.eclipse.sirius.business.api.migration.AbstractMigrationParticipant;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.osgi.framework.Version;

import com.google.common.collect.Lists;

/**
 * Remove the old font format attribute values used before the multi valued
 * label font format attribute.
 * 
 * @author mbats
 */
public class FontFormatMigrationParticipant extends AbstractMigrationParticipant {
    private static final String ITALIC = "italic";

    private static final String BOLD = "bold";

    private static final Version MIGRATION_VERSION = new Version("10.0.0.201505181740");

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    public Object getValue(EObject object, EStructuralFeature feature, Object value, String loadedVersion) {
        List<FontFormat> labelFormat = Lists.newArrayList();
        if (object instanceof BasicLabelStyleDescription) {
            if (feature.getEType() == ViewpointPackage.Literals.FONT_FORMAT) {
                if (value instanceof String) {
                    String oldFontFormat = (String) value;
                    if (oldFontFormat.contains(ITALIC)) {
                        FontFormatHelper.setFontFormat(labelFormat, FontFormat.ITALIC_LITERAL);
                    }
                    if (oldFontFormat.contains(BOLD)) {
                        FontFormatHelper.setFontFormat(labelFormat, FontFormat.BOLD_LITERAL);
                    }
                }
            }
        }
        if (object instanceof BasicLabelStyle) {
            if (feature.getEType() == ViewpointPackage.Literals.FONT_FORMAT) {
                if (value instanceof String) {
                    String oldFontFormat = (String) value;
                    if (oldFontFormat.contains(ITALIC)) {
                        FontFormatHelper.setFontFormat(labelFormat, FontFormat.ITALIC_LITERAL);
                    }
                    if (oldFontFormat.contains(BOLD)) {
                        FontFormatHelper.setFontFormat(labelFormat, FontFormat.BOLD_LITERAL);
                    }
                }
            }
        }
        if (labelFormat.size() == 0) {
            return null;
        }
        return labelFormat.toString().replaceAll(",", "").replace("[", "").replace("]", "");
    }
}
