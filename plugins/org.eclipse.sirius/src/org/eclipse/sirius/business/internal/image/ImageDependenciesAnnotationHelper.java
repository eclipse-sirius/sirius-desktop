/*******************************************************************************
 * Copyright (c) 2022, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.image.ImageManager;
import org.eclipse.sirius.business.api.image.RichTextAttributeRegistry;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.internal.query.SiriusSessionQuery;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.description.DAnnotationEntry;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;

/**
 * This helper provides utility methods to manage the "Image Dependencies" DAnnotation entry.
 * 
 * @author gplouhinec
 * @author lfasani
 *
 */
public final class ImageDependenciesAnnotationHelper {

    /**
     * Name of the source of the image dependencies annotation.
     */
    public static final String IMAGES_DEPENDENCIES_ANNOTATION_SOURCE_NAME = "ImagesDependencies"; //$NON-NLS-1$

    /**
     * The string that separates the key that can be either "RichText" or the id of the representation from one side and
     * the project in dependency on the other side.
     */
    public static final String SEPARATOR = ":"; //$NON-NLS-1$

    /**
     * The name of the WorkspaceImage class.
     */
    public static final String WORKSPACE_IMAGE_CLASS_NAME = "WorkspaceImage"; //$NON-NLS-1$

    /**
     * The name of the workspacePath feature for WorkspaceImage instances.
     */
    public static final String WORKSPACE_PATH_FEATURE_NAME = "workspacePath"; //$NON-NLS-1$

    /**
     * The name of the ownedStyle feature for many DRepresentationElements (DNode, DNodeContainer, ...).
     */
    public static final String OWNED_STYLE_FEATURE_NAME = "ownedStyle"; //$NON-NLS-1$

    private static final String RICH_TEXT_KEY = "RichText"; //$NON-NLS-1$

    private DAnalysisSessionImpl session;

    private String currentProjectName;

    /**
     * Default constructor.
     * 
     * @param session
     */
    public ImageDependenciesAnnotationHelper(DAnalysisSessionImpl session) {
        this.session = session;
    }

    /**
     * Find all image dependencies in diagrams and in rich text and return the list of project in dependencies.
     */
    public static Set<String> getAllImageProjectsDirectDependencies(DAnalysis dAnalysis) {
        //@formatter:off
        Set<String> projectDependencies = ImageDependenciesAnnotationHelper.getImagesDependenciesAnnotationEntry(dAnalysis)
                .map(DAnnotationEntry::getDetails)
                .stream()
                .flatMap(List::stream)
                .map(dep -> {
                    return dep.split(SEPARATOR)[1];
                })
                .collect(Collectors.toCollection(
                        TreeSet::new));
        //@formatter:on

        return projectDependencies;
    }

    /**
     * Get the "Images Dependencies" DAnnotationEntry or create it if not found.<br/>
     * It does not change the session model.
     * 
     * @param createIfNotPresent
     * 
     * @return the "Images Dependencies" DAnnotationEntry
     */
    private DAnnotationEntry getOrCreateImagesDependenciesAnnotationEntry() {
        DAnnotationEntry dAnnotationEntry = null;
        final Optional<DAnalysis> sharedMainDAnalysis = session.getSharedMainDAnalysis();
        if (sharedMainDAnalysis.isPresent()) {
            dAnnotationEntry = getImagesDependenciesAnnotationEntry(sharedMainDAnalysis.get()).orElseGet(() -> {
                DAnnotationEntry annotationEntry = createImagesDependenciesDAnnotationEntry();
                return annotationEntry;
            });
        }
        return dAnnotationEntry;
    }

    private boolean hasImageDependenciesAnnotationEntry() {
        return session.getSharedMainDAnalysis().flatMap(dAnalysis -> getImagesDependenciesAnnotationEntry(dAnalysis)).isPresent();
    }

