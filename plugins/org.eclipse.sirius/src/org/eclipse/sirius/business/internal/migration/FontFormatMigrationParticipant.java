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
package org.eclipse.sirius.business.internal.migration;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.metamodel.helper.FontFormatHelper;
import org.eclipse.sirius.business.api.migration.AbstractMigrationParticipant;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.osgi.framework.Version;

import com.google.common.collect.Lists;

/**
 * Remove the old font format attribute values used before the multi valued
 * label font format attribute.
 * 
 * @author mbats
 */
public class FontFormatMigrationParticipant extends AbstractMigrationParticipant {

    private static final Version MIGRATION_VERSION = new Version("10.0.0.201505222000"); //$NON-NLS-1$

    private static final String ITALIC = "italic"; //$NON-NLS-1$

    private static final String BOLD = "bold"; //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    public Object getValue(EObject object, EStructuralFeature feature, Object value, String loadedVersion) {
        if (Version.parseVersion(loadedVersion).compareTo(FontFormatMigrationParticipant.MIGRATION_VERSION) < 0) {
            // All features typed by FontFormat have been modified: the
            // cardinality has been changed from [0..1] to [0..n], the value,
            // which was a FontFormat, now has to be a list of FontFormat.
            // The previous "normal" value correspond to an empty list, it was
            // the default value and was not serialized.
            if (feature.getEType() == ViewpointPackage.Literals.FONT_FORMAT && value instanceof String) {
                List<FontFormat> labelFormat = Lists.newArrayList();
                String oldFontFormat = (String) value;
                if (oldFontFormat.contains(ITALIC)) {
                    FontFormatHelper.setFontFormat(labelFormat, FontFormat.ITALIC_LITERAL);
                }
                // The previous "bold_italic" value is treated by the two
                // "if contains" blocks.
                if (oldFontFormat.contains(BOLD)) {
                    FontFormatHelper.setFontFormat(labelFormat, FontFormat.BOLD_LITERAL);
                }
                return labelFormat.toString().replaceAll(",", "").replace("[", "").replace("]", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            }
        }
        return null;
    }
}
