/*******************************************************************************
 * Copyright (c) 2015, 2018 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.filters.tool.abstracttool;

import org.eclipse.sirius.editor.properties.filters.tool.abstracttooldescription.AbstractToolDescriptionElementsToSelectFilter;
import org.eclipse.sirius.viewpoint.description.tool.GroupMenu;
import org.eclipse.sirius.viewpoint.description.tool.PopupMenu;

/**
 * A filter for the elementsToSelect property section.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class AbstractToolDescriptionElementsToSelectFilterSpec extends AbstractToolDescriptionElementsToSelectFilter {

    @Override
    protected boolean isRightInputType(Object arg0) {
        return arg0 instanceof org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription && !(arg0 instanceof PopupMenu || arg0 instanceof GroupMenu);
    }
}
