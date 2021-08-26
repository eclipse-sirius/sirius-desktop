/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.query;

import java.util.Iterator;

import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * A class aggregating all the queries (read-only!) having a {@link Group} as a
 * starting point.
 * 
 * @author mporhel
 * 
 */
public class GroupQuery {

    private Group group;

    /**
     * Create a new query.
     * 
     * @param group
     *            the element to query.
     */
    public GroupQuery(Group group) {
        this.group = group;
    }

    /**
     * Get a viewpoint in this {@link Group} named viewpointName, null else.
     * 
     * @param viewpointName
     *            the viewpointName to look for
     * 
     * @return the first {@link Viewpoint} of this Group
     */
    public Viewpoint getSiriusFromName(String viewpointName) {
        Viewpoint result = null;
        for (Viewpoint viewpoint : group.getOwnedViewpoints()) {
            if (viewpoint.getName() != null && viewpoint.getName().equals(viewpointName)) {
                result = viewpoint;
                break;
            }
        }
        return result;
    }

    /**
     * Return the {@link DiagramDescription} having the same name has the given
     * one.
     * 
     * @param diagramDescriptionName
     *            the {@link DiagramDescription} name.
     * @return the {@link DiagramDescription} having the same name has the given
     *         one .
     */
    public Option<DiagramDescription> findDiagramDescription(String diagramDescriptionName) {
        Iterator<Viewpoint> itSirius = group.getOwnedViewpoints().iterator();
        while (itSirius.hasNext()) {
            Viewpoint viewpoint = itSirius.next();
            Iterator<RepresentationDescription> itRepresentationDescription = new ViewpointQuery(viewpoint).getAllRepresentationDescriptions().iterator();
            while (itRepresentationDescription.hasNext()) {
                RepresentationDescription description = itRepresentationDescription.next();
                if (description instanceof DiagramDescription && ((DiagramDescription) description).getName() != null) {
                    if (((DiagramDescription) description).getName().equals(diagramDescriptionName)) {
                        return Options.newSome((DiagramDescription) description);
                    }
                }
            }
        }
        return Options.newNone();
    }

}
