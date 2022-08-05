/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.query;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DRepresentationDescriptorQuery;
import org.eclipse.sirius.business.api.query.DRepresentationElementQuery;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.query.SiriusProjectDependencies;
import org.eclipse.sirius.business.api.query.SiriusProjectDependencyQuery;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.image.ImageDependenciesAnnotationHelper;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * A class aggregating the queries (read-only!) having a {@link Session} as a starting point.
 * 
 * @author lfasani
 */
public final class SessionDetailsReport {
    private static final String ARROW = " -> "; //$NON-NLS-1$

    private static final String CR = System.lineSeparator();

    private static final String TAB = "  "; //$NON-NLS-1$

    private static final String SPACE = " "; //$NON-NLS-1$

    private static final String STARS = "*** "; //$NON-NLS-1$

    private static final String SEPARATOR = " - "; //$NON-NLS-1$

    private static final String BRACKET_IN = "["; //$NON-NLS-1$

    private static final String BRACKET_OUT = "]"; //$NON-NLS-1$

    private static final String COLON = ":"; //$NON-NLS-1$

    private static final String NOT_ANALYSED = "(Not analysed)"; //$NON-NLS-1$

    private static final String TRANSITIVE_DEPENDENCY = "(Transitive dependency)"; //$NON-NLS-1$

    private Session session;

    private IFile resourceAird;

    /**
     * Default constructor.
     * 
     * @param session
     *            the session
     */
    public SessionDetailsReport(IFile resourceAird) {
        this.resourceAird = resourceAird;
        URI airdURI = URI.createPlatformResourceURI(resourceAird.getFullPath().toString(), true);
        session = SessionManager.INSTANCE.getExistingSession(airdURI);
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
        if (session == null) {
            informations.append(Messages.SessionQuery_sessionNotOpened).append(CR);
        }

        informations.append(STARS);
        informations.append(Messages.SessionQuery_Date).append(SPACE).append(Date.from(Instant.now())).append(CR).append(CR);

        addDependenciesInformation(informations);

        if (session != null) {
            addResourcesInformation(informations);

            addViewpointsInformation(informations);

            addRepresentationsInformation(informations);
        }

        return informations.toString();
    }

    private void addRepresentationsInformation(StringBuilder informations) {
        // --------------------
        // Gather information
        Collection<DRepresentation> loadedReps = DialectManager.INSTANCE.getAllLoadedRepresentations(session);
        //@formatter:off
        List<DRepresentationElement> repElements = loadedReps.stream()
            .flatMap(rep -> rep.getRepresentationElements().stream())
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
        Set<DRepresentation> representationsWithoutTarget = repElements.stream()
            .filter(repElement -> {
                EObject target = repElement.getTarget();
                return target == null || target.eResource() == null;
            })
            .map(repElement -> new DRepresentationElementQuery(repElement).getParentRepresentation())
            .filter(Objects::nonNull)
            .collect(Collectors.toCollection(LinkedHashSet::new));
        //@formatter:on

        Collection<DRepresentationDescriptor> allRepDescs = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
        List<DRepresentationDescriptor> invalidReps = new ArrayList<DRepresentationDescriptor>();
        Map<DRepresentationDescriptor, String> repDescToTags = new LinkedHashMap<DRepresentationDescriptor, String>();

        processGeneralInformation(informations, allRepDescs, invalidReps, repDescToTags);

        informations.append(CR).append(Messages.SessionQuery_LoadedReps).append(TAB).append(loadedReps.size()).append(CR);
        informations.append(Messages.SessionQuery_NbRepElements).append(TAB).append(repElements.size()).append(CR);

        informations.append(CR).append(MessageFormat.format(Messages.SessionQuery_LoadedBrokenReps, representationsWithoutTarget.size())).append(CR);
        representationsWithoutTarget.stream().map(rep -> new DRepresentationQuery(rep).getRepresentationDescriptor()).forEach(repDescriptor -> {
            informations.append(TAB);
            addRepresentationDescriptorSimpleInfo(informations, repDescriptor);
            informations.append(CR);
        });
        informations.append(Messages.SessionQuery_LoadedBrokenRepsInfo).append(CR);

        informations.append(CR).append(MessageFormat.format(Messages.SessionQuery_InvalidReps, invalidReps.size())).append(CR);
        invalidReps.stream().forEach(repDescriptor -> {
            informations.append(TAB);
            addRepresentationDescriptorSimpleInfo(informations, repDescriptor);
            informations.append(CR);
        });
        informations.append(Messages.SessionQuery_InvalidRepsInfo).append(CR);

        informations.append(CR).append(MessageFormat.format(Messages.SessionQuery_RepresentationDescriptorDetails, allRepDescs.size())).append(CR);
        allRepDescs.stream().forEach(repDescriptor -> {
            informations.append(TAB);
            addRepresentationDescriptorExtendedInfo(informations, repDescriptor, repDescToTags);
            informations.append(CR);
        });
    }

