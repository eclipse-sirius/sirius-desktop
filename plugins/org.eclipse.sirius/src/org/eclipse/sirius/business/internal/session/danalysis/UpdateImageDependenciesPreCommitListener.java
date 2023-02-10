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
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.image.ImageManager;
import org.eclipse.sirius.business.api.image.RichTextAttributeRegistry;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.image.ImageDependenciesAnnotationHelper;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.description.DAnnotationEntry;

/**
 * A precommit listener that will update the images dependencies DAnnotationEntry for each new WorkspaceImage style
 * added, updated or deleted.
 * 
 * @author glenn
 * @author lfasani
 *
 */
public class UpdateImageDependenciesPreCommitListener extends ResourceSetListenerImpl {

    private static final String OWNED_STYLE = ImageDependenciesAnnotationHelper.OWNED_STYLE_FEATURE_NAME;

    private final ImageDependenciesAnnotationHelper imageDependenciesAnnotationHelper;

    private Session session;

    /**
     * Constructor.
     * 
     * @param dAnalysisSessionImpl
     *            the session
     */
    public UpdateImageDependenciesPreCommitListener(DAnalysisSessionImpl dAnalysisSessionImpl) {
        this.session = dAnalysisSessionImpl;
        this.imageDependenciesAnnotationHelper = new ImageDependenciesAnnotationHelper(dAnalysisSessionImpl);
    }

    @Override
    public boolean isPrecommitOnly() {
        return true;
    }

    @Override
    public boolean isAggregatePrecommitListener() {
        return true;
    }

    @Override
    /**
     * {@inheritDoc}
     * 
     * The notifier will be used to update images dependencies when the WorkspaceImage style is updated or removed;
     * otherwise, the newValue is used when the WorkspaceImage style is set.
     */
    public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
        if (session == null || !session.isOpen()) {
            return null;
        }

