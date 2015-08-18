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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.osgi.framework.Version;

/**
 * Migration participant about the replacement on DAnalysis of models tag by
 * semanticResources tag.<br>
 * Models tag is not serialized anymore.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class ReplaceModelsBySemanticResources extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * The VP version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("10.0.0.201505191300"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    public Object getValue(EObject object, EStructuralFeature feature, Object value, String loadedVersion) {
        if (Version.parseVersion(loadedVersion).compareTo(ReplaceModelsBySemanticResources.MIGRATION_VERSION) < 0) {
            if (ViewpointPackage.eINSTANCE.getDAnalysis_Models().equals(feature)) {
                // Thanks to the getAffiliation overload, the return value will
                // be used to create a ResourceDescriptor.
                // The models uri has already been resolved and is absolute.
                if (value instanceof InternalEObject) {
                    String strvalue = ((InternalEObject) value).eProxyURI().trimFragment().toString();
                    return strvalue;
                }
            }
        }
        return super.getValue(object, feature, value, loadedVersion);
    }

    @Override
    public EStructuralFeature getAffiliation(EClass eClass, EStructuralFeature eStructuralFeature, String loadedVersion) {
        if (Version.parseVersion(loadedVersion).compareTo(ReplaceModelsBySemanticResources.MIGRATION_VERSION) < 0) {
            if (ViewpointPackage.eINSTANCE.getDAnalysis_Models().equals(eStructuralFeature)) {
                return ViewpointPackage.eINSTANCE.getDAnalysis_SemanticResources();
            }
        }
        return super.getAffiliation(eClass, eStructuralFeature, loadedVersion);
    }

}
