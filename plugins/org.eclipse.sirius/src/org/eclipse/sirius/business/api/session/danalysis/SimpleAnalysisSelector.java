/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
 * A selector that always selects the same analysis.
 * 
 * @author ymortier
 */
public class SimpleAnalysisSelector implements DAnalysisSelector {

    private final DAnalysis analysisToSelect;

    /**
     * Creates a new {@link SimpleAnalysisSelector}.
     * 
     * @param analysisToSelect
     *            the analysis to select.
     */
    public SimpleAnalysisSelector(final DAnalysis analysisToSelect) {
        this.analysisToSelect = analysisToSelect;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelector#selectSmartlyAnalysisForAddedResource(org.eclipse.emf.ecore.resource.Resource,
     *      java.util.Collection)
     */
    public DAnalysis selectSmartlyAnalysisForAddedResource(final Resource resource, final Collection<DAnalysis> allAnalysis) {
        return this.analysisToSelect;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelector#selectSmartlyAnalysisForAddedRepresentation(org.eclipse.sirius.viewpoint.DRepresentation,
     *      java.util.Collection)
     */
    public DAnalysis selectSmartlyAnalysisForAddedRepresentation(final DRepresentation representation, final Collection<DAnalysis> allAnalysis) {
        return this.analysisToSelect;
    }

}