    /**
     * Get the "Images Dependencies" DAnnotationEntry.
     * 
     * @return the "Images Dependencies" DAnnotationEntry in Optional container if it exists; an empty Optional instance
     *         otherwise
     */
    public static Optional<DAnnotationEntry> getImagesDependenciesAnnotationEntry(DAnalysis dAnalysis) {
        EList<DAnnotationEntry> sessionAnnotations = dAnalysis.getEAnnotations();
        for (DAnnotationEntry annotationEntry : sessionAnnotations) {
            if (annotationEntry.getSource().equals(IMAGES_DEPENDENCIES_ANNOTATION_SOURCE_NAME)) {
                return Optional.of(annotationEntry);
            }
        }
        return Optional.empty();
    }

    /**
     * Remove the given {@link DAnnotationEntry} from the main {@link DAnalysis}.
     */
    private void removeMainDAnalysisDAnnotationEntry() {
        if (hasImageDependenciesAnnotationEntry()) {
            Optional<DAnalysis> sharedMainDAnalysis = session.getSharedMainDAnalysis();
            if (sharedMainDAnalysis.isPresent()) {
                EList<DAnnotationEntry> sessionAnnotations = sharedMainDAnalysis.get().getEAnnotations();
                sessionAnnotations.remove(getOrCreateImagesDependenciesAnnotationEntry());
            }
        }
    }

    /**
     * Used to retrieve the corresponding "details" item from the given {representation, image project} entry if it
     * exists. Useful to know if we need to create another "details" item, or delete it.
     * 
     * @param annotationEntry
     *            the DAnnotationEntry from which we search for the corresponding dependency
     * @param representationToImageDepEntry
     *            the dependency we are searching for
     * @return the "details" item found if it exists; an empty string otherwise
     */
    private String getDetailsForDiagramToImageDependencyEntry(DAnnotationEntry annotationEntry, String expectedKey, String expectedProjectName) {
        if (IMAGES_DEPENDENCIES_ANNOTATION_SOURCE_NAME.equals(annotationEntry.getSource())) {
            for (String details : annotationEntry.getDetails()) {
                String[] detailsSplit = details.split(SEPARATOR);
                if (detailsSplit.length == 2) {
                    String key = detailsSplit[0].trim();
                    String imageProject = detailsSplit[1];
                    if (key.equals(expectedKey) && imageProject.equals(expectedProjectName)) {
                        return details;
                    }
                }
            }
        }
        return StringUtil.EMPTY_STRING;
    }

    /**
     * Creates and initializes a DAnnotationEntry with the "Images Dependencies" source.
     * 
     * @return the newly created DAnnotationEntry
     */
    private DAnnotationEntry createImagesDependenciesDAnnotationEntry() {
        DAnnotationEntry annotationEntry = DescriptionFactory.eINSTANCE.createDAnnotationEntry();
        annotationEntry.setSource(IMAGES_DEPENDENCIES_ANNOTATION_SOURCE_NAME);
        return annotationEntry;
    }

    /**
     * Creates a common details for the "Images Dependencies" DAnnotationEntry, with the representation identifier as
     * key and the project name as value.
     * 
     * @param annotationEntry
     *            the DAnnotationEntry where the new "details" item will be created
     * 
     * @param key
     *            the representation identifier
     * @param value
     *            the name of the image project
     * @return the new "details" item
     */
    private String createDetailsForDAnnotationEntry(DAnnotationEntry annotationEntry, String key, String projectName) {
        String detailsItem = key + SEPARATOR + projectName;
        annotationEntry.getDetails().add(detailsItem);
        return detailsItem;

    }

    /**
     * Find all image dependencies in diagrams and in rich text and return the list of project in dependencies.
     */
    public void updateAllImageProjectsDependencies() {
        // Do not remove DAnnotationEntry until it is necessary to avoid potential differences with Diff/merge
        if (hasImageDependenciesAnnotationEntry()) {
            getOrCreateImagesDependenciesAnnotationEntry().getDetails().clear();
        }

        Map<DRepresentation, List<String>> diagramToImageProjectDependencies = new HashMap<>();
        findImagePathInRichTextDescription(diagramToImageProjectDependencies);
        findImagePathInWorkspaceImage(diagramToImageProjectDependencies);

        addImageDependencyAnnotationDetails(diagramToImageProjectDependencies);
    }

