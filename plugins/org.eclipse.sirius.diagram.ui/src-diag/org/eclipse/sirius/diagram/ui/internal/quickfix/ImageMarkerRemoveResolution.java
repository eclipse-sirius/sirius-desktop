/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES and others.
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

import java.util.Optional;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.resource.FileProvider;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingWithInterpreterHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.StyleHelper;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.diagram.tools.internal.validation.constraints.ImagePathWrappingStatus.ImagePathTarget;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.resource.NavigationMarkerConstants;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.ui.IEditorPart;

/**
 * Class providing quick fix for image that can not be found for:
 * <li>images associated to diagram nodes</li>
 * <li>images displayed in rich text editor</li>
 *
 * This quick fix will remove not found image
 *
 * @author Séraphin Costa
 *
 */
public class ImageMarkerRemoveResolution extends AbstractValidationFix {
    @Override
    public String getLabel() {
        return Messages.ImageMarkerRemove_label;
    }

    @Override
    protected void doExecuteFix(IMarker marker, IEditorPart editor, View markedView, Session session) {
        TransactionalEditingDomain transactionalEditingDomain = session.getTransactionalEditingDomain();
        String imageIssueKind = marker.getAttribute(NavigationMarkerConstants.IMAGE_PATH_TARGET_KEY, ""); //$NON-NLS-1$
        String imagePath = marker.getAttribute(NavigationMarkerConstants.IMAGE_PATH_KEY, null);
        boolean exists = FileProvider.getDefault().exists(new Path(imagePath), session);
        EObject target = markedView.getElement();
        Diagram gmfDiagram = markedView.getDiagram();
        EObject diagramTarget = gmfDiagram.getElement();

        if (!exists) {
            boolean fixSucceeded = false;
            if (ImagePathTarget.DREPRESENTATION_DESCRIPTOR.toString().equals(imageIssueKind)) {
                String featurename = marker.getAttribute(NavigationMarkerConstants.IMAGE_PATH_FEATURE_NAME, ""); //$NON-NLS-1$
                fixSucceeded = removeImagePathInRichText(target, featurename, imagePath, transactionalEditingDomain);
            } else if (ImagePathTarget.SEMANTIC_TARGET.toString().equals(imageIssueKind) && markedView.getElement() instanceof DSemanticDecorator) {
                String featurename = marker.getAttribute(NavigationMarkerConstants.IMAGE_PATH_FEATURE_NAME, ""); //$NON-NLS-1$
                fixSucceeded = removeImagePathInRichText(target, featurename, imagePath, transactionalEditingDomain);
            } else if (ImagePathTarget.WORKSPACE_IMAGE.toString().equals(imageIssueKind) && diagramTarget instanceof DDiagram) {
                DDiagram dDiagram = (DDiagram) diagramTarget;
                DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
                fixSucceeded = removeImageInDiagram(dDiagram, (DDiagramElement) target, transactionalEditingDomain);
            }

            if (fixSucceeded) {
                revalidate(editor, gmfDiagram);
            }
        } else {
            revalidate(editor, gmfDiagram);
        }

    }

    public static boolean removeImagePathInRichText(EObject eObject, String featurename, String imagePath, TransactionalEditingDomain ted) {
        boolean fixSucceeded = false;
        Optional<EAttribute> eAttributeOpt = eObject.eClass().getEAllAttributes().stream().filter(attr -> featurename.equals(attr.getName())).findFirst();
        if (eAttributeOpt.isPresent()) {
            EAttribute eAttribute = eAttributeOpt.get();
            Object stringObj = eObject.eGet(eAttribute);
            if (stringObj instanceof String) {
                String htmlText = (String) stringObj;
                String imgRegex = "<img\\s+[^>]*?src\\s*=\\s*(\"|')" + imagePath + "(\"|')[^>]*?>"; //$NON-NLS-1$ //$NON-NLS-2$
                ted.getCommandStack().execute(new RecordingCommand(ted) {
                    @Override
                    protected void doExecute() {
                        String newHtmlText = htmlText.replaceAll(imgRegex, ""); //$NON-NLS-1$
                        eObject.eSet(eAttribute, newHtmlText);
                    }
                });
                fixSucceeded = true;
            }
        } ;
        return fixSucceeded;
    }

    /**
     * Remove workspace image style in diagram (but keep all others components of style) Note: if default style is
     * workspace image, this function set the style to the default image
     *
     * @param dDiagram
     *            the diagram in which there is the node with workspace image
     * @param target
     *            the node with the workspace image we want to remove
     * @param ted
     *            transaction manager
     * @return true if image was removed, false otherwise
     */
    public static boolean removeImageInDiagram(DDiagram dDiagram, DDiagramElement target, TransactionalEditingDomain ted) {

        RepresentationElementMapping mapping = target.getMapping();
        EObject parentElement = target.eContainer();
        Style targetStyle = target.getStyle();

        if (mapping instanceof DiagramElementMapping //
                && parentElement instanceof DSemanticDecorator //
                && targetStyle instanceof WorkspaceImage) {

            DiagramElementMapping diagramMapping = (DiagramElementMapping) mapping;
            DSemanticDecorator parentDiagramElement = (DSemanticDecorator) parentElement;
            EObject parentObject = parentDiagramElement.getTarget();
            WorkspaceImage currentStyle = (WorkspaceImage) targetStyle;

            RecordingCommand command = new RecordingCommand(ted) {
                @Override
                protected void doExecute() {
                    // Helpers
                    IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(dDiagram);
                    MappingWithInterpreterHelper mappingHelper = new MappingWithInterpreterHelper(interpreter);
                    StyleHelper styleHelper = new StyleHelper(interpreter);

                    // if default style is workspace image, we just reset the path to default
                    // otherwise replace the entire style with default and copy parameters
                    if (currentStyle.getDescription() instanceof WorkspaceImageDescription) {
                        WorkspaceImageDescription styleDescription = (WorkspaceImageDescription) currentStyle.getDescription();

                        currentStyle.setWorkspacePath(styleDescription.getWorkspacePath());
                    } else {
                        Style newStyle = mappingHelper.getBestStyle(diagramMapping, target.getTarget(), target, parentObject, dDiagram);

                        // copy all feature of currentStyle to newStyle excepting workspacePath
                        EList<EStructuralFeature> currentStyleFeatures = DiagramPackage.eINSTANCE.getWorkspaceImage().getEAllStructuralFeatures();
                        EList<EStructuralFeature> newStyleFeatures = newStyle.eClass().getEAllStructuralFeatures();

                        currentStyleFeatures.stream() //
                                .filter(feature -> feature != DiagramPackage.eINSTANCE.getWorkspaceImage_WorkspacePath()) //
                                .filter(newStyleFeatures::contains) // intersection with newStyleFeatures
                                .forEach(feature -> newStyle.eSet(feature, currentStyle.eGet(feature)));

                        styleHelper.setAndRefreshStyle(target, null, newStyle);
                    }
                }
            };

            ted.getCommandStack().execute(command);
            return true;

        } else {
            return false;
        }
    }
}
