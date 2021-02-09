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
package org.eclipse.sirius.business.api.query;

import java.sql.Date;
import java.text.MessageFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * A class aggregating the queries (read-only!) having a {@link Session} as a starting point.
 * 
 * @author lfasani
 */
public final class SessionQuery {
    private static final String ARROW = " -> "; //$NON-NLS-1$

    private static final String CR = "\n"; //$NON-NLS-1$

    private static final String TAB = "  "; //$NON-NLS-1$

    private static final String SPACE = " "; //$NON-NLS-1$

    private static final String STARS = "*** "; //$NON-NLS-1$

    private static final String SEPARATOR = " - "; //$NON-NLS-1$

    private Session session;

    /**
     * Default constructor.
     * 
     * @param session
     *            the session
     */
    public SessionQuery(Session session) {
        this.session = session;
    }

    /**
     * Returns general information related to the representations of this session.
     * <li>number of representations</li>
     * <li>number of loaded representations</li>
     * <li>number of opened representations</li>
     * <li>invalid representations</li>
     * <li>etc</li>
     * 
     * @return the formatted information
     */
    public String getSessionFormattedInformation() {
        StringBuilder informations = new StringBuilder();

        informations.append(STARS);
        informations.append(Messages.SessionQuery_Date).append(SPACE).append(Date.from(Instant.now())).append(CR).append(CR);

        addResourcesInformation(informations);

        addViewpointsInformation(informations);

        addRepresentationsInformation(informations);

        return informations.toString();
    }

    private void addRepresentationsInformation(StringBuilder informations) {
        // --------------------
        // Gather information
        Collection<DRepresentation> loadedReps = DialectManager.INSTANCE.getAllLoadedRepresentations(session);
        //@formatter:off
        @SuppressWarnings("unchecked")
        List<DRepresentationElement> repElements = loadedReps.stream()
            .flatMap(rep -> {
                try {
                    // Using aql allows to optimize performances
                    return ((Collection<DRepresentationElement>) session.getInterpreter().evaluate(rep, "aql:self.eAllContents(viewpoint::DRepresentationElement)")).stream(); //$NON-NLS-1$
                } catch (EvaluationException e) {
                }
                return null;
            })
            .collect(Collectors.toList());
        
        Set<DRepresentation> representationsWithoutTarget = repElements.stream()
            .filter(repElement -> repElement.getTarget() == null || repElement.getTarget().eResource() == null)
            .map(repElement -> new DRepresentationElementQuery(repElement).getParentRepresentation())
            .collect(Collectors.toSet());

        Collection<DRepresentationDescriptor> allRepDescs = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
        long nbDiagram = allRepDescs.stream().filter(repDesc -> repDesc.getDescription().getClass().getSimpleName().contains("Diagram")).count(); //$NON-NLS-1$
        long nbTables = allRepDescs.stream().filter(repDesc -> repDesc.getDescription().getClass().getSimpleName().contains("Table")).count(); //$NON-NLS-1$
        long nbTree = allRepDescs.stream().filter(repDesc -> repDesc.getDescription().getClass().getSimpleName().contains("Tree")).count(); //$NON-NLS-1$
        long nbSequence = allRepDescs.stream().filter(repDesc -> repDesc.getDescription().getClass().getSimpleName().contains("Sequence")).count(); //$NON-NLS-1$
        
        Collection<DRepresentationDescriptor> invalidReps = allRepDescs.stream()
            .filter(repDescriptor -> {
                boolean representationValid = new DRepresentationDescriptorQuery(repDescriptor).isRepresentationValid();
                return !representationValid;
            })
            .collect(Collectors.toList());
        //@formatter:on

        informations.append(CR).append(STARS);
        informations.append(Messages.SessionQuery_Representations).append(CR).append(CR);
        informations.append(Messages.SessionQuery_AllRepresentations).append(SPACE).append(allRepDescs.size()).append(CR);
        informations.append(TAB).append(Messages.SessionQuery_Diagram).append(SPACE).append(nbDiagram).append(CR);
        informations.append(TAB).append(Messages.SessionQuery_Table).append(SPACE).append(nbTables).append(CR);
        informations.append(TAB).append(Messages.SessionQuery_Tree).append(SPACE).append(nbTree).append(CR);
        informations.append(TAB).append(Messages.SessionQuery_Sequence).append(SPACE).append(nbSequence).append(CR);

        informations.append(CR).append(Messages.SessionQuery_LoadedReps).append(TAB).append(loadedReps.size()).append(CR);
        informations.append(Messages.SessionQuery_NbRepElements).append(TAB).append(repElements.size()).append(CR);

        informations.append(CR).append(MessageFormat.format(Messages.SessionQuery_LoadedBrokenReps, representationsWithoutTarget.size())).append(CR);
        representationsWithoutTarget.stream().map(rep -> new DRepresentationQuery(rep).getRepresentationDescriptor()).forEach(repDescriptor -> {
            informations.append(TAB);
            addRepresentationDescriptorSimpleInfo(informations, repDescriptor);
            informations.append(CR);
        });

        informations.append(CR).append(Messages.SessionQuery_InvalidReps).append(TAB).append(invalidReps.size()).append(CR);
        invalidReps.stream().forEach(repDescriptor -> {
            informations.append(TAB);
            addRepresentationDescriptorSimpleInfo(informations, repDescriptor);
            informations.append(CR);
        });

        informations.append(CR).append(Messages.SessionQuery_RepresentationDescriptorDetails).append(TAB).append(allRepDescs.size()).append(CR);
        allRepDescs.stream().forEach(repDescriptor -> {
            informations.append(TAB);
            addRepresentationDescriptorExtendedInfo(informations, repDescriptor);
            informations.append(CR);
        });
    }

