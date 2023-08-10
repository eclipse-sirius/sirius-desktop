/*******************************************************************************
 * Copyright (c) 2021, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.business.api.image;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.StyleHelper;
import org.eclipse.sirius.diagram.description.style.BorderedStyleDescription;
import org.eclipse.sirius.diagram.description.style.StyleFactory;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.diagram.model.business.internal.query.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.diagram.model.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.ui.business.internal.image.refresh.WorkspaceImageFigureRefresher;
import org.eclipse.sirius.diagram.ui.business.internal.query.CustomizableQuery;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.Customizable;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.eclipse.ui.IEditorPart;

/**
 * Helper to manage WorkspaceImage style.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class WorkspaceImageHelper {

    /**
     * Singleton of {@link WorkspaceImageHelper}.
     */
    public static final WorkspaceImageHelper INSTANCE = new WorkspaceImageHelper();

    /**
     * Update the specified style or replace it according to the specified imagePath.
     * 
     * @param basicLabelStyle
     *            the style to update or replace
     * @param imagePath
     *            the new image path to use
     */
    public void updateStyle(BasicLabelStyle basicLabelStyle, String imagePath) {
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(basicLabelStyle);
        if (domain != null) {
            Command command = getWorkspacePathChangeCommand(domain, basicLabelStyle, imagePath);
            domain.getCommandStack().execute(command);
            refreshStyle();
        }
    }

    /**
     * Update the specified styles or replace it according to the specified imagePath.
     * 
     * @param basicLabelStyles
     *            the list of styles to update or replace
     * @param imagePath
     *            the new image path to use
     */
    public void updateManyStyles(List<BasicLabelStyle> basicLabelStyles, String imagePath) {
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(basicLabelStyles);
        if (domain != null) {
            CompoundCommand command = new CompoundCommand();
            for (BasicLabelStyle basicLabelStyle : basicLabelStyles) {
                command.append(getWorkspacePathChangeCommand(domain, basicLabelStyle, imagePath));
            }
            domain.getCommandStack().execute(command);
            refreshStyle();
        }
    }

    /**
     * Refreshes style after workspace image update.
     * 
     * This method refresh the style of workspace image, this method must be executed after one or many execution of
     * command returned by getWorkspacePathChangeCommand.
     */
    public void refreshStyle() {
        IEditorPart activeEditor = EclipseUIUtil.getActiveEditor();
        if (activeEditor instanceof DiagramEditor diagramEditor) {
            WorkspaceImageFigureRefresher.refreshAllEditPart(diagramEditor.getDiagramEditPart());
        }
    }

    /**
     * Build command to update the style according to a new image in the workspace.
     * 
     * @param domain
     *            the editing domain for command, must be non-null
     * @param basicLabelStyle
     *            the style to update or replace
     * @param workspacePath
     *            the new path of a image in the workspace
     * @return the command to execute to update specified workspace image style.
     */
    public Command getWorkspacePathChangeCommand(TransactionalEditingDomain domain, BasicLabelStyle basicLabelStyle, String workspacePath) {
        Command updateWorkspacePathCmd = null;
        if (basicLabelStyle instanceof WorkspaceImage) {
            updateWorkspacePathCmd = SetCommand.create(domain, basicLabelStyle, DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH, workspacePath);
            if (!basicLabelStyle.getCustomFeatures().contains(DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH.getName())) {
                updateWorkspacePathCmd = updateWorkspacePathCmd
                        .chain(AddCommand.create(domain, basicLabelStyle, ViewpointPackage.Literals.CUSTOMIZABLE__CUSTOM_FEATURES, DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH.getName()));
            }
        } else {
            Object feature = getFeature(basicLabelStyle);
            Object newWorkspaceImageStyle = getNewWorkspaceImageStyle(basicLabelStyle, workspacePath);
            updateWorkspacePathCmd = SetCommand.create(domain, basicLabelStyle.eContainer(), feature, newWorkspaceImageStyle);
        }
        return updateWorkspacePathCmd;
    }

    private Object getFeature(BasicLabelStyle basicLabelStyle) {
        Object feature = null;
        EObject container = basicLabelStyle.eContainer();
        if (container instanceof DNode) {
            feature = DiagramPackage.Literals.DNODE__OWNED_STYLE;
        } else if (container instanceof DDiagramElementContainer) {
            feature = DiagramPackage.Literals.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE;
        }
        return feature;
    }

    private Object getNewWorkspaceImageStyle(BasicLabelStyle basicLabelStyle, String workspacePath) {
        Style newWorkspaceImageStyle = null;
        WorkspaceImageDescription workspaceImageDescription = StyleFactory.eINSTANCE.createWorkspaceImageDescription();
        workspaceImageDescription.setWorkspacePath(workspacePath);
        DDiagramElement dde = (DDiagramElement) basicLabelStyle.eContainer();
        newWorkspaceImageStyle = createAndAffectWorkspaceImageCustomized(dde, workspaceImageDescription);
        if (basicLabelStyle != null && newWorkspaceImageStyle instanceof LabelStyle) {
            copyCustomizedProperties(basicLabelStyle, newWorkspaceImageStyle);
        }

        if (basicLabelStyle instanceof BorderedStyle && newWorkspaceImageStyle instanceof WorkspaceImage) {
            copyBorderProperties(dde, (BorderedStyle) basicLabelStyle, (WorkspaceImage) newWorkspaceImageStyle, workspaceImageDescription);
        }
        newWorkspaceImageStyle.setDescription(((Style) basicLabelStyle).getDescription());
        return newWorkspaceImageStyle;
    }

    private void copyBorderProperties(DDiagramElement dde, BorderedStyle oldStyle, WorkspaceImage workspaceImage, WorkspaceImageDescription workspaceImageDescription) {

        // Copy the border properties for Region and RegionContainer only.
        if ((dde instanceof DNodeContainer && new DNodeContainerExperimentalQuery((DNodeContainer) dde).isRegionContainer())
                || (dde instanceof DDiagramElementContainer && new DDiagramElementContainerExperimentalQuery((DDiagramElementContainer) dde).isRegion())) {

            workspaceImage.setBorderColor(oldStyle.getBorderColor());
            workspaceImage.setBorderLineStyle(oldStyle.getBorderLineStyle());
            workspaceImage.setBorderSize(oldStyle.getBorderSize());

            StyleDescription oldDescription = oldStyle.getDescription();
            if (oldDescription instanceof BorderedStyleDescription) {
                BorderedStyleDescription oldDesc = (BorderedStyleDescription) oldDescription;
                workspaceImageDescription.setBorderColor(oldDesc.getBorderColor());
                workspaceImageDescription.setBorderLineStyle(oldDesc.getBorderLineStyle());
                workspaceImageDescription.setBorderSizeComputationExpression(oldDesc.getBorderSizeComputationExpression());
            }
        }
    }

    private Style createAndAffectWorkspaceImageCustomized(final DDiagramElement dde, final WorkspaceImageDescription wid) {
        Style newStyle = null;
        if (dde instanceof DNode) {
            newStyle = createAndAffectNodeStyle((DNode) dde, wid);
        } else if (dde instanceof DDiagramElementContainer) {
            newStyle = createAndAffectContainerStyle((DDiagramElementContainer) dde, wid);
        }
        if (newStyle != null) {
            newStyle.getCustomFeatures().add(DiagramPackage.Literals.WORKSPACE_IMAGE__WORKSPACE_PATH.getName());
        }
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

    private void copyCustomizedProperties(final Customizable source, final Customizable target) {
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
                                copyCustomizedProperties(sourceEObjectValue, targetEObjectValue);
                            }
                        }
                    }
                }
            }
            // CHECKSTYLE:ON
        }
    }
}
