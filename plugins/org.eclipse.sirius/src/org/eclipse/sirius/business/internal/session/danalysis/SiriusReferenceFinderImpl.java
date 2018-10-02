/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.query.SiriusReferenceFinder;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

public class SiriusReferenceFinderImpl implements SiriusReferenceFinder {

    /**
     * EReferences used to find inverse cross references.
     */
    protected final List<EReference> refs = Arrays.asList(ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET, ViewpointPackage.Literals.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS);

    /**
     * The session.
     */
    protected DAnalysisSessionImpl dAnalysisSessionImpl;

    /**
     * Default constructor.
     * 
     * @param dAnalysisSessionImpl
     *            the session
     */
    public SiriusReferenceFinderImpl(DAnalysisSessionImpl dAnalysisSessionImpl) {
        this.dAnalysisSessionImpl = dAnalysisSessionImpl;
    }

    @Override
    public Map<EObject, Collection<EObject>> getReferencingSiriusElements(Collection<EObject> semanticObjects, SearchScope searchScope) {
        if (SearchScope.ALL_REPRESENTATIONS_SCOPE.equals(searchScope)) {
            loadAllRepresentations();
        }
        Map<EObject, Collection<EObject>> result = new LinkedHashMap<>();
        for (EObject semanticObject : semanticObjects) {
            Collection<EObject> siriusElements = dAnalysisSessionImpl.getSemanticCrossReferencer().getNonNavigableInverseReferences(semanticObject).stream() //
                    .filter(setting -> refs.contains(setting.getEStructuralFeature())) // keep only refs features
                    .map(Setting::getEObject).collect(Collectors.toSet());
            result.put(semanticObject, siriusElements);
        }

        return result;
    }

    @Override
    public Map<EObject, Collection<DRepresentationDescriptor>> getImpactedRepresentationDescriptors(Collection<EObject> semanticObjects, SearchScope searchScope) {
        Map<EObject, Collection<DRepresentationDescriptor>> impactedRepDesc = new LinkedHashMap<>();
        Map<EObject, Collection<EObject>> referencingSiriusElements = getReferencingSiriusElements(semanticObjects, searchScope);
        for (EObject semanticObject : semanticObjects) {
            Set<DRepresentationDescriptor> repDescs = new LinkedHashSet<>();
            referencingSiriusElements.get(semanticObject).stream().forEach(obj -> {
                Option<DRepresentation> representation = new EObjectQuery(obj).getRepresentation();
                if (representation.some()) {
                    DRepresentationDescriptor representationDescriptor = new DRepresentationQuery(representation.get()).getRepresentationDescriptor();
                    if (representationDescriptor != null) {
                        repDescs.add(representationDescriptor);
                    }
                }
            });
            if (repDescs.size() > 0) {
                impactedRepDesc.put(semanticObject, repDescs);
            }
        }
        return impactedRepDesc;
    }

    private void loadAllRepresentations() {
        DialectManager.INSTANCE.getAllRepresentationDescriptors(dAnalysisSessionImpl).stream().forEach(repDesc -> repDesc.getRepresentation());
    }
}
