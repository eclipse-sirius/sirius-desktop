/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.migration.description;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.osgi.framework.Version;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.business.api.migration.AbstractVSMMigrationParticipant;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * The VSM migration participant for the migration from OptionalLayer to
 * AdditionalLayer.
 * 
 * @author fbarbin
 * 
 */
public class OptionalLayersVSMMigrationParticipant extends AbstractVSMMigrationParticipant {

    /**
     * The VP version for which this migration is added.
     */
    private static final Version MIGRATION_VERSION = new Version("6.9.0.201308011200");

    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    public EClassifier getType(EPackage ePackage, String name, String loadedVersion) {
        if (Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION) < 0) {
            if (ePackage.getNsURI() != null && ePackage.getNsURI().equals(DescriptionPackage.eINSTANCE.getNsURI()) && name.equals("OptionalLayer")) {
                return DescriptionPackage.eINSTANCE.getAdditionalLayer();
            }
        }
        return super.getType(ePackage, name, loadedVersion);
    }

    @Override
    public EStructuralFeature getLocalElement(EClass eClass, String name, String loadedVersion) {
        if (Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION) < 0) {
            if (DescriptionPackage.eINSTANCE.getDiagramDescription().isSuperTypeOf(eClass) && name.equals("optionalLayers")) {
                return DescriptionPackage.eINSTANCE.getDiagramDescription_AdditionalLayers();
            }
        }
        return super.getLocalElement(eClass, name, loadedVersion);
    }

    @Override
    public Option<String> getNewFragment(String uriFragment, String loadedVersion) {
        Version loadedVersion2 = Version.parseVersion(loadedVersion);
        if (loadedVersion2.compareTo(MIGRATION_VERSION) < 0) {
            if (uriFragment.contains("@optionalLayers")) {
                String newUriFragment = uriFragment.replaceAll("@optionalLayers", "@additionalLayers");
                return Options.newSome(newUriFragment);
            }
        }
        return super.getNewFragment(uriFragment, loadedVersion);
    }
}
