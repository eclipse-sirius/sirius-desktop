/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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
package org.eclipse.sirius.ui.debug;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.ext.emf.AllContents;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * Analyzes the inter-resource references inside a ResourceSet to discover its high-level topology and relationships
 * between resources.
 * 
 * @author pcdavid
 */
public class ResourceSetTopologyAnalyzer {
    public static class Reference {
        public final Resource sourceResource;

        public final Resource targetResource;

        public final EStructuralFeature feature;

        public int count = 1;

        public Reference(Resource sourceResource, Resource targetResource, EStructuralFeature feature) {
            this.sourceResource = sourceResource;
            this.targetResource = targetResource;
            this.feature = feature;
        }

        public boolean mergeIfEquivalent(Reference other) {
            if (this.sourceResource == other.sourceResource && this.targetResource == other.targetResource && this.feature == other.feature) {
                count += other.count;
                return true;
            } else {
                return false;
            }
        }
    }

    private final ResourceSet resourceSet;

    private final Multimap<EStructuralFeature, Reference> references = ArrayListMultimap.create();

    public ResourceSetTopologyAnalyzer(ResourceSet rs) {
        this.resourceSet = Objects.requireNonNull(rs);
    }

    public Multimap<EStructuralFeature, Reference> analyze() {
        EcoreUtil.resolveAll(resourceSet);
        references.clear();
        for (Resource res : resourceSet.getResources()) {
            Set<Reference> outgoingRefs = analyzeReferences(res);
            for (Reference ref : outgoingRefs) {
                record(ref);
            }
        }
        return references;
    }

    protected void record(Reference ref) {
        Collection<Reference> existing = references.get(ref.feature);
        for (Reference existingRef : existing) {
            if (existingRef.mergeIfEquivalent(ref)) {
                return;
            }
        }
        existing.add(ref);
    }

    private Set<Reference> analyzeReferences(Resource res) {
        Set<Reference> outgoing = new HashSet<>();
        for (EObject root : res.getContents()) {
            for (EObject obj : AllContents.of(root, true)) {
                outgoing.addAll(collectCrossResourceReferences(obj));
            }
        }
        return outgoing;
    }

    private Set<Reference> collectCrossResourceReferences(EObject src) {
        Set<Reference> result = new HashSet<>();
        Resource sourceResource = src.eResource();
        for (EContentsEList.FeatureIterator<?> featureIterator = (EContentsEList.FeatureIterator<?>) src.eCrossReferences().iterator(); featureIterator.hasNext();) {
            EObject tgt = (EObject) featureIterator.next();
            if (tgt != null) {
                EReference eReference = (EReference) featureIterator.feature();
                Resource targetResource = tgt.eResource();
                if (sourceResource != targetResource && !isIgnored(targetResource)) {
                    Reference r = new Reference(sourceResource, targetResource, eReference);
                    result.add(r);

                }
            }
        }
        return result;
    }

    private boolean isIgnored(Resource targetResource) {
        return targetResource == null || targetResource.getURI().scheme().equals("environment"); // ||
                                                                                                 // targetResource.getURI().scheme().equals("http")
                                                                                                 // ||
                                                                                                 // targetResource.getURI().scheme().equals("https");
    }

}
