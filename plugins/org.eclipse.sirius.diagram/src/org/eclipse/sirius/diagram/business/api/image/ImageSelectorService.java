/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.api.image;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.ui.IEditorPart;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.metamodel.helper.StyleHelper;
import org.eclipse.sirius.diagram.business.internal.image.ImageSelectorDescriptor;
import org.eclipse.sirius.diagram.business.internal.image.ImageSelectorDescriptorRegistry;
import org.eclipse.sirius.diagram.business.internal.image.refresh.WorkspaceImageFigureRefresher;
import org.eclipse.sirius.diagram.business.internal.query.CustomizableQuery;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.ContainerStyle;
import org.eclipse.sirius.viewpoint.Customizable;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DDiagramElementContainer;
import org.eclipse.sirius.viewpoint.DNode;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.NodeStyle;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.WorkspaceImage;
import org.eclipse.sirius.viewpoint.description.style.StyleFactory;
import org.eclipse.sirius.viewpoint.description.style.WorkspaceImageDescription;

/**
 * Service class to get the most priority {@link ImageSelector}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ImageSelectorService {

    /**
     * Singleton of {@link ImageSelectorService}.
     */
    public static final ImageSelectorService INSTANCE = new ImageSelectorService();

    /**
     * Get a default {@link ImageSelector} to select. Compute the first
     * contribution which is not overridden by another extension
     * 
     * @return a default ImageSelector
     */
    public ImageSelector getImageSelector() {
        ImageSelector result = null;
        List<ImageSelectorDescriptor> imageSelectorDescriptors = ImageSelectorDescriptorRegistry.getRegisteredExtensions();
        if (!imageSelectorDescriptors.isEmpty()) {
            ImageSelectorDescriptor imageSelectorDescriptor = getFirstMostOverrider(imageSelectorDescriptors);
            if (imageSelectorDescriptor != null) {
                result = imageSelectorDescriptor.getImageSelector();
            }
        }
        if (result == null) {
            result = new WorkspaceImageSelector();
        }
        return result;
    }

    /**
     * Get the first {@link ImageSelectorDescriptor} of extension list or the
     * the most overriding extension.
     * 
     * @param imageSelectorDescriptors
     * 
     * @return
     */
    private ImageSelectorDescriptor getFirstMostOverrider(List<ImageSelectorDescriptor> imageSelectorDescriptors) {
        List<String> overriddenImageSelectorIDs = new ArrayList<String>();
        ImageSelectorDescriptor firstMostOverridingImageSelectorDescriptor = null;
        Iterator<ImageSelectorDescriptor> descriptorsIterator = imageSelectorDescriptors.iterator();
        while (descriptorsIterator.hasNext()) {
            // Recovers all overridden ImageSelectorDescriptor
            firstMostOverridingImageSelectorDescriptor = descriptorsIterator.next();
            String overrideValue = firstMostOverridingImageSelectorDescriptor.getOverrideValue();
            if (overrideValue != null) {
                overriddenImageSelectorIDs.add(overrideValue);
            }
        }
        descriptorsIterator = imageSelectorDescriptors.iterator();
        while (descriptorsIterator.hasNext()) {
            // Find the first ImageSelectorDescriptor that is not
            // overridden
            firstMostOverridingImageSelectorDescriptor = descriptorsIterator.next();
            if (!overriddenImageSelectorIDs.contains(firstMostOverridingImageSelectorDescriptor.getId())) {
                return firstMostOverridingImageSelectorDescriptor;
            }
        }

        return firstMostOverridingImageSelectorDescriptor;
    }

    /**
     * Update the specified style or replace it according to the specified
     * imagePath.
     * 
     * @param basicLabelStyle
     *            the style to update or replace
     * @param imagePath
     *            the new image path to use
     */
    public void updateStyle(BasicLabelStyle basicLabelStyle, String imagePath) {
        updateWorkspacePath(basicLabelStyle, imagePath);
        IEditorPart activeEditor = EclipseUIUtil.getActiveEditor();
        if (activeEditor instanceof DiagramEditor) {
            DiagramEditor diagramEditor = (DiagramEditor) activeEditor;
            WorkspaceImageFigureRefresher.refreshAllEditPart(diagramEditor.getDiagramEditPart());
        }
    }

    /**
     * Update the style according to a new image in the workspace.
     * 
     * @param basicLabelStyle
     *            the style to update or replace
     * @param workspacePath
     *            the new path of a image in the workspace
     */
    protected void updateWorkspacePath(BasicLabelStyle basicLabelStyle, String workspacePath) {
        Command updateWorkspacePathCmd = null;
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(basicLabelStyle);
        if (domain != null) {
            if (basicLabelStyle instanceof WorkspaceImage) {
                updateWorkspacePathCmd = SetCommand.create(domain, basicLabelStyle, ViewpointPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH, workspacePath);
                if (!basicLabelStyle.getCustomFeatures().contains(ViewpointPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH.getName())) {
                    updateWorkspacePathCmd = updateWorkspacePathCmd.chain(AddCommand.create(domain, basicLabelStyle, ViewpointPackage.Literals.CUSTOMIZABLE__CUSTOM_FEATURES,
                            ViewpointPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH.getName()));
                }
            } else {
                Object feature = getFeature(basicLabelStyle);
                Object newWorkspaceImageStyle = getNewWorkspaceImageStyle(basicLabelStyle, workspacePath);
                updateWorkspacePathCmd = SetCommand.create(domain, basicLabelStyle.eContainer(), feature, newWorkspaceImageStyle);
            }
            Command updateHeightCmd = getUpdateHeightCommand(domain, basicLabelStyle);
            if (updateHeightCmd != null) {
                updateWorkspacePathCmd = updateWorkspacePathCmd.chain(updateHeightCmd);
            }
            domain.getCommandStack().execute(updateWorkspacePathCmd);
        }
    }

    private Command getUpdateHeightCommand(TransactionalEditingDomain domain, BasicLabelStyle basicLabelStyle) {
        Command updateHeightCommand = null;
        EObjectQuery eObjectQuery = new EObjectQuery(basicLabelStyle.eContainer());
        Collection<EObject> inverseReferences = eObjectQuery.getInverseReferences(NotationPackage.Literals.VIEW__ELEMENT);
        if (!inverseReferences.isEmpty()) {
            EObject next = inverseReferences.iterator().next();
            if (next instanceof Node) {
                Node node = (Node) next;
                LayoutConstraint layoutConstraint = node.getLayoutConstraint();
                if (layoutConstraint instanceof Size) {
                    Size size = (Size) layoutConstraint;
                    if (size.getHeight() != -1) {
                        updateHeightCommand = SetCommand.create(domain, size, NotationPackage.Literals.SIZE__HEIGHT, -1);
                    }
                }
            }
        }
        return updateHeightCommand;
    }

    private Object getFeature(BasicLabelStyle basicLabelStyle) {
        Object feature = null;
        EObject container = basicLabelStyle.eContainer();
        if (container instanceof DNode) {
            feature = ViewpointPackage.Literals.DNODE__OWNED_STYLE;
        } else if (container instanceof DDiagramElementContainer) {
            feature = ViewpointPackage.Literals.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE;
        }
        return feature;
    }

    private Object getNewWorkspaceImageStyle(BasicLabelStyle basicLabelStyle, String workspacePath) {
        Style newWorkspaceImageStyle = null;
        WorkspaceImageDescription workspaceImageDescription = StyleFactory.eINSTANCE.createWorkspaceImageDescription();
        workspaceImageDescription.setWorkspacePath(workspacePath);
        newWorkspaceImageStyle = createAndAffectWorkspaceImageCustomized((DDiagramElement) basicLabelStyle.eContainer(), workspaceImageDescription);
        if (basicLabelStyle != null && newWorkspaceImageStyle instanceof LabelStyle) {
            copyProperties(basicLabelStyle, newWorkspaceImageStyle);
        }
        newWorkspaceImageStyle.setDescription(((Style) basicLabelStyle).getDescription());
        return newWorkspaceImageStyle;
    }

    private Style createAndAffectWorkspaceImageCustomized(final DDiagramElement dde, final WorkspaceImageDescription wid) {
        Style newStyle = null;
        if (dde instanceof DNode) {
            newStyle = createAndAffectNodeStyle((DNode) dde, wid);
        } else if (dde instanceof DDiagramElementContainer) {
            newStyle = createAndAffectContainerStyle((DDiagramElementContainer) dde, wid);
        }
        newStyle.getCustomFeatures().add(ViewpointPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH.getName());
        return newStyle;
    }

    private Style createAndAffectNodeStyle(DNode node, WorkspaceImageDescription wid) {
        Session session = SessionManager.INSTANCE.getSession(node.getTarget());
        final NodeStyle myStyle = new StyleHelper(session.getInterpreter()).createNodeStyle(wid);
        return myStyle;
    }

    private Style createAndAffectContainerStyle(DDiagramElementContainer container, WorkspaceImageDescription wid) {
        Session session = SessionManager.INSTANCE.getSession(container.getTarget());
        final ContainerStyle myStyle = new StyleHelper(session.getInterpreter()).createContainerStyle(wid);
        return myStyle;
    }

    private void copyProperties(final Customizable source, final Customizable target) {
        Collection<String> targetCustomizableFeatureNames = new CustomizableQuery(target).getCustomizableFeatureNames();
        Collection<String> sourceCustomizableFeatureNames = new CustomizableQuery(source).getCustomizableFeatureNames();
        for (EStructuralFeature sourceEStructuralFeature : source.eClass().getEAllStructuralFeatures()) {
            // CHECKSTYLE:OFF
            if (sourceCustomizableFeatureNames.contains(sourceEStructuralFeature.getName())) {
                Object sourceValue = source.eGet(sourceEStructuralFeature);
                EStructuralFeature targetEStructuralFeature = target.eClass().getEStructuralFeature(sourceEStructuralFeature.getName());
                if (targetEStructuralFeature != null && targetEStructuralFeature.getEType() == sourceEStructuralFeature.getEType()) {
                    if (targetCustomizableFeatureNames.contains(targetEStructuralFeature.getName())) {
                        if (sourceValue instanceof EObject) {
                            // RGBValues values copy
                            EObject sourceEObjectValue = (EObject) sourceValue;
                            Object targetValue = target.eGet(targetEStructuralFeature);
                            if (targetValue instanceof EObject) {
                                EObject targetEObjectValue = (EObject) targetValue;

                                if (sourceEObjectValue.eClass() == targetEObjectValue.eClass()) {
                                    for (EAttribute sourceEAttribute : sourceEObjectValue.eClass().getEAllAttributes()) {
                                        Object sourceEAttributeValue = sourceEObjectValue.eGet(sourceEAttribute);
                                        targetEObjectValue.eSet(sourceEAttribute, sourceEAttributeValue);
                                    }
                                }
                            }
                        } else {
                            // other customizable attributes value copy
                            target.eSet(targetEStructuralFeature, sourceValue);
                            if (source.getCustomFeatures().contains(targetEStructuralFeature.getName())) {
                                target.getCustomFeatures().add(targetEStructuralFeature.getName());
                            }
                        }
                    } else if (sourceValue instanceof Customizable) {
                        Customizable sourceEObjectValue = (Customizable) sourceValue;
                        Object targetValue = target.eGet(targetEStructuralFeature);
                        if (targetValue instanceof Customizable) {
                            Customizable targetEObjectValue = (Customizable) targetValue;
                            if (sourceEObjectValue.eClass() == targetEObjectValue.eClass()) {
                                copyProperties(sourceEObjectValue, targetEObjectValue);
                            }
                        }
                    }
                }
            }
            // CHECKSTYLE:ON
        }
    }
}
