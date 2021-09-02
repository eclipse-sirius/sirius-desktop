/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.migration;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.xmi.UnresolvedReferenceException;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.Messages;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.osgi.framework.Version;

/**
 * This migration participant unsets the originalStyle feature for the instance of type DNode, DDiagramElementContainer,
 * DEdge and DNodeListElement.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 *
 */
public class UnsetOriginalStyleFeatureMigrationParticipant extends AbstractRepresentationsFileMigrationParticipant {

    /**
     * Migration version.
     */
    public static final Version MIGRATION_VERSION = new Version("14.5.0.202104161500"); //$NON-NLS-1$

    private int numberOfOrignalStyeFeatureUnset;

    private final List<EReference> originalStyleReferences = Arrays.asList(DiagramPackage.eINSTANCE.getDNode_OriginalStyle(), DiagramPackage.eINSTANCE.getDEdge_OriginalStyle(),
            DiagramPackage.eINSTANCE.getDDiagramElementContainer_OriginalStyle(), DiagramPackage.eINSTANCE.getDNodeListElement_OriginalStyle());

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    public Object getValue(EObject object, EStructuralFeature feature, Object value, String loadedVersion) {
        if (Version.parseVersion(loadedVersion).compareTo(UnsetOriginalStyleFeatureMigrationParticipant.MIGRATION_VERSION) < 0) {
            if (originalStyleReferences.contains(feature)) {

                numberOfOrignalStyeFeatureUnset++;
                return null;
            }
        }
        return super.getValue(object, feature, value, loadedVersion);
    }

    /**
     * This method is overridden to unset originalStyle features.<br/>
     * It is not sufficient to implement {@code getValue(EObject , EStructuralFeature , Object , String )} method for
     * not dangling features.
     * 
     * @see org.eclipse.sirius.business.api.migration.AbstractRepresentationsFileMigrationParticipant#postLoad(org.eclipse.sirius.viewpoint.DAnalysis,
     *      org.osgi.framework.Version)
     */
    @Override
    protected void postLoad(DAnalysis dAnalysis, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            //@formatter:off 
            dAnalysis.getOwnedViews().stream()
                .flatMap(dView -> new DViewQuery(dView).getLoadedRepresentations().stream())
                .filter(DDiagram.class::isInstance)
                .flatMap(diagram -> new DDiagramQuery((DDiagram) diagram).getAllDiagramElements().stream())
                .forEach(diagElement -> {
                    if (diagElement instanceof DNode) {
                        ((DNode) diagElement).setOriginalStyle(null);
                    } else if (diagElement instanceof DEdge) {
                        ((DEdge) diagElement).setOriginalStyle(null);
                    } else if (diagElement instanceof DDiagramElementContainer) {
                        ((DDiagramElementContainer) diagElement).setOriginalStyle(null);
                    } else if (diagElement instanceof DNodeListElement) {
                        ((DNodeListElement) diagElement).setOriginalStyle(null);
                    }
                });
            //@formatter:on
        }
    }

    @Override
    public void postLoad(XMLResource resource, String loadedVersion) {
        Iterator<Diagnostic> iterator = resource.getErrors().iterator();
        int numberOfOrignalStyeDanglingFeature = 0;
        while (iterator.hasNext()) {
            Resource.Diagnostic diagnostic = iterator.next();
            if (diagnostic instanceof UnresolvedReferenceException) {
                EStructuralFeature feature = ((UnresolvedReferenceException) diagnostic).getFeature();
                if (originalStyleReferences.contains(feature)) {
                    iterator.remove();
                    numberOfOrignalStyeDanglingFeature++;
                }
            }
        }

        if (numberOfOrignalStyeFeatureUnset > 0) {
            StringBuilder message = new StringBuilder();
            message.append(MessageFormat.format(Messages.UnsetOriginalStyleFeatureMigrationParticipant_title, resource.getURI().toPlatformString(true)));
            message.append(MessageFormat.format(Messages.UnsetOriginalStyleFeatureMigrationParticipant_unsetFeatures, numberOfOrignalStyeFeatureUnset + numberOfOrignalStyeDanglingFeature));
            if (numberOfOrignalStyeDanglingFeature > 0) {
                message.append(MessageFormat.format(Messages.UnsetOriginalStyleFeatureMigrationParticipant_danglingFeatures, numberOfOrignalStyeDanglingFeature));
            }
            DiagramPlugin.getDefault().logInfo(message.toString());

            numberOfOrignalStyeFeatureUnset = 0;
        }

        super.postLoad(resource, loadedVersion);
    }
}
