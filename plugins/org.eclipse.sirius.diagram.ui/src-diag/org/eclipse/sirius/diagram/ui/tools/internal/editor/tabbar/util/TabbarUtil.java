/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Utility class for tabbar management.
 * 
 * @author fbarbin
 * 
 */
public final class TabbarUtil {

    private TabbarUtil() {
        // nothing
    }

    /**
     * Returns the dialect uri if given part represent a {@link DialectEditor}.
     * 
     * @param part
     *            the editor.
     * @return {@link URI} as string or {@link StringUtil#EMPTY_STRING} if
     *         cannot retrieves representation uri.
     */
    public static String getDRepresentationURIFromWorkbenchPart(IWorkbenchPart part) {
        if (part instanceof DialectEditor) {
            DRepresentation representation = ((DialectEditor) part).getRepresentation();
            // Representation could be null : error editor.
            if (representation != null) {
                URI uri = EcoreUtil.getURI(representation);
                if (uri != null) {
                    return uri.toString();
                }
            }
        }
        return StringUtil.EMPTY_STRING;
    }
}
