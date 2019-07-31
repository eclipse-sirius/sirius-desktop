/*******************************************************************************
 * Copyright (c) 2012, 2018 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *    IBM Corporation and others - The deresolve() method was lifted from GMF's
 *         org.eclipse.gmf.runtime.emf.core.resources.GMFHelper
 *******************************************************************************/
package org.eclipse.sirius.business.internal.resource.parser;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIHelperImpl;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileVersionSAXParser;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.IdentifiedElement;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.osgi.framework.Version;

/**
 * A GMFHelper implementation for RepresentationsFile. Delegates the setValue method to
 * {@link RepresentationsFileMigrationService}.
 * 
 * @author fbarbin
 * 
 */
public class RepresentationsFileXMIHelper extends XMIHelperImpl {

    private static final String REPRESENTATION_NAME_FEATURE_LABEL = "name"; //$NON-NLS-1$

    private static final String REPRESENTATION_DOCUMENTATION_FEATURE_LABEL = "documentation"; //$NON-NLS-1$

    private static final String PLATFORM_SCHEME = "platform"; //$NON-NLS-1$

    private String version;

    private boolean migrationNeeded;

    /**
     * Constructor.
     * 
     * @param resource
     *            the current resource.
     */
    public RepresentationsFileXMIHelper(XMLResource resource) {
        super(resource);

        RepresentationsFileVersionSAXParser parser = new RepresentationsFileVersionSAXParser(resource.getURI());
        this.version = parser.getVersion(new NullProgressMonitor());
        this.migrationNeeded = RepresentationsFileMigrationService.getInstance().isMigrationNeeded(Version.parseVersion(version));
    }

    @Override
    public void setValue(EObject object, EStructuralFeature feature, Object value, int position) {
        if (migrationNeeded) {
            Object newValue = RepresentationsFileMigrationService.getInstance().getValue(object, feature, value, version);
            if (newValue != null) {
                super.setValue(object, feature, newValue, position);
                // we don't set value if the feature is the derived name one. Can happen when doing a migration with
                // name and documentation in DRepresentation.
            } else if (!(object instanceof DRepresentation && (REPRESENTATION_NAME_FEATURE_LABEL.equals(feature.getName()) || REPRESENTATION_DOCUMENTATION_FEATURE_LABEL.equals(feature.getName())))) {
                super.setValue(object, feature, value, position);
            }
        } else {
            super.setValue(object, feature, value, position);
        }

    }

    @Override
    public EObject createObject(EFactory eFactory, EClassifier type) {
        EFactory factory = eFactory;
        if (migrationNeeded) {
            // Handle EClass moved from one package to another,
            // prefix is not sufficient to retrieve the new package and factory
            // in
            // org.eclipse.emf.ecore.xmi.impl.XMLHandler.getFactoryForPrefix(String)
            // which does factory =
            // ePackage.getEFactoryInstance();
            // The migration participants return the new type with EClassifier
            // getType(EPackage, String, String).
            // Then we get the factory instance from the EClassifier's EPackage.
            if (type != null && type.getEPackage() != null && type.getEPackage().getEFactoryInstance() != null) {
                factory = type.getEPackage().getEFactoryInstance();
            }
        }
        EObject newObject = super.createObject(factory, type);
        if (migrationNeeded) {
            newObject = RepresentationsFileMigrationService.getInstance().updateCreatedObject(newObject, version);
        }
        return newObject;
    }

    /**
     * Extracted from org.eclipse.gmf.runtime.emf.core.resources.GMFHelper.deresolve(URI).
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.emf.core.resources.GMFHelper.deresolve(URI)
     * @see org.eclipse.emf.ecore.xmi.XMLHelper#deresolve(org.eclipse.emf.common.util.URI)
     */
    @Override
    public URI deresolve(URI uri) {

        // if this both target and container are within a platform resource and
        // projects or plugins are different then do not deresolve.
        // CHECKSTYLE:OFF
        if (((PLATFORM_SCHEME.equals(uri.scheme())) && (PLATFORM_SCHEME.equals(resourceURI.scheme()))) && ((uri.segmentCount() > 2) && (resourceURI.segmentCount() > 2))
                && ((!uri.segments()[0].equals(resourceURI.segments()[0])) || (!uri.segments()[1].equals(resourceURI.segments()[1])))) {
            // CHECKSTYLE:ON
            return uri;
        }
        return super.deresolve(uri);
    }

    @Override
    public String convertToString(EFactory factory, EDataType dataType, Object value) {
        String str = null;
        if (dataType.equals(ViewpointPackage.eINSTANCE.getResourceDescriptor())) {
            URI deresolvedURI = this.deresolve(((ResourceDescriptor) value).getResourceURI());
            if (deresolvedURI != null) {
                str = deresolvedURI.toString();
            }
        }

        return str != null ? str : super.convertToString(factory, dataType, value);
    }

    @Override
    protected Object createFromString(EFactory eFactory, EDataType eDataType, String value) {
        if (value != null && eDataType.equals(ViewpointPackage.eINSTANCE.getResourceDescriptor())) {
            // ResourceDescriptor(String) constructor converts string into URI
            // That URI is used to get a relative URI
            URI resolvedURI = new ResourceDescriptor(value).getResourceURI().resolve(resourceURI);
            return new ResourceDescriptor(resolvedURI);
        }
        return super.createFromString(eFactory, eDataType, value);
    }

    @Override
    public String getID(EObject obj) {
        if (obj instanceof IdentifiedElement && ((IdentifiedElement) obj).getUid() != null) {
            // Do not serialize xmi:id for identified elements as their uuid attribute is used to reference them.
            return null;
        }
        return super.getID(obj);
    }

}