    private void processGeneralInformation(StringBuilder informations, Collection<DRepresentationDescriptor> allRepDescs, List<DRepresentationDescriptor> invalidReps,
            Map<DRepresentationDescriptor, String> repDescToTags) {
        long nbDiagram = 0;
        long nbEditionTables = 0;
        long nbCrossTables = 0;
        long nbTree = 0;
        long nbSequence = 0;
        for (DRepresentationDescriptor dRepresentationDescriptor : allRepDescs) {
            StringBuilder tags = new StringBuilder();
            if (!new DRepresentationDescriptorQuery(dRepresentationDescriptor).isRepresentationValid()) {
                invalidReps.add(dRepresentationDescriptor);
                tags.append(BRACKET_IN + Messages.SessionQuery_TagInvalid + BRACKET_OUT);
            }
            if (dRepresentationDescriptor.isLoadedRepresentation()) {
                tags.append(BRACKET_IN + Messages.SessionQuery_TagLoaded + BRACKET_OUT);
            }
            String simpleNameForDescription = dRepresentationDescriptor.getDescription().getClass().getSimpleName();
            simpleNameForDescription = simpleNameForDescription.replace("Description", ""); //$NON-NLS-1$//$NON-NLS-2$
            if (simpleNameForDescription.contains("Diagram")) { //$NON-NLS-1$
                nbDiagram++;
                tags.append(BRACKET_IN + Messages.SessionQuery_Diagram + BRACKET_OUT);
            } else if (simpleNameForDescription.contains("EditionTable")) { //$NON-NLS-1$
                nbEditionTables++;
                tags.append(BRACKET_IN + Messages.SessionQuery_EditionTable + BRACKET_OUT);
            } else if (simpleNameForDescription.contains("CrossTable")) { //$NON-NLS-1$
                nbCrossTables++;
                tags.append(BRACKET_IN + Messages.SessionQuery_CrossTable + BRACKET_OUT);
            } else if (simpleNameForDescription.contains("Tree")) { //$NON-NLS-1$
                nbTree++;
                tags.append(BRACKET_IN + Messages.SessionQuery_Tree + BRACKET_OUT);
            } else if (simpleNameForDescription.contains("Sequence")) { //$NON-NLS-1$
                nbSequence++;
                tags.append(BRACKET_IN + Messages.SessionQuery_Sequence + BRACKET_OUT);
            } else {
                tags.append(BRACKET_IN + simpleNameForDescription + BRACKET_OUT);
            }

            repDescToTags.put(dRepresentationDescriptor, tags.toString());
        }

        informations.append(CR).append(STARS);
        informations.append(Messages.SessionQuery_Representations).append(CR).append(CR);
        informations.append(Messages.SessionQuery_AllRepresentations).append(SPACE).append(allRepDescs.size()).append(CR);
        informations.append(TAB).append(Messages.SessionQuery_Diagram).append(COLON).append(SPACE).append(nbDiagram).append(CR);
        informations.append(TAB).append(Messages.SessionQuery_Sequence).append(COLON).append(SPACE).append(nbSequence).append(CR);
        informations.append(TAB).append(Messages.SessionQuery_EditionTable).append(COLON).append(SPACE).append(nbEditionTables).append(CR);
        informations.append(TAB).append(Messages.SessionQuery_CrossTable).append(COLON).append(SPACE).append(nbCrossTables).append(CR);
        informations.append(TAB).append(Messages.SessionQuery_Tree).append(COLON).append(SPACE).append(nbTree).append(CR);
    }