        Map<DRepresentation, List<String>> diagramToNewImageDependency = new HashMap<>();
        Map<DRepresentation, List<String>> diagramToOldImageDependency = new HashMap<>();
        for (Notification notification : event.getNotifications()) {
            Object newValue = notification.getNewValue();
            Object oldValue = notification.getOldValue();
            Set<EAttribute> richTextAttributes = RichTextAttributeRegistry.INSTANCE.getEAttributes();
            String featureName = StringUtil.EMPTY_STRING;
            DRepresentationElement dRepElement;
            if (notification.getFeature() instanceof ENamedElement) {
                featureName = ((ENamedElement) notification.getFeature()).getName();
            }
            // case when the WorkspaceImage style is created
            if (imageDependenciesAnnotationHelper.isWorkspaceImageInstance(newValue)) {
                EObject eContainer = ((EObject) newValue).eContainer();
                if (eContainer instanceof DRepresentationElement && OWNED_STYLE.equals(featureName)) {
                    dRepElement = (DRepresentationElement) eContainer;
                    DRepresentation representation = SiriusUtil.findRepresentation(dRepElement);
                    String workspacePath = getWorkspacePath((EObject) newValue);
                    if (workspacePath != null) {
                        Optional<String> projectName = imageDependenciesAnnotationHelper.getProjectFromImagePath(workspacePath);
                        if (representation != null && projectName.isPresent()) {
                            addElementInMultiMap(diagramToNewImageDependency, representation, projectName.get());
                        }
                    }
                }
            }
            // case when the WorkspaceImage style is modified
            else if (imageDependenciesAnnotationHelper.isWorkspaceImageInstance(notification.getNotifier())) {
                EObject notifierEObject = (EObject) notification.getNotifier();
                if (newValue instanceof String && oldValue instanceof String) {
                    // Change Workspace Path
                    if (notifierEObject.eContainer() instanceof DRepresentationElement && ImageDependenciesAnnotationHelper.WORKSPACE_PATH_FEATURE_NAME.equals(featureName)) {
                        dRepElement = (DRepresentationElement) notifierEObject.eContainer();
                        DRepresentation representation = SiriusUtil.findRepresentation(dRepElement);
                        String workspacePath = (String) newValue;
                        Optional<String> newProjectName = imageDependenciesAnnotationHelper.getProjectFromImagePath(workspacePath);
                        if (representation != null && newProjectName.isPresent()) {
                            addElementInMultiMap(diagramToNewImageDependency, representation, newProjectName.get());
                        }

                        String oldWorkspacePath = (String) oldValue;
                        Optional<String> oldProjectName = imageDependenciesAnnotationHelper.getProjectFromImagePath(oldWorkspacePath);
                        if (!newProjectName.equals(oldProjectName) && representation != null && oldProjectName.isPresent()) {
                            addElementInMultiMap(diagramToOldImageDependency, representation, oldProjectName.get());
                        }
                    }
                }
                // case when the rich text is modified
            } else if (richTextAttributes.contains(notification.getFeature())) {
                EObject notifierEObject = (EObject) notification.getNotifier();
                EAttribute attr = (EAttribute) notification.getFeature();
                Object value = notifierEObject.eGet(attr);
                if (value instanceof String) {
                    String valueStr = (String) value;
                    Pattern pattern = Pattern.compile(ImageManager.HTML_IMAGE_PATH_PATTERN);
                    Matcher matcher = pattern.matcher(valueStr);
                    while (matcher.find()) {
                        String workspacePath = matcher.group(1);
                        imageDependenciesAnnotationHelper.getProjectFromImagePath(workspacePath).ifPresent(projectName -> {
                            addElementInMultiMap(diagramToNewImageDependency, null, projectName);
                        });
                    }
                }
                // case when the WorkspaceImage style is removed
            } else if (imageDependenciesAnnotationHelper.isWorkspaceImageInstance(oldValue)) {
                EObject notifierEObject = (EObject) notification.getNotifier();
                EObject oldValueEObject = (EObject) oldValue;
                // Set ownedStyle, reset style properties to default values
                if (notifierEObject instanceof DRepresentationElement && OWNED_STYLE.equals(featureName)) {
                    dRepElement = (DRepresentationElement) notifierEObject;
                    DRepresentation representation = SiriusUtil.findRepresentation(dRepElement);
                    String workspacePath = getWorkspacePath(oldValueEObject);
                    Optional<String> projectName = imageDependenciesAnnotationHelper.getProjectFromImagePath(workspacePath);
                    if (representation != null && !projectName.isEmpty()) {
                        addElementInMultiMap(diagramToOldImageDependency, representation, projectName.get());
                    }
                }
            }
        }
        RecordingCommandWithCustomUndo recordingCommand = new RecordingCommandWithCustomUndo(this.session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                if (!diagramToNewImageDependency.isEmpty()) {
                    DAnnotationEntry imageDependenciesEntry = imageDependenciesAnnotationHelper.getOrCreateImagesDependenciesAnnotationEntry();
                    if (imageDependenciesEntry != null) {
                        imageDependenciesAnnotationHelper.addImageDependencyAnnotationDetails(diagramToNewImageDependency, imageDependenciesEntry);
                    }
                }
                if (!diagramToOldImageDependency.isEmpty()) {
                    imageDependenciesAnnotationHelper.removeImageDependencyAnnotationDetails(diagramToOldImageDependency);
                }
            }
        };
        return recordingCommand;
    }

    /**
     * Used to retrieve the workspacePath of a workspaceImage.
     * 
     * @param workspaceImage
     *            the object that is expected to be a WorkspaceImage instance
     * @return the workspacePath if it exists; an empty string otherwise
     */
    private String getWorkspacePath(EObject workspaceImage) {
        String workspacePath = StringUtil.EMPTY_STRING;
        EStructuralFeature eStructuralFeature = workspaceImage.eClass().getEStructuralFeature(ImageDependenciesAnnotationHelper.WORKSPACE_PATH_FEATURE_NAME);
        if (eStructuralFeature != null) {
            workspacePath = (String) workspaceImage.eGet(eStructuralFeature);
        }
        return workspacePath;
    }

    private boolean addElementInMultiMap(Map<DRepresentation, List<String>> repToProjectNames, DRepresentation rep, String projectName) {
        List<String> list = repToProjectNames.get(rep);
        if (list == null) {
            list = new ArrayList<>();
            repToProjectNames.put(rep, list);
        }
        return list.add(projectName);
    }

}
