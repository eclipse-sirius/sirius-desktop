/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.filters.tool.abstracttool;

import org.eclipse.sirius.editor.properties.filters.tool.abstracttooldescription.AbstractToolDescriptionElementsToSelectFilter;
import org.eclipse.sirius.viewpoint.description.tool.PopupMenu;

/**
 * A filter for the elementsToSelect property section.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class AbstractToolDescriptionElementsToSelectFilterSpec extends AbstractToolDescriptionElementsToSelectFilter {

    @Override
    protected boolean isRightInputType(Object arg0) {
        return arg0 instanceof org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription && !(arg0 instanceof PopupMenu);
    }
}