    private void findImagePathInRichTextDescription(Map<DRepresentation, List<String>> diagramToNewImageDependency) {

        for (Resource resource : session.getSemanticResources()) {
            EcoreUtil.getAllProperContents(resource, true).forEachRemaining(object -> {
                if (object instanceof EObject) {
                    findImagePathInRichText((EObject) object, diagramToNewImageDependency);
                }
            });
        }

        for (DRepresentationDescriptor repDescriptor : DialectManager.INSTANCE.getAllRepresentationDescriptors(session)) {
            findImagePathInRichText(repDescriptor, diagramToNewImageDependency);
        }
    }

    private void findImagePathInRichText(EObject eObject, Map<DRepresentation, List<String>> diagramToNewImageDependency) {
        Set<EAttribute> richTextAttributes = RichTextAttributeRegistry.INSTANCE.getEAttributes();
        Pattern pattern = Pattern.compile(ImageManager.HTML_IMAGE_PATH_PATTERN);

        List<EAttribute> attributesToCheck = eObject.eClass().getEAllAttributes().stream().filter(richTextAttributes::contains).collect(Collectors.toList());
        for (EAttribute eAttribute : attributesToCheck) {
            Object stringObj = eObject.eGet(eAttribute);
            if (stringObj instanceof String) {
                String htmlText = (String) stringObj;
                Matcher matcher = pattern.matcher(htmlText);
                while (matcher.find()) {
                    String imagePath = matcher.group(1);
                    this.getProjectFromImagePath(imagePath).ifPresent(projectName -> {
                        addProjectDependency(diagramToNewImageDependency, null, projectName);
                    });
                }
            }
        }
    }

    private void addProjectDependency(Map<DRepresentation, List<String>> diagramToNewImageDependency, DRepresentation key, String projectName) {
        List<String> projectNames = Optional.ofNullable(diagramToNewImageDependency.get(key)).orElseGet(ArrayList::new);
        if (!projectNames.contains(projectName)) {
            projectNames.add(projectName);
        }
        if (!projectNames.isEmpty()) {
            diagramToNewImageDependency.put(key, projectNames);
        }
    }

    private void findImagePathInWorkspaceImage(Map<DRepresentation, List<String>> diagramToNewImageDependency) {

        for (DRepresentation representation : DialectManager.INSTANCE.getAllRepresentations(session)) {
            Iterable<EObject> it = () -> representation.eAllContents();
            //@formatter:off
            StreamSupport.stream(it.spliterator(), false)
                .filter(object -> object.eClass().getName().equals("WorkspaceImage")) //$NON-NLS-1$
                .forEach(object -> {
                    EStructuralFeature feature = object.eClass().getEStructuralFeature("workspacePath"); //$NON-NLS-1$
                    Object imagePath = object.eGet(feature);
                    if (imagePath instanceof String) {
                        this.getProjectFromImagePath((String) imagePath).ifPresent(projectName -> {
                            addProjectDependency(diagramToNewImageDependency, representation, projectName);
                        });
                    }
                });
            //@formatter:on
        }
    }

