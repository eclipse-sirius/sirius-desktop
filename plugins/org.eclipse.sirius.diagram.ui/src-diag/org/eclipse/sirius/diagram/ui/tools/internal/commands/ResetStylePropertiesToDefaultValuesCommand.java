/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.commands;

import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingWithInterpreterHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.StyleHelper;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.internal.refresh.diagram.ViewPropertiesSynchronizer;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.style.ResetStylePropertiesToDefaultValuesAction;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.Style;

/**
 * Specific command to reset style properties to their default values.
 * 
 * @author mporhel
 */
public class ResetStylePropertiesToDefaultValuesCommand extends RecordingCommand {

    private DDiagram dDiagram;

    private Map<View, DDiagramElement> customizedViews;

    /**
     * Constructor.
     * 
     * @param domain
     *            the editing domain
     * @param dDiagram
     *            the {@link DDiagram} on which to reset style customization
     * @param customizedViews
     *            the customized {@link View}s to reset
     */
    public ResetStylePropertiesToDefaultValuesCommand(final TransactionalEditingDomain domain, DDiagram dDiagram, Map<View, DDiagramElement> customizedViews) {
        super(domain, ResetStylePropertiesToDefaultValuesAction.ACTION_NAME);
        this.dDiagram = dDiagram;
        this.customizedViews = customizedViews;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(dDiagram);
        MappingWithInterpreterHelper mappingHelper = new MappingWithInterpreterHelper(interpreter);
        StyleHelper styleHelper = new StyleHelper(interpreter);
        for (Entry<View, DDiagramElement> entry : customizedViews.entrySet()) {
            View view = entry.getKey();
            DDiagramElement dDiagramElement = entry.getValue();
            if (dDiagramElement != null) {
                resetDDiagramElementCustomizations(dDiagramElement, mappingHelper, styleHelper);
            }
            resetViewStylePropertiesToDefaultValues(view);
        }
    }

