/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.model.business.internal.description.spec;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramImportDescription;
import org.eclipse.sirius.diagram.description.Layer;

/**
 * Implementation of the DiagramImportDescription interface.
 * 
 * @author amartin
 */
public class DiagramImportDescriptionSpec extends DiagramDescriptionSpec implements DiagramImportDescription {

    /**
     * the wrapped diagram description.
     */
    protected DiagramDescription importedDiagramDescription;

    /**
     * Return the EClass corresponding to this object.
     * 
     * @overrides
     * @return the EClass corresponding to this object.
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.DIAGRAM_IMPORT_DESCRIPTION;
    }

    /**
     * Return the imported Diagram.
     * 
     * @return importedDiagramDescription
     */
    public DiagramDescription basicGetImportedDiagram() {
        return importedDiagramDescription;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DiagramDescription#getImportedDiagram()
     */
    @Override
    public DiagramDescription getImportedDiagram() {
        if (importedDiagramDescription != null && importedDiagramDescription.eIsProxy()) {
            final InternalEObject oldDiagramDescription = (InternalEObject) importedDiagramDescription;
            importedDiagramDescription = (DiagramDescription) eResolveProxy(oldDiagramDescription);
            if (importedDiagramDescription != oldDiagramDescription) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__IMPORTED_DIAGRAM, oldDiagramDescription, importedDiagramDescription));
                }
            }
        }
        return importedDiagramDescription;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.DiagramImportDescription#setImportedDiagram(org.eclipse.sirius.viewpoint.description.DiagramDescription)
     */
    @Override
    public void setImportedDiagram(final DiagramDescription value) {
        final DiagramDescription oldImportedDescription = importedDiagramDescription;
        importedDiagramDescription = value;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__IMPORTED_DIAGRAM, oldImportedDescription, importedDiagramDescription));
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramImportDescriptionImpl#eGet(int, boolean, boolean)
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        Object result = null;
        switch (featureID) {
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__IMPORTED_DIAGRAM:
            Object mapping;
            if (resolve) {
                mapping = getImportedDiagram();
            } else {
                mapping = basicGetImportedDiagram();
            }
            result = mapping;
            break;
        default:
            result = super.eGet(featureID, resolve, coreType);
            break;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramImportDescriptionImpl#eSet(int, java.lang.Object)
     */
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__IMPORTED_DIAGRAM:
            setImportedDiagram((DiagramDescription) newValue);
            return;
        default:
            break;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramDescriptionImpl#eUnset()
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__IMPORTED_DIAGRAM:
            setImportedDiagram((DiagramDescription) null);
            return;
        default:
            break;
        }
        super.eUnset(featureID);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramDescriptionImpl#getDefaultLayer()
     */
    @Override
    public Layer getDefaultLayer() {
        final DiagramDescription desc = getImportedDiagram();
        return desc != null ? desc.getDefaultLayer() : null;

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DiagramDescriptionImpl#getAdditionalLayers()
     */
    @Override
    public EList<AdditionalLayer> getAdditionalLayers() {

        if (additionalLayers == null) {
            additionalLayers = new EObjectContainmentEList.Resolving<AdditionalLayer>(AdditionalLayer.class, this, DescriptionPackage.DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS);
            if (importedDiagramDescription != null) {
                additionalLayers.addAll(importedDiagramDescription.getAdditionalLayers());
                // optionalLayers.addAll(ComponentizationHelper.getContributedLayers(importedDiagramDescription,
                // viewpoints));getOptionalLayers(importedDiagramDescription));
            }
        }
        return additionalLayers;
    }

    /**
     * Add the case importedDiagram to the default eIsSet method.
     * 
     * @see package org.eclipse.sirius.description.impl.DiagramDescriptionImpl #eIsSet()
     * 
     * @param featureID
     *            the id of the feature to check.
     * @return true if the feature is set
     */
    @Override
    public boolean eIsSet(final int featureID) {
        switch (featureID) {
        case DescriptionPackage.DIAGRAM_IMPORT_DESCRIPTION__IMPORTED_DIAGRAM:
            return importedDiagramDescription != null;
        default:
            return super.eIsSet(featureID);
        }
    }

}
