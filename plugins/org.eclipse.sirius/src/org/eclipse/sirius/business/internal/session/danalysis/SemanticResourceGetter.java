/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.sirius.business.internal.query.DAnalysisesInternalQuery;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * A {@link RunnableWithResult} to get semantic resources in a read transaction.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SemanticResourceGetter extends RunnableWithResult.Impl<Collection<Resource>> {

    private DAnalysisSessionImpl dAnalysisSessionImpl;

    /**
     * Default constructor.
     * 
     * @param dAnalysisSessionImpl
     *            the {@link DAnalysisSessionImpl} referencing the semantic
     *            resources
     */
    public SemanticResourceGetter(DAnalysisSessionImpl dAnalysisSessionImpl) {
        super();
        this.dAnalysisSessionImpl = dAnalysisSessionImpl;
    }

    /**
     * {@inheritDoc}
     */
    public void run() {
        Collection<Resource> semanticResources = new LinkedHashSet<Resource>();
        Collection<DAnalysis> allAnalyses = new DAnalysisesInternalQuery(dAnalysisSessionImpl.getAnalyses()).getAllAnalyses();
        for (DAnalysis analysis : allAnalyses) {
            if (analysis != null) {
                for (EObject model : new ArrayList<EObject>(analysis.getModels())) {
                    if (model != null) {
                        // TODO remove this try/catch once the offline mode will be supported
                        try {
                            final Resource resource = model.eResource();
                            if (resource != null) {
                                // CHECKSTYLE:OFF
                                if (!dAnalysisSessionImpl.getControlledResources().contains(resource)) {
                                    semanticResources.add(resource);
                                }
                                // CHECKSTYLE:ON
                            }
                        } catch (IllegalStateException e) {
                            // An issue has been encountered while
                            // connecting to
                            // remote CDO server
                            if (SiriusPlugin.getDefault().isDebugging()) {
                                SiriusPlugin.getDefault().getLog().log(new Status(IStatus.WARNING, SiriusPlugin.ID, "Error while connecting to remote CDO server"));
                            }
                        }
                    }
                }
            }
        }
        setResult(semanticResources);
    }

}
