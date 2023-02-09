/*******************************************************************************
 * Copyright (c) 2022, 2023 THALES GLOBAL SERVICES and others.
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
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.image.RichTextAttributeRegistry;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.resource.NavigationMarkerConstants;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * Class providing a mass quick fix for image that can not be found for:
 * <li>images associated to diagram nodes</li>
 * <li>images displayed in rich text editor</li>
 * 
 * This quick fix will replace the starting part of the image paths.
 *
 * @author lfasani
 */
public class ImageMarkerMassResolution extends AbstractValidationFix {

    public ImageMarkerMassResolution() {
    }

    @Override
    public String getLabel() {
        return Messages.ImageMarkerMassResolution_label;
    }

    @Override
    protected void doExecuteFix(IMarker marker, IEditorPart editor, View markedView, Session session) {
        String imagePath = marker.getAttribute(NavigationMarkerConstants.IMAGE_PATH_KEY, null);
        boolean exists = FileProvider.getDefault().exists(new Path(imagePath), session);
        if (!exists) {
            boolean fixSucceeded = ImageMarkerMassResolution.fixImagePathMassively(session, imagePath);
            if (fixSucceeded) {
                revalidate(editor, markedView.getDiagram());
            }
        } else {
            revalidate(editor, markedView.getDiagram());
        }

    }

    private static boolean fixImagePathMassivelyInDescription(Session session, String oldPathPrefix, String newPathPrefix) {
        Set<EAttribute> eAttributes = RichTextAttributeRegistry.INSTANCE.getEAttributes();

        boolean[] fixSucceeded = { false };
        for (Resource resource : session.getSemanticResources()) {
            EcoreUtil.getAllProperContents(resource, true).forEachRemaining(object -> {
                if (object instanceof EObject) {
                    fixSucceeded[0] = fixSucceeded[0] || updateImagePathInRichText(eAttributes, (EObject) object, oldPathPrefix, newPathPrefix);
                }
            });
        }

        for (DRepresentationDescriptor repDescriptor : DialectManager.INSTANCE.getAllRepresentationDescriptors(session)) {
            fixSucceeded[0] = fixSucceeded[0] || updateImagePathInRichText(eAttributes, repDescriptor, oldPathPrefix, newPathPrefix);
        }
        return fixSucceeded[0];

    }

    private static boolean fixImagePathMassivelyInWorkspaceImage(Session session, String oldPathPrefix, String newPathPrefix) {
        boolean[] fixSucceeded = { false };

        for (DRepresentation representation : DialectManager.INSTANCE.getAllRepresentations(session)) {
            Iterable<EObject> it = () -> representation.eAllContents();
            StreamSupport.stream(it.spliterator(), false).filter(WorkspaceImage.class::isInstance).forEach(wImage -> {
                String workspacePath = ((WorkspaceImage) wImage).getWorkspacePath();
                if (workspacePath.startsWith(oldPathPrefix)) {
                    String newWorkspacePath = workspacePath.replaceFirst(oldPathPrefix, newPathPrefix);
                    ((WorkspaceImage) wImage).setWorkspacePath(newWorkspacePath);
                    fixSucceeded[0] = true;
                }
            });
        }
        return fixSucceeded[0];
    }

    /**
     * Replace the starting part of the image paths for all diagram nodes or rich text description.
     * 
     * @param session
     *            the session
     * @param currentImagePath
     *            the current image PAth to update
     * @return true if some change has been done
     */
    public static boolean fixImagePathMassively(Session session, String currentImagePath) {
        boolean[] fixSucceeded = { false };

        // init the path with the current path without the trailing image name
        String imageFolder = currentImagePath.replaceFirst("/(?:.(?!/))+$", ""); //$NON-NLS-1$//$NON-NLS-2$
        Shell activeShell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
        // ask user for the path to replace
        ChangeMassivelyImagePathDialog dialog = new ChangeMassivelyImagePathDialog(activeShell, imageFolder);

        if (dialog.open() == Window.OK) {
            // remove trailing slash
            String oldPathPrefix = dialog.getOldPathPrefix().replaceAll("/+$", ""); //$NON-NLS-1$ //$NON-NLS-2$
            String newPathPrefix = dialog.getNewPathPrefix().replaceAll("/+$", ""); //$NON-NLS-1$ //$NON-NLS-2$

            session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
                @Override
                protected void doExecute() {
                    fixSucceeded[0] = fixImagePathMassivelyInDescription(session, oldPathPrefix, newPathPrefix);
                    if (dialog.processDiagramContent()) {
                        fixSucceeded[0] = fixImagePathMassivelyInWorkspaceImage(session, oldPathPrefix, newPathPrefix) || fixSucceeded[0];
                    }
                }
            });
        }
        return fixSucceeded[0];
    }

    private static boolean updateImagePathInRichText(Set<EAttribute> eAttributes, EObject eObject, String oldPathPrefix, String newPathPrefix) {
        boolean fixSucceeded = false;

        List<EAttribute> attributesToCheck = eObject.eClass().getEAllAttributes().stream().filter(eAttributes::contains).collect(Collectors.toList());
        for (EAttribute eAttribute : attributesToCheck) {
            Object stringObj = eObject.eGet(eAttribute);
            if (stringObj instanceof String) {
                String htmlText = (String) stringObj;
                String srcText = "src=\""; //$NON-NLS-1$
                String textToReplace = srcText + oldPathPrefix;
                if (htmlText.contains(textToReplace)) {
                    String newHtmlText = htmlText.replaceAll(textToReplace, srcText + newPathPrefix);
                    eObject.eSet(eAttribute, newHtmlText);
                    fixSucceeded = true;
                }
            }
        }
        return fixSucceeded;
    }
}