    private void addDependenciesInformation(StringBuilder informations) {
        informations.append(STARS);
        informations.append(Messages.SessionQuery_Dependencies).append(CR);
        SiriusProjectDependencies directDependencies = new SiriusProjectDependencyQuery(resourceAird.getProject()).getAllDependencies();
        addGeneralProjectsDependencies(informations, directDependencies);
        addImageProjectsDependencies(informations, directDependencies);

        informations.append(CR);
    }

    private void addResourcesInformation(StringBuilder informations) {
        informations.append(STARS);
        informations.append(Messages.SessionQuery_Resources).append(CR);
        informations.append(CR).append(MessageFormat.format(Messages.SessionQuery_SessionResources, session.getAllSessionResources().size())).append(CR);
        session.getAllSessionResources().forEach(res -> {
            informations.append(TAB);
            addResourceDescription(informations, res);
            informations.append(CR);
        });
        informations.append(CR).append(MessageFormat.format(Messages.SessionQuery_SemanticResources, session.getSemanticResources().size())).append(CR);
        session.getSemanticResources().forEach(res -> {
            informations.append(TAB);
            addResourceDescription(informations, res);
            informations.append(CR);
        });
        List<Resource> controlledResources = ((DAnalysisSessionEObject) session).getControlledResources();
        if (controlledResources.size() > 0) {
            informations.append(CR).append(MessageFormat.format(Messages.SessionQuery_ControlledResources, controlledResources.size())).append(CR);
            controlledResources.forEach(res -> {
                informations.append(TAB);
                addResourceDescription(informations, res);
                informations.append(CR);
            });
        }
    }

    private String getViewpointDescription(Viewpoint viewpoint) {
        String vpDescription = null;
        if (viewpoint != null) {
            Resource resource = viewpoint.eResource();
            if (resource == null) {
                // toString allows to get the eProxyURI
                vpDescription = viewpoint.toString();
            } else {
                vpDescription = viewpoint.getName() + SPACE + Messages.SessionQuery_LoadedFromResource + SPACE + resource.getURI().toString();
            }
        }

        return vpDescription;
    }

