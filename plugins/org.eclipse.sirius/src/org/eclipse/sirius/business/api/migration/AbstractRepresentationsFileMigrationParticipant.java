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
package org.eclipse.sirius.business.api.migration;

import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.business.api.query.AirDResouceQuery;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.osgi.framework.Version;

/**
 * <p>
 * Clients wishing to participate in the migration process of a representations
 * file must subclass this class, and implement
 * {@link #getAttribute(org.eclipse.emf.ecore.EClass, String, String)},
 * {@link #getLocalElement(org.eclipse.emf.ecore.EClass, String, String)},
 * {@link #getType(org.eclipse.emf.ecore.EPackage, String, String)}, etc.
 * </p>
 * <p>
 * This class is intended to be subclassed by clients.
 * </p>
 * 
 * @author lredor
 */
public abstract class AbstractRepresentationsFileMigrationParticipant extends AbstractMigrationParticipant {
    /**
     * {@inheritDoc}
     */
    public void postLoad(XMLResource resource, String loadedVersion) {
        super.postLoad(resource, loadedVersion);
        // The migration often uses dAnalysis and loaded version at osgi form
        if (resource instanceof AirdResource) {
            AirDResouceQuery query = new AirDResouceQuery((AirdResource) resource);
            Option<DAnalysis> optionalAnalysis = query.getDAnalysis();
            if (optionalAnalysis.some()) {
                DAnalysis dAnalysis = optionalAnalysis.get();
                postLoad(dAnalysis, Version.parseVersion(loadedVersion));
            }
        }
    }

    /**
     * The post load step often uses {@link DAnalysis} and loaded version at
     * osgi form so this method is called by the
     * {@link #postLoad(XMLResource, String)} method, to avoid boring job of
     * getting dAnalysis and parsing version.
     * 
     * @param dAnalysis
     *            The analysis of the resource to migrate
     * @param loadedVersion
     *            the osgi form of the loaded version
     */
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        // Nothing to migrate by default.
    }
}
