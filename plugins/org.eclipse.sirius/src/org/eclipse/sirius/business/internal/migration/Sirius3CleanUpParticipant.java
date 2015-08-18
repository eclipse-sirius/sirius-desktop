/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.migration;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.osgi.framework.Version;

/**
 * Clean up viewpoint.ecore for Sirius3.
 * <ul>
 * <li>Remove DView.initialized attribute. <br>
 * EStructuralFeature corresponding to DView.initialized are put aside during
 * load and remove from resourceMap on postLoad.</li>
 * </ul>
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class Sirius3CleanUpParticipant extends AbstractRepresentationsFileMigrationParticipant {
    /**
     * The VP version for which this migration is added.
     */
    private static final Version MIGRATION_VERSION = new Version("10.0.0.201504010000"); //$NON-NLS-1$

    private Map<DView, EStructuralFeature> initializedFeature = new HashMap<DView, EStructuralFeature>();

    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            for (java.util.Map.Entry<DView, EStructuralFeature> feature : initializedFeature.entrySet()) {
                DView dView = feature.getKey();
                EStructuralFeature eStructuralFeature = feature.getValue();
                Resource eResource = dView.eResource();
                AnyType anyType = ((XMLResource) eResource).getEObjectToExtensionMap().get(dView);
                FeatureMap any = anyType.getAnyAttribute();
                Entry entryToRemove = null;
                for (Entry entry : any) {
                    if (entry.getEStructuralFeature().equals(eStructuralFeature)) {
                        entryToRemove = entry;
                        break;
                    }
                }
                any.remove(entryToRemove);
            }
        }
        initializedFeature.clear();
        super.postLoad(dAnalysis, loadedVersion);
    }

    @Override
    protected void handleFeature(EObject owner, EStructuralFeature unkownFeature, Object valueOfUnknownFeature) {
        if (owner instanceof DView && unkownFeature.getName().equals("initialized")) { //$NON-NLS-1$
            Resource eResource = owner.eResource();
            if (eResource != null) {
                AnyType anyType = ((XMLResource) eResource).getEObjectToExtensionMap().get(owner);
                if (anyType != null) {
                    FeatureMap any = anyType.getAnyAttribute();
                    for (Entry entry : any) {
                        if (entry.getEStructuralFeature().equals(unkownFeature)) {
                            initializedFeature.put((DView) owner, unkownFeature);
                        }
                    }
                }
            }
        }
        super.handleFeature(owner, unkownFeature, valueOfUnknownFeature);
    }

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }
}