    private void addResourcesInformation(StringBuilder informations) {
        informations.append(STARS);
        informations.append(Messages.SessionQuery_Resources).append(CR);
        informations.append(CR).append(MessageFormat.format(Messages.SessionQuery_SessionResources, session.getAllSessionResources().size())).append(CR);
        session.getAllSessionResources().forEach(res -> {
            informations.append(TAB).append(getResourceDescription(res)).append(CR);
        });
        informations.append(CR).append(MessageFormat.format(Messages.SessionQuery_SemanticResources, session.getSemanticResources().size())).append(CR);
        session.getSemanticResources().forEach(res -> {
            informations.append(TAB).append(getResourceDescription(res)).append(CR);
        });
        List<Resource> controlledResources = ((DAnalysisSessionEObject) session).getControlledResources();
        if (controlledResources.size() > 0) {
            informations.append(CR).append(MessageFormat.format(Messages.SessionQuery_ControlledResources, controlledResources.size())).append(CR);
            controlledResources.forEach(res -> {
                informations.append(TAB).append(getResourceDescription(res)).append(CR);
            });
        }
    }

    private String getViewpointDescription(Viewpoint viewpoint) {
        String vpDescription = null;
        Resource resource = viewpoint.eResource();
        if (resource == null) {
            // toString allows to get the eProxyURI
            vpDescription = viewpoint.toString();
        } else {
            vpDescription = viewpoint.getName() + SPACE + Messages.SessionQuery_LoadedFromResource + SPACE + resource.getURI().toString();
        }
        return vpDescription;
    }

    private void addRepresentationDescriptorExtendedInfo(StringBuilder informations, DRepresentationDescriptor repDescriptor) {
        addRepresentationDescriptorSimpleInfo(informations, repDescriptor);
        informations.append(SEPARATOR);
        informations.append(EcoreUtil.getURI(repDescriptor.getDescription()));
        informations.append(SEPARATOR);
        informations.append(repDescriptor.getRepPath().toString());
        informations.append(ARROW);
        informations.append(new EObjectQuery(repDescriptor.getTarget()).getGenericDecription());
    }

    /**
     * Add the representation descriptor formatted information in the StringBuilder.
     * 
     * @param informations
     *            the StringBuilder
     * @param repDescriptor
     *            the representation descriptor
     */
    public void addRepresentationDescriptorSimpleInfo(StringBuilder informations, DRepresentationDescriptor repDescriptor) {
        informations.append(repDescriptor.getName());
        informations.append(SEPARATOR);
        informations.append(Messages.SessionQuery_RepUid);
        informations.append(SPACE);
        informations.append(repDescriptor.getUid());
    }

    private void addViewpointsInformation(StringBuilder informations) {
        //@formatter:off
        Collection<DView> selectedViews = session.getSelectedViews();
        Set<String> selectedViewpointDescriptions = selectedViews.stream()
                .map(view -> view.getViewpoint())
                .map(this::getViewpointDescription)
                .collect(Collectors.toSet());
            Collection<DView> unselectedViews  = new ArrayList<>(session.getOwnedViews());
            unselectedViews.removeAll(selectedViews);
            Set<String> unselectedViewpointDescriptions = unselectedViews.stream()
                    .map(view -> view.getViewpoint())
                    .map(this::getViewpointDescription)
                    .collect(Collectors.toSet());
        //@formatter:on

        informations.append(CR).append(STARS);
        informations.append(Messages.SessionQuery_Viewpoints).append(CR).append(CR);
        informations.append(MessageFormat.format(Messages.SessionQuery_ActiveViewpoints, selectedViewpointDescriptions.size())).append(CR);
        selectedViewpointDescriptions.stream().forEach(vpURI -> {
            informations.append(TAB).append(vpURI).append(CR);
        });
        informations.append(CR);
        informations.append(MessageFormat.format(Messages.SessionQuery_InactiveViewpoints, unselectedViewpointDescriptions.size())).append(CR);
        unselectedViewpointDescriptions.stream().forEach(vpURI -> {
            informations.append(TAB).append(vpURI).append(CR);
        });
    }

    private String getResourceDescription(Resource resource) {
        StringBuilder sb = new StringBuilder();
        URI uri = resource.getURI();
        // Convert the iterator to Spliterator
        Spliterator<EObject> spliterator = Spliterators.spliteratorUnknownSize(EcoreUtil.getAllProperContents(resource, false), 0);
        // Get a sequential Stream from spliterator
        long nbElements = StreamSupport.stream(spliterator, false).count();

        long fileSize = 0;
        Option<IResource> correspondingResource = new URIQuery(uri).getCorrespondingResource();
        if (correspondingResource.some() && correspondingResource.get() instanceof IFile) {
            // fileSize = ((IFile)correspondingResource.get()).getF

            java.net.URI locationURI = ((IFile) correspondingResource.get()).getLocationURI();
            try {
                fileSize = EFS.getStore(locationURI).fetchInfo().getLength();
            } catch (CoreException e) {
            }
        }

        sb.append(uri.toString()).append(SEPARATOR).append(nbElements).append(SPACE).append(Messages.SessionQuery_Elements).append(SEPARATOR).append(fileSize).append(SPACE)
                .append(Messages.SessionQuery_FileSize);
        return sb.toString();
    }

}
