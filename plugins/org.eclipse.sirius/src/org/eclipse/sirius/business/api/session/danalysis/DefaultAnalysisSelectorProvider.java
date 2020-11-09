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
package org.eclipse.sirius.business.api.session.danalysis;

import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * A default analysis selector provider to make sure there is always at least one present even when no UI is there.
 * 
 * @author cedric
 *
 */
public class DefaultAnalysisSelectorProvider implements DAnalysisSelectorProvider {

    @Override
    public boolean provides(DAnalysisSession session) {
        return true;
    }

    @Override
    public DAnalysisSelector getSelector(DAnalysisSession session) {
        return new DAnalysisSelector() {

            @Override
            public DAnalysis selectSmartlyAnalysisForAddedResource(Resource resource, Collection<DAnalysis> allAnalysis) {
                return allAnalysis.iterator().next();
            }

            @Override
            public DAnalysis selectSmartlyAnalysisForAddedRepresentation(DRepresentation representation, Collection<DAnalysis> allAnalysis) {
                return allAnalysis.iterator().next();
            }
        };
    }

    @Override
    public int getPriority() {
        // This is the default DAnalysisSelectorProvider with the lowest possible priority.
        return 0;
    }
}
