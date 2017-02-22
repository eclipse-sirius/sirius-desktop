/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.format;

import org.eclipse.sirius.diagram.formatdata.AbstractFormatData;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * An interface extending {@link SiriusFormatDataManager} to add the capacity to
 * get formats during format pasting by taking in consideration the key and the
 * mapping when more than one formats are associated to the key.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public interface SiriusFormatDataManagerWithMapping extends SiriusFormatDataManager {

    /**
     * Get the format data with the best corresponding to the key.
     * 
     * @param key
     *            The key
     * @param mapping
     *            the mapping used to discriminate returned
     *            {@link AbstractFormatData} when the key is attached to more
     *            than one format.
     * @return the format data corresponding to the key or null if not found.
     */
    AbstractFormatData getFormatData(FormatDataKey key, RepresentationElementMapping mapping);

    /**
     * Add a format data according to the key and the mapping information.
     * 
     * @param key
     *            The key
     * @param mapping
     *            the mapping used to discriminate returned
     *            {@link AbstractFormatData} when the key is attached to more
     *            than one format.
     * @param formatData
     *            The format data associated to the key and mapping.
     */
    void addFormatData(FormatDataKey key, RepresentationElementMapping mapping, AbstractFormatData formatData);

}
