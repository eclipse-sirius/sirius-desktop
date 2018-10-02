/*******************************************************************************
 * Copyright (c) 2014 Obeo.
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
package org.eclipse.sirius.diagram.ui.tools.internal.properties.filter;

import org.eclipse.jface.viewers.IFilter;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;

/**
 * Property section filter for {@link DNodeListElementEditPart }.
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 */
public class DNodeListElementEditPartPropertySectionFilter implements IFilter {

    @Override
    public boolean select(Object toTest) {
        return toTest instanceof DNodeListElementEditPart;
    }

}
