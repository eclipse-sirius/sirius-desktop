/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.quickfix;

import java.util.List;
import java.util.Optional;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.tools.internal.validation.constraints.ImagePathWrappingStatus.ImagePathTarget;
import org.eclipse.sirius.diagram.ui.business.api.image.ImageSelector.SelectionMode;
import org.eclipse.sirius.diagram.ui.business.api.image.ImageSelectorService;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.resource.NavigationMarkerConstants;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.IEditorPart;

/**
 * Class providing quick fix for image that can not be found for:
 * <li>images associated to diagram nodes</li>
 * <li>images displayed in rich text editor</li>
 *
 * @author lfasani
 */
public class ImageMarkerResolution extends AbstractValidationFix {

    @Override
    public String getLabel() {
        return Messages.ImageMarkerResolution_label;
    }

    @Override
    protected void doExecuteFix(IMarker marker, IEditorPart editor, View markedView, TransactionalEditingDomain transactionalEditingDomain) {
        String imageIssueKind = marker.getAttribute(NavigationMarkerConstants.IMAGE_PATH_TARGET_KEY, ""); //$NON-NLS-1$
        String imagePath = marker.getAttribute(NavigationMarkerConstants.IMAGE_PATH_KEY, null);
        boolean exists = FileProvider.getDefault().exists(new Path(imagePath));
        if (!exists) {
            boolean fixSucceeded = false;
            if (ImagePathTarget.DREPRESENTATION_DESCRIPTOR.toString().equals(imageIssueKind)) {
                String featurename = marker.getAttribute(NavigationMarkerConstants.IMAGE_PATH_FEATURE_NAME, ""); //$NON-NLS-1$
                DRepresentationDescriptor representationDescriptor = getRepresentationDescriptor(transactionalEditingDomain, marker);
                fixSucceeded = fixImagePathInRichText(representationDescriptor, featurename, imagePath, transactionalEditingDomain);
            } else if (ImagePathTarget.SEMANTIC_TARGET.toString().equals(imageIssueKind) && markedView.getElement() instanceof DSemanticDecorator) {
                String featurename = marker.getAttribute(NavigationMarkerConstants.IMAGE_PATH_FEATURE_NAME, ""); //$NON-NLS-1$
                fixSucceeded = fixImagePathInRichText(((DSemanticDecorator) markedView.getElement()).getTarget(), featurename, imagePath, transactionalEditingDomain);
            } else if (ImagePathTarget.WORKSPACE_IMAGE.toString().equals(imageIssueKind)) {
                fixSucceeded = fixWorkspaceImagePath(markedView.getElement(), imagePath, transactionalEditingDomain);
            }

            if (fixSucceeded) {
                revalidate(editor, markedView.getDiagram());
            }
        } else {
            revalidate(editor, markedView.getDiagram());
        }

    }

    /**
     * Allow the user to select a new image and update the model accordingly.
     * 
     * @param eObject
     *            the object to update
     * @param featurename
     *            the feature to update
     * @param imagePath
     *            the new imagePath
     * @param ted
     *            the TransactionalEditingDomain
     * @return true if something has been changed in the model.
     */
    static public boolean fixImagePathInRichText(EObject eObject, String featurename, String imagePath, TransactionalEditingDomain ted) {
        boolean fixSucceeded = false;
        Optional<EAttribute> eAttributeOpt = eObject.eClass().getEAllAttributes().stream().filter(attr -> featurename.equals(attr.getName())).findFirst();
        if (eAttributeOpt.isPresent()) {
            EAttribute eAttribute = eAttributeOpt.get();
            Object stringObj = eObject.eGet(eAttribute);
            if (stringObj instanceof String) {
                String htmlText = (String) stringObj;
                List<String> selectImages = ImageSelectorService.INSTANCE.getImageSelector().selectImages(eObject, SelectionMode.MONO_SELECTION);
                if (selectImages.size() == 1) {
                    String quote = "\""; //$NON-NLS-1$
                    ted.getCommandStack().execute(new RecordingCommand(ted) {
                        @Override
                        protected void doExecute() {
                            String newHtmlText = htmlText.replaceAll(quote + imagePath + quote, quote + selectImages.get(0) + quote);
                            eObject.eSet(eAttribute, newHtmlText);
                        }
                    });
                    fixSucceeded = true;

                }
            }
        } ;
        return fixSucceeded;
    }

    /**
     * Allow the user to select a new image and update the workspaceImage accordingly.
     * 
     * @param eObject
     *            the object to update
     * @param imagePath
     *            the new imagePath
     * @param ted
     *            the TransactionalEditingDomain
     * @return true if something has been changed in the model.
     */
    static public boolean fixWorkspaceImagePath(EObject eObject, String imagePath, TransactionalEditingDomain ted) {
        boolean fixSucceeded = false;

        WorkspaceImage workspaceImage = (WorkspaceImage) eObject.eContents().stream().filter(WorkspaceImage.class::isInstance).findFirst().orElse(null);
        if (workspaceImage != null) {
            List<String> selectImages = ImageSelectorService.INSTANCE.getImageSelector().selectImages(eObject, SelectionMode.MONO_SELECTION);
            if (selectImages.size() == 1) {
                ted.getCommandStack().execute(new RecordingCommand(ted) {
                    @Override
                    protected void doExecute() {
                        workspaceImage.setWorkspacePath(selectImages.get(0));
                    }
                });
                fixSucceeded = true;
            }
        }
        return fixSucceeded;
    }

    private DRepresentationDescriptor getRepresentationDescriptor(TransactionalEditingDomain transactionalEditingDomain, IMarker marker) {
        String diagramDescriptorURI = marker.getAttribute(NavigationMarkerConstants.DIAGRAM_DESCRIPTOR_URI, null);

        if (diagramDescriptorURI != null) {
            ResourceSet set = transactionalEditingDomain.getResourceSet();
            if (set != null) {
                EObject markedDiagramDescriptor = set.getEObject(URI.createURI(diagramDescriptorURI), true);
                if (markedDiagramDescriptor instanceof DRepresentationDescriptor) {
                    return (DRepresentationDescriptor) markedDiagramDescriptor;
                }
            }
        }
        return null;
    }
}
