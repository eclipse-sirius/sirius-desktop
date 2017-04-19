/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.osgi.framework.Version;

/**
 * The DRepresentationDescriptor#representation feature is now derived, transient and volatile. It has been replaced by
 * the repPath feature that contains the URI as a String. This migration participant is about setting the repPath
 * feature instead of representation.
 * 
 * @author Florian Barbin
 */
public class ReplaceRepresentationByRepPathMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * The aird version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("12.0.0.201704191300"); //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    public Object getValue(EObject object, EStructuralFeature feature, Object value, String loadedVersion) {
        if (Version.parseVersion(loadedVersion).compareTo(ReplaceRepresentationByRepPathMigrationParticipant.MIGRATION_VERSION) < 0) {
            if (ViewpointPackage.eINSTANCE.getDRepresentationDescriptor_Representation().equals(feature)) {
                // Thanks to the getAffiliation overload, the return value will
                // be used to create a ResourceDescriptor.
                // The representation uri has already been resolved and is absolute.
                if (value instanceof InternalEObject) {
                    String strvalue = EcoreUtil.getURI((EObject) value).toString();
                    return strvalue;
                }
            }
        }
        return super.getValue(object, feature, value, loadedVersion);
    }

    @Override
    public EStructuralFeature getAffiliation(EClass eClass, EStructuralFeature eStructuralFeature, String loadedVersion) {
        if (Version.parseVersion(loadedVersion).compareTo(ReplaceRepresentationByRepPathMigrationParticipant.MIGRATION_VERSION) < 0) {
            if (ViewpointPackage.eINSTANCE.getDRepresentationDescriptor_Representation().equals(eStructuralFeature)) {
                return ViewpointPackage.eINSTANCE.getDRepresentationDescriptor_RepPath();
            }
        }
        return super.getAffiliation(eClass, eStructuralFeature, loadedVersion);
    }
}
