/*******************************************************************************
 * Copyright (c) 2009, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.format;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.formatdata.AbstractFormatData;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * An interface for all the SiriusFormatDataManager for mapping key (
 * {@link FormatDataKey}) and formatData ({@link AbstractFormatData}).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public interface SiriusFormatDataManager {

    /**
     * Get the format data corresponding to the key.
     * 
     * @param key
     *            The key
     * @return the format data corresponding to the key or null if not found.
     * @deprecated Replaced by
     *             {@link SiriusFormatDataManagerWithMapping#getFormatData(FormatDataKey, org.eclipse.sirius.viewpoint.description.RepresentationElementMapping)}
     *             handling more copy/paste cases by using mapping information.
     */
    @Deprecated
    AbstractFormatData getFormatData(FormatDataKey key);

    /**
     * Add a format data according to the key.
     * 
     * @param key
     *            The key
     * @param formatData
     *            The format data
     * @deprecated Replaced by
     *             {@link SiriusFormatDataManagerWithMapping#addFormatData(FormatDataKey, org.eclipse.sirius.viewpoint.description.RepresentationElementMapping, AbstractFormatData)}
     *             handling more copy/paste cases by using mapping information.
     */
    @Deprecated
    void addFormatData(FormatDataKey key, AbstractFormatData formatData);

    /**
     * Create a key corresponding to the semanticDecorator and available for
     * this manager.
     * 
     * @param semanticDecorator
     *            the semantic decorator
     * @return a new key corresponding to the semanticDecorator and available
     *         for this manager.
     */
    FormatDataKey createKey(DSemanticDecorator semanticDecorator);

    /**
     * Store the format data for this edit part and all it's children.
     * 
     * @param rootEditPart
     *            the root of the editParts to store.
     */
    void storeFormatData(IGraphicalEditPart rootEditPart);

    /**
     * Apply the current format data to the rootEditPart.
     * 
     * @param rootEditPart
     *            the root edit from which we would try to apply the current
     *            stored format
     */
    void applyFormat(IGraphicalEditPart rootEditPart);

    /**
     * Apply the current layout data to the rootEditPart.
     * 
     * @param rootEditPart
     *            the root edit from which we would try to apply the current
     *            stored format
     */
    void applyLayout(IGraphicalEditPart rootEditPart);

    /**
     * Apply the current style data to the rootEditPart.
     * 
     * @param rootEditPart
     *            the root edit from which we would try to apply the current
     *            stored style
     */
    void applyStyle(IGraphicalEditPart rootEditPart);

    /**
     * Check if the manager contains data.
     * 
     * @return true if the manager contains data, false otherwise.
     */
    boolean containsData();

    /**
     * Remove all the stored format data.
     */
    void clearFormatData();

}
