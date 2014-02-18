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

import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Behavior to control similar data.
 * 
 * @author dlecan
 */
public interface ISimilarLostElementData {

    /**
     * Check if current element data is similar to specified arguments.
     * <p>
     * This method checks, recursively if needed:
     * </p>
     * <ul>
     * <li>Mapping name extrated from diagramElement</li>
     * <li>Equality of references of target objects</li>
     * </ul>
     * 
     * @param semanticDecorator
     *            Element where to extract mapping name and target object.
     * @return <code>true</code> If current data is similar to specified diagram
     *         element.
     */
    boolean isSimilarTo(DSemanticDecorator semanticDecorator);

}
