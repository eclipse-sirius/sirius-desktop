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
package org.eclipse.sirius.table.business.internal.dialect;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

import com.google.common.base.Function;

import org.eclipse.sirius.description.contribution.Contribution;
import org.eclipse.sirius.description.contribution.ContributionProvider;
import org.eclipse.sirius.ext.emf.AllContents;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.internal.contribution.IncrementalModelContributor;
import org.eclipse.sirius.business.internal.contribution.ReferenceResolver;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Customization of the generic IncrementalModelContributor for computing
 * effective EditionTableDescriptions.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public final class TableModelContributor extends IncrementalModelContributor {
    /**
     * Constructor.
     * 
     * @param contributionFinder
     *            the function to use to find all the potentially applicable
     *            contributions from a source model
     * @param resolver
     *            the resolver to use on the contributions to locate their
     *            actual source and target elements
     * @param idFunction
     *            the function used to obtain logical identifiers for model
     *            elements, required to perform the incremental update
     */
    public TableModelContributor(Function<Iterable<EObject>, Iterable<Contribution>> contributionFinder, ReferenceResolver resolver, Function<EObject, Object> idFunction) {
        super(contributionFinder, resolver, idFunction);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.internal.contribution.IncrementalModelContributor#findAllContributions(org.eclipse.emf.ecore.EObject,
     *      java.lang.Iterable)
     */
    @Override
    protected List<Contribution> findAllContributions(EObject targetModel, Iterable<EObject> sources) {
        this.contributionFinder = new TableContributionsFinder((TableDescription) targetModel) {
            @Override
            protected String getSiriusURI(Viewpoint vp) {
                return TableModelContributor.this.getSiriusURI(vp);
            }
        };
        return super.findAllContributions(targetModel, sources);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.internal.contribution.IncrementalModelContributor#postProcess(org.eclipse.emf.ecore.EObject)
     */
    @Override
    protected void postProcess(EObject result) {
        incorporateAllReusedElements((TableDescription) result);
        removeContributions(result);
    }

    private void incorporateAllReusedElements(TableDescription result) {
        EList<EObject> imported = result.getImportedElements();
        for (EObject obj : AllContents.of(result, true)) {
            for (EReference re : obj.eClass().getEAllReferences()) {
                if (isReuseReference(re)) {
                    for (EObject o : new EObjectQuery(obj).getValues(re)) {
                        if (!EcoreUtil.isAncestor(result, o)) {
                            imported.add(o);
                        }
                    }
                }
            }
        }
    }

    private boolean isReuseReference(EReference re) {
        return !re.isContainment() && re.getName().startsWith("reused"); //$NON-NLS-1$
    }

    private void removeContributions(EObject obj) {
        if (obj instanceof ContributionProvider) {
            ((ContributionProvider) obj).getContributions().clear();
        }
        for (EObject child : obj.eContents()) {
            removeContributions(child);
        }
    }
}