    private void addRepresentationDescriptorExtendedInfo(StringBuilder informations, DRepresentationDescriptor repDescriptor, Map<DRepresentationDescriptor, String> repDescToTags) {
        addRepresentationDescriptorSimpleInfo(informations, repDescriptor);
        informations.append(SEPARATOR);
        RepresentationDescription description = repDescriptor.getDescription();
        if (description != null) {
            informations.append("description: "); //$NON-NLS-1$
            informations.append(description.getName());
            informations.append(SEPARATOR);

            Viewpoint vp = (Viewpoint) description.eContainer();
            if (vp != null) {
                informations.append("viewpoint: "); //$NON-NLS-1$
                informations.append(vp.getName());
            } else {
                informations.append("viewpoint: null"); //$NON-NLS-1$
            }
        } else {
            informations.append("description: null"); //$NON-NLS-1$
        }
        informations.append(SEPARATOR);
        ResourceDescriptor repPath = repDescriptor.getRepPath();
        if (repPath != null) {
            informations.append("repPath: "); //$NON-NLS-1$
            informations.append(repPath.toString());
        } else {
            informations.append("repPath: null"); //$NON-NLS-1$
        }
        informations.append(ARROW);
        EObject target = repDescriptor.getTarget();
        if (target != null) {
            informations.append(new EObjectQuery(target).getGenericDecription());
        } else {
            informations.append("null"); //$NON-NLS-1$
        }
        informations.append(TAB);
        informations.append(repDescToTags.get(repDescriptor));
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
        informations.append("uid: "); //$NON-NLS-1$
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

    /**
     * Add resource information to the passed StringBuilder.
     * 
     * @param informations
     *            the StringBuilder
     */
    private void addResourceDescription(StringBuilder informations, Resource resource) {
        URI uri = resource.getURI();
        // Convert the iterator to Spliterator
        Spliterator<EObject> spliterator = Spliterators.spliteratorUnknownSize(EcoreUtil.getAllProperContents(resource, false), 0);
        // Get a sequential Stream from spliterator
        long nbElements = StreamSupport.stream(spliterator, false).count();

        long fileSize = 0;
        Option<IResource> correspondingResource = new URIQuery(uri).getCorrespondingResource();
        if (correspondingResource.some() && correspondingResource.get() instanceof IFile) {
            java.net.URI locationURI = ((IFile) correspondingResource.get()).getLocationURI();
            try {
                fileSize = EFS.getStore(locationURI).fetchInfo().getLength();
            } catch (CoreException e) {
            }
        }

        informations.append(uri.toString()).append(SEPARATOR).append(nbElements).append(SPACE).append(Messages.SessionQuery_Elements).append(SEPARATOR).append(fileSize).append(SPACE)
                .append(Messages.SessionQuery_FileSize);
    }

    private void addGeneralProjectsDependencies(StringBuilder informations, SiriusProjectDependencies dependencies) {
        Set<String> directDependencyProjectNames = new TreeSet<>(dependencies.getGeneralProjectDirectDependencies());
        Set<String> transitiveDependencyProjectNames = new TreeSet<>(dependencies.getGeneralProjectTransitiveDependencies());
        Set<String> notAnalysedDependencyProjectNames = new TreeSet<>(dependencies.getNotAnalysedGeneralProjectDependencies());
        int nbProjects = directDependencyProjectNames.size() + transitiveDependencyProjectNames.size() + notAnalysedDependencyProjectNames.size();

        informations.append(CR).append(MessageFormat.format(Messages.SessionQuery_GeneralProjectsDependencies, nbProjects)).append(CR);
        directDependencyProjectNames.forEach(project -> {
            informations.append(TAB);
            informations.append(project);
            informations.append(CR);
        });

        transitiveDependencyProjectNames.forEach(project -> {
            informations.append(TAB);
            informations.append(TRANSITIVE_DEPENDENCY);
            informations.append(project);
            informations.append(CR);
        });

        notAnalysedDependencyProjectNames.forEach(project -> {
            informations.append(TAB);
            informations.append(NOT_ANALYSED);
            informations.append(project);
            informations.append(CR);
        });
    }

    private void addImageProjectsDependencies(StringBuilder informations, SiriusProjectDependencies dependencies) {
        Set<String> imageProjectsDirectDependencies = new TreeSet<>(dependencies.getImageProjectsDirectDependencies());
        if (session != null) {
            // if the session is opened we take the result directly from the DAnnotation instead of serialized
            // dependencies because it may have been modified without being saved.
            // @formatter:off
            Set<String> imageProjectsDirectDependenciesSet = session.getAllSessionResources().stream()
                    .flatMap(res -> res.getContents().stream())
                    .filter(DAnalysis.class::isInstance)
                    .map(DAnalysis.class::cast)
                    .flatMap(dAnalysis -> ImageDependenciesAnnotationHelper.getAllImageProjectsDirectDependencies(dAnalysis).stream())
                    .filter(projectName -> !projectName.equals(resourceAird.getProject().getName()))
                    .collect(Collectors.toSet());
            // @formatter:on
            imageProjectsDirectDependencies = new TreeSet<>(imageProjectsDirectDependenciesSet);
        }

        informations.append(CR).append(MessageFormat.format(Messages.SessionQuery_ImageProjectsDependencies, imageProjectsDirectDependencies.size())).append(CR);
        imageProjectsDirectDependencies.forEach(project -> {
            informations.append(TAB);
            informations.append(project);
            informations.append(CR);
        });
    }

    /**
     * Used to get the project name from the workspacePath of a WorkspaceImage.
     * 
     * @param imagePath
     *            the path of the image
     * @return the name of the project containing the image
     */
    public static Optional<String> getProjectFromImagePath(String imagePath) {
        boolean exists = FileProvider.getDefault().exists(new Path(imagePath));
        String projectName = null;
        if (exists) {
            URI uri = URI.createURI(imagePath);
            String cdoPrefix = URIQuery.CDO_URI_SCHEME + ":/"; //$NON-NLS-1$
            if ((uri.scheme() == null || imagePath.startsWith(cdoPrefix)) && !imagePath.startsWith("/")) { //$NON-NLS-1$
                String path = imagePath.startsWith(cdoPrefix) ? imagePath.substring(cdoPrefix.length()) : imagePath;

                String[] split = path.split("/"); //$NON-NLS-1$
                if (split.length > 0) {
                    projectName = split[0];
                }
            }
        }
        return Optional.ofNullable(projectName);
    }
}
