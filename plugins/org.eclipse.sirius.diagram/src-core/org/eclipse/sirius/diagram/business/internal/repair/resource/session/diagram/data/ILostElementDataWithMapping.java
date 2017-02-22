/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.repair.resource.session.diagram.data;

import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * Lost element data with mapping.
 * 
 * @author dlecan
 */
public interface ILostElementDataWithMapping extends ILostElementDataWithTarget {

    /**
     * Sets the value of mapping to mapping.
     * 
     * @param mapping
     *            The mapping to set.
     */
    void setMapping(RepresentationElementMapping mapping);

    /**
     * Returns the mapping.
     * 
     * @return The mapping.
     */
    RepresentationElementMapping getMapping();

}
