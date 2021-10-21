/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.business.api.query;

import java.util.Objects;

import org.eclipse.sirius.table.metamodel.table.DCellStyle;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.metamodel.table.TablePackage;

/**
 * A class aggregating all the queries (read-only!) having a
 * {@link DTableElementStyle} as a starting point.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class DTableElementStyleQuery {

    private final DTableElementStyle tableElementStyle;

    /**
     * Creates a new query.
     * 
     * @param tableElementStyle
     *            the cell to query.
     */
    public DTableElementStyleQuery(DTableElementStyle tableElementStyle) {
        this.tableElementStyle = Objects.requireNonNull(tableElementStyle);
    }

    /**
     * Returns whether at least one of the features of the object is considered
     * to be set.
     * 
     * @return whether at least one of the features of the object is set.
     */
    public boolean isSet() {
        boolean result = tableElementStyle.eIsSet(TablePackage.eINSTANCE.getDTableElementStyle_BackgroundColor()) && tableElementStyle.getBackgroundColor() != null;
        result = result || tableElementStyle.eIsSet(TablePackage.eINSTANCE.getDTableElementStyle_DefaultBackgroundStyle());
        result = result || tableElementStyle.eIsSet(TablePackage.eINSTANCE.getDTableElementStyle_DefaultForegroundStyle());
        result = result || tableElementStyle.eIsSet(TablePackage.eINSTANCE.getDTableElementStyle_ForegroundColor()) && tableElementStyle.getForegroundColor() != null;
        result = result || tableElementStyle.eIsSet(TablePackage.eINSTANCE.getDTableElementStyle_LabelFormat()) && tableElementStyle.getLabelFormat() != null;
        result = result || tableElementStyle.eIsSet(TablePackage.eINSTANCE.getDTableElementStyle_LabelSize());
        if (tableElementStyle instanceof DCellStyle) {
            result = result || tableElementStyle.eIsSet(TablePackage.eINSTANCE.getDCellStyle_BackgroundStyleOrigin()) && ((DCellStyle) tableElementStyle).getBackgroundStyleOrigin() != null;
            result = result || tableElementStyle.eIsSet(TablePackage.eINSTANCE.getDCellStyle_ForegroundStyleOrigin()) && ((DCellStyle) tableElementStyle).getForegroundStyleOrigin() != null;
        }
        return result;
    }
}
