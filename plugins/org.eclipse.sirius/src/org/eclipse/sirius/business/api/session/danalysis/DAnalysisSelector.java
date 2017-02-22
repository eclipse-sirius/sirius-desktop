/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.session.danalysis;

import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * a callback to select an analysis.
 * 
 * @author mchauvin
 */
public interface DAnalysisSelector {

    /**
     * Select the most smartly you can the right analysis to store a new new
     * view from this resource.
     * 
     * @param resource
     *            the resource
     * @param allAnalysis
     *            the analysis candidates
     * @return the selected analysis
     */
    DAnalysis selectSmartlyAnalysisForAddedResource(Resource resource, Collection<DAnalysis> allAnalysis);

    /**
     * Select the most smartly you can the right analysis to store a new
     * representation.
     * 
     * @param representation
     *            the representation.
     * @param allAnalysis
     *            the analysis candidates
     * @return the selected analysis
     */
    DAnalysis selectSmartlyAnalysisForAddedRepresentation(DRepresentation representation, Collection<DAnalysis> allAnalysis);

}