    /**
     * Fill details corresponding to projects dependencies.<br/>
     * Note that :
     * <li>dependency to current project is ignored.</li>
     * <li>ImageDependency DAnnotation is removed if empty</li>
     */
    public void addImageDependencyAnnotationDetails(Map<DRepresentation, List<String>> diagramToNewImageDependency) {

        DAnnotationEntry imageDependenciesEntry = getOrCreateImagesDependenciesAnnotationEntry();

        diagramToNewImageDependency.entrySet().stream().forEach(entry -> {
            DRepresentation dRepresentation = entry.getKey();
            List<String> projectNames = entry.getValue();
            for (String projectName : projectNames) {
                if (!getCurrentProjectName().equals(projectName)) {
                    String dRepresentationString = dRepresentation != null ? dRepresentation.getUid() : RICH_TEXT_KEY;
                    String optDetailsEntry = this.getDetailsForDiagramToImageDependencyEntry(imageDependenciesEntry, dRepresentationString, projectName);
                    if (optDetailsEntry.isEmpty()) {
                        this.createDetailsForDAnnotationEntry(imageDependenciesEntry, dRepresentationString, projectName);
                    }
                }
            }
        });

        if (imageDependenciesEntry.getDetails().size() == 0) {
            removeMainDAnalysisDAnnotationEntry();
        } else if (!hasImageDependenciesAnnotationEntry()) {
            final Optional<DAnalysis> sharedMainDAnalysis = session.getSharedMainDAnalysis();
            if (sharedMainDAnalysis.isPresent()) {
                sharedMainDAnalysis.get().getEAnnotations().add(imageDependenciesEntry);
            }
        }
    }

    /**
     * Remove details corresponding to unused projects in representation workspace image.
     */
    public void removeImageDependencyAnnotationDetails(Map<DRepresentation, List<String>> diagramToOldImageDependency) {
        if (!hasImageDependenciesAnnotationEntry()) {
            return;
        }

        diagramToOldImageDependency.entrySet().stream().forEach(entry -> {
            DRepresentation dRepresentation = entry.getKey();
            List<String> projectNames = entry.getValue();

            List<String> notAlreadyExistingImageDependencyProjects = new ArrayList<>(projectNames);
            for (DRepresentationElement element : dRepresentation.getRepresentationElements()) {
                if (this.isWorkspaceImageInstance(element.getStyle())) {
                    EStructuralFeature eStructuralFeature = element.getStyle().eClass().getEStructuralFeature(WORKSPACE_PATH_FEATURE_NAME);
                    if (eStructuralFeature != null) {
                        String imagePath = (String) element.getStyle().eGet(eStructuralFeature);
                        projectNames.stream().filter(projectName -> imagePath.startsWith(projectName)).findAny().ifPresent(projectName -> {
                            notAlreadyExistingImageDependencyProjects.remove(projectName);
                        });
                        if (notAlreadyExistingImageDependencyProjects.isEmpty()) {
                            break;
                        }
                    }
                }
            }
            DAnnotationEntry imageDependenciesEntry = getOrCreateImagesDependenciesAnnotationEntry();
            for (String projectName : notAlreadyExistingImageDependencyProjects) {
                String detailsEntry = this.getDetailsForDiagramToImageDependencyEntry(imageDependenciesEntry, dRepresentation.getUid(), projectName);
                if (!detailsEntry.isEmpty()) {
                    imageDependenciesEntry.getDetails().remove(detailsEntry);
                    if (imageDependenciesEntry.getDetails().isEmpty()) {
                        removeMainDAnalysisDAnnotationEntry();
                    }
                }
            }
        });
    }

    /**
     * Used to determine if an EObject is an instance of WorkspaceImage.
     * 
     * @param object
     *            the object we need to check
     * @return true if the object is a WorkspaceImage instance: false otherwise
     */
    public boolean isWorkspaceImageInstance(Object object) {
        return object instanceof EObject && ((EObject) object).eClass().getName().equals(ImageDependenciesAnnotationHelper.WORKSPACE_IMAGE_CLASS_NAME);
    }

    /**
     * Used to get the project name from the workspacePath of a WorkspaceImage.
     * 
     * @param imagePath
     *            the path of the image
     * @return the name of the project containing the image
     */
    public Optional<String> getProjectFromImagePath(String imagePath) {
        boolean exists = FileProvider.getDefault().exists(new Path(imagePath), session);
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
        return Optional.ofNullable(URI.decode(projectName));
    }

    /**
     * Get the project Name.
     */
    public String getCurrentProjectName() {
        if (currentProjectName == null) {
            this.currentProjectName = new SiriusSessionQuery(session).getSharedProjectName();
        }
        return currentProjectName;
    }
}
