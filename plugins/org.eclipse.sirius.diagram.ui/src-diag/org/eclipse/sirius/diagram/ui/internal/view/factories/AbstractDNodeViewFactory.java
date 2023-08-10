/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.internal.view.factories;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractViewFactory;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.ShapeStyle;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.description.style.SquareDescription;
import org.eclipse.sirius.diagram.ui.business.internal.query.DNodeQuery;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;

/**
 * Abstract view factory to gather the creation of the different type of view created from DNode.
 * 
 * @author mporhel
 * 
 */
public abstract class AbstractDNodeViewFactory extends AbstractDesignerNodeFactory {

    /**
     * Get specific visual id of the current factory.
     * 
     * @return a specific visualId.
     */
    protected abstract int getVisualId();

    /**
     * Get specific name visual id of the current factory.
     * 
     * @return a specific visualId.
     */
    protected abstract int getNameVisualId();

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<ShapeStyle> createStyles(View view) {
        List<ShapeStyle> styles = new ArrayList<ShapeStyle>();
        styles.add(NotationFactory.eINSTANCE.createShapeStyle());
        return styles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void decorateView(View containerView, View view, IAdaptable semanticAdapter, String semanticHint, int index, boolean persisted) {
        String semHint = checkSemanticHint(view, semanticHint);

        super.decorateView(containerView, view, semanticAdapter, semHint, index, persisted);

        addShortcutEannotation(containerView, view);

        IAdaptable eObjectAdapter = null;
        EObject eObject = semanticAdapter.getAdapter(EObject.class);
        if (eObject != null) {
            eObjectAdapter = new EObjectAdapter(eObject);
        }

        createLabelView(view, eObjectAdapter, eObject);

        updateLayoutConstraint(view, semanticAdapter);
    }

    private String checkSemanticHint(View view, String semanticHint) {
        String semHint = semanticHint;
        if (semHint == null) {
            semHint = SiriusVisualIDRegistry.getType(getVisualId());
            view.setType(semHint);
        }
        return semHint;
    }

    private void addShortcutEannotation(View containerView, View view) {
        // only for DNodeViewFactory
        if (DNodeEditPart.VISUAL_ID == getVisualId() && !DDiagramEditPart.MODEL_ID.equals(SiriusVisualIDRegistry.getModelID(containerView))) {
            EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
            shortcutAnnotation.setSource("Shortcut"); //$NON-NLS-1$
            shortcutAnnotation.getDetails().put("modelID", DDiagramEditPart.MODEL_ID); //$NON-NLS-1$
            view.getEAnnotations().add(shortcutAnnotation);
        }
    }

    private void createLabelView(View view, IAdaptable eObjectAdapter, EObject eObject) {
        // there was an additional condition for DNode2... and DNode4..., if
        // name was empty, no label node was created.
        // the condition was removed because the label was never
        // created/refreshed on name changes.
        Node node = AbstractViewFactory.getViewService().createNode(eObjectAdapter, view, SiriusVisualIDRegistry.getType(getNameVisualId()), ViewUtil.APPEND, true, getPreferencesHint());
        if (eObject instanceof DDiagramElement) {
            node.setVisible(!new DDiagramElementQuery((DDiagramElement) eObject).isLabelHidden());
        }
    }

    private void updateLayoutConstraint(View view, IAdaptable semanticAdapter) {
        if (view instanceof Node && ((Node) view).getLayoutConstraint() instanceof Size) {
            Size size = (Size) ((Node) view).getLayoutConstraint();
            DNode viewNode = semanticAdapter.getAdapter(DNode.class);
            if (viewNode != null) {
                boolean isSquareDescription = false;
                if (viewNode.getStyle() != null) {
                    isSquareDescription = viewNode.getStyle().getDescription() instanceof SquareDescription;
                }
                boolean isAutoSizeStyle = new DNodeQuery(viewNode).isAutoSizeStyle();
                if (!isSquareDescription || (isSquareDescription && !isAutoSizeStyle)) {
                    Dimension defaultDimension = new DNodeQuery(viewNode).getDefaultDimension();
                    size.setHeight(defaultDimension.height);
                    size.setWidth(defaultDimension.width);
                }
            }
        }
    }
}