    private void resetDDiagramElementCustomizations(DDiagramElement dDiagramElement, MappingWithInterpreterHelper mappingHelper, StyleHelper styleHelper) {
        DSemanticDecorator parentDDiagramElt = (DSemanticDecorator) dDiagramElement.eContainer();
        DiagramElementMapping diagramElementMapping = dDiagramElement.getDiagramElementMapping();
        if (dDiagramElement instanceof DEdge) {
            DEdge dEdge = (DEdge) dDiagramElement;
            if (dEdge.getOwnedStyle() != null) {
                dEdge.getOwnedStyle().getCustomFeatures().clear();
                if (dEdge.getOwnedStyle().getBeginLabelStyle() != null) {
                    dEdge.getOwnedStyle().getBeginLabelStyle().getCustomFeatures().clear();
                }
                if (dEdge.getOwnedStyle().getCenterLabelStyle() != null) {
                    dEdge.getOwnedStyle().getCenterLabelStyle().getCustomFeatures().clear();
                }
                if (dEdge.getOwnedStyle().getEndLabelStyle() != null) {
                    dEdge.getOwnedStyle().getEndLabelStyle().getCustomFeatures().clear();
                }
                Style bestStyle = mappingHelper.getBestStyle(diagramElementMapping, dEdge.getTarget(), dEdge, parentDDiagramElt.getTarget(), dDiagram);
                if (bestStyle instanceof EdgeStyle) {
                    EdgeStyle edgeStyle = (EdgeStyle) bestStyle;
                    dEdge.setOwnedStyle(edgeStyle);
                    // Now the style has a container. This container is needed
                    // to compute the interpreted expression. So launch a
                    // refresh for these expressions (edge size and some
                    // colors).
                    styleHelper.refreshStyle(edgeStyle);
                }
            }
        } else if (dDiagramElement instanceof DNode) {
            DNode dNode = (DNode) dDiagramElement;
            if (dNode.getOwnedStyle() != null) {
                dNode.getOwnedStyle().getCustomFeatures().clear();
                Style bestStyle = mappingHelper.getBestStyle(diagramElementMapping, dNode.getTarget(), dNode, parentDDiagramElt.getTarget(), dDiagram);
                if (bestStyle instanceof NodeStyle) {
                    NodeStyle nodeStyle = (NodeStyle) bestStyle;
                    dNode.setOwnedStyle(nodeStyle);
                    // Now the style has a container. This container is needed
                    // to compute the interpreted expression. So launch a
                    // refresh for these expressions
                    // (borderSizeComputationExpression, ...).
                    styleHelper.refreshStyle(nodeStyle);
                }
            }
        } else if (dDiagramElement instanceof DDiagramElementContainer) {
            DDiagramElementContainer dDiagramElementContainer = (DDiagramElementContainer) dDiagramElement;
            if (dDiagramElementContainer.getOwnedStyle() != null) {
                dDiagramElementContainer.getOwnedStyle().getCustomFeatures().clear();
                Style bestStyle = mappingHelper.getBestStyle(diagramElementMapping, dDiagramElementContainer.getTarget(), dDiagramElementContainer, parentDDiagramElt.getTarget(), dDiagram);
                if (bestStyle instanceof ContainerStyle) {
                    ContainerStyle containerStyle = (ContainerStyle) bestStyle;
                    dDiagramElementContainer.setOwnedStyle(containerStyle);
                    // Now the style has a container. This container is needed
                    // to compute the interpreted expression. So launch a
                    // refresh for these expressions
                    // (borderSizeComputationExpression, ...).
                    styleHelper.refreshStyle(containerStyle);
                }
            }
        } else if (dDiagramElement instanceof DNodeListElement) {
            DNodeListElement dNodeElementElement = (DNodeListElement) dDiagramElement;
            if (dNodeElementElement.getOwnedStyle() != null) {
                dNodeElementElement.getOwnedStyle().getCustomFeatures().clear();
                Style bestStyle = mappingHelper.getBestStyle(diagramElementMapping, dNodeElementElement.getTarget(), dNodeElementElement, parentDDiagramElt.getTarget(), dDiagram);
                if (bestStyle instanceof NodeStyle) {
                    NodeStyle nodeStyle = (NodeStyle) bestStyle;
                    dNodeElementElement.setOwnedStyle(nodeStyle);
                    // Now the style has a container. This container is needed
                    // to compute the interpreted expression. So launch a
                    // refresh for these expressions
                    // (borderSizeComputationExpression, ...).
                    styleHelper.refreshStyle(nodeStyle);
                }
            }
        }
    }

    private void resetViewStylePropertiesToDefaultValues(View view) {
        ViewQuery viewQuery = new ViewQuery(view);
        for (Object obj : view.getStyles()) {
            if (obj instanceof org.eclipse.gmf.runtime.notation.Style) {
                org.eclipse.gmf.runtime.notation.Style style = (org.eclipse.gmf.runtime.notation.Style) obj;
                for (EAttribute eAttribute : style.eClass().getEAllAttributes()) {
                    if (ViewQuery.CUSTOMIZABLE_GMF_EXCLUSIVE_STYLE_ATTRIBUTES.contains(eAttribute)) {
                        Object currentValue = style.eGet(eAttribute);
                        Object defaultValue = viewQuery.getDefaultValue(eAttribute);
                        if (currentValue != null && !currentValue.equals(defaultValue) || currentValue == null && defaultValue != null) {
                            style.eSet(eAttribute, defaultValue);
                        }
                    }
                }
            }
        }
        if (view instanceof Shape || view instanceof Connector) {
            // manage Note and Text especially because style properties are
            // directly stored on Shape
            // manage NoteAttachment especially because style properties are
            // directly stored on Connector
            for (EAttribute eAttribute : view.eClass().getEAllAttributes()) {
                if (NotationPackage.Literals.STYLE.isSuperTypeOf(eAttribute.getEContainingClass()) && ViewQuery.CUSTOMIZABLE_GMF_STYLE_ATTRIBUTES.contains(eAttribute)) {
                    Object currentValue = view.eGet(eAttribute);
                    Object defaultValue = viewQuery.getDefaultValue(eAttribute);
                    if (currentValue != null && !currentValue.equals(defaultValue) || currentValue == null && defaultValue != null) {
                        view.eSet(eAttribute, defaultValue);
                    }
                }
            }
        }
        new ViewPropertiesSynchronizer().synchronizeViewProperties(view);
    }
}
