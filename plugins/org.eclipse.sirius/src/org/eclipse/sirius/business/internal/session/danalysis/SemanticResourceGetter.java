/*******************************************************************************
 * Copyright (c) 2012, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.viewpoint.DAnalysis;

/**
 * Helper to collect top-level semantic resources.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public final class SemanticResourceGetter {
    private SemanticResourceGetter() {
        // Prevent instanciation.
    }

    /**
     * Finds all the resources referenced as semantic models by any of the
     * analysis in the session which are not known to be controlled semantic
     * resources.
     * <p>
     * Uses {@code DAnalysisSessionEObjectImpl.getControlledResources()} to
     * determine if a resources is known to be controlled.
     * <p>
     * Performs a full walk on all referenced DAnalysis to identify their
     * models. Will load the analysis if needed, but not the semantic models.
     * 
     * @param session
     *            the session.
     * @return all the registered top-level semantic resources in the session.
     */
    public static Collection<Resource> collectTopLevelSemanticResources(DAnalysisSessionImpl session) {
        Collection<Resource> semanticResources = new LinkedHashSet<Resource>();
        for (DAnalysis analysis : session.allAnalyses()) {
            for (ResourceDescriptor resourceDesc : analysis.getSemanticResources()) {
                Resource resource = ModelUtils.getResource(session.getTransactionalEditingDomain().getResourceSet(), resourceDesc.getResourceURI());
                // empty resource are not taken into account because it can be
                // resource that has been created for bad reason
                if (resource != null && !resource.getContents().isEmpty()) {
                    semanticResources.add(resource);
                }
            }
        }

        semanticResources.removeAll(session.getControlledResources());
        return semanticResources;
    }
}
