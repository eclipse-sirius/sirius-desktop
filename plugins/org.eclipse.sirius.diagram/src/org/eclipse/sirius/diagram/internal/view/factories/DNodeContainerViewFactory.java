/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.view.factories;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;

import com.google.common.collect.Lists;

import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.internal.edit.parts.DNodeContainerNameEditPart;
import org.eclipse.sirius.diagram.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.viewpoint.DNodeContainer;
import org.eclipse.sirius.viewpoint.description.style.SizeComputationContainerStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * @was-generated
 */
public class DNodeContainerViewFactory extends AbstractDesignerNodeFactory {

    /**
     * An adapter to mark the View as layout by the SiriusLayoutDataManager.
     */
    private static final Adapter JUST_CREATED = new Adapter() {

        public void setTarget(final Notifier newTarget) {
        }

        public void notifyChanged(final Notification notification) {
        }

        public boolean isAdapterForType(final Object type) {
            return type.equals(DNodeContainerViewFactory.class);
        }

        public Notifier getTarget() {
            return null;
        }

    };

    /**
     * @was-generated
     */
    protected List<?> createStyles(View view) {
        List<Style> styles = Lists.newArrayList();
        styles.add(NotationFactory.eINSTANCE.createShapeStyle());
        return styles;
    }

    /**
     * @was-generated
     */
    protected void decorateView(View containerView, View view, IAdaptable semanticAdapter, String semanticHint, int index, boolean persisted) {
        if (semanticHint == null) {
            semanticHint = SiriusVisualIDRegistry.getType(DNodeContainerEditPart.VISUAL_ID);
            view.setType(semanticHint);
        }
        super.decorateView(containerView, view, semanticAdapter, semanticHint, index, persisted);
        if (!DDiagramEditPart.MODEL_ID.equals(SiriusVisualIDRegistry.getModelID(containerView))) {
            EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
            shortcutAnnotation.setSource("Shortcut"); //$NON-NLS-1$
            shortcutAnnotation.getDetails().put("modelID", DDiagramEditPart.MODEL_ID); //$NON-NLS-1$
            view.getEAnnotations().add(shortcutAnnotation);
        }
        IAdaptable eObjectAdapter = null;
        EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
        if (eObject != null) {
            eObjectAdapter = new EObjectAdapter(eObject);
        }
        getViewService().createNode(eObjectAdapter, view, SiriusVisualIDRegistry.getType(DNodeContainerNameEditPart.VISUAL_ID), ViewUtil.APPEND, true, getPreferencesHint());
        getViewService().createNode(eObjectAdapter, view, SiriusVisualIDRegistry.getType(DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID), ViewUtil.APPEND, true, getPreferencesHint());
        updateLayoutConstraint(view, semanticAdapter);
    }

    private void updateLayoutConstraint(View view, IAdaptable semanticAdapter) {
        if (view instanceof Node && ((Node) view).getLayoutConstraint() instanceof Size) {
            Size size = (Size) ((Node) view).getLayoutConstraint();
            DNodeContainer viewNodecontainer = (DNodeContainer) semanticAdapter.getAdapter(DNodeContainer.class);
            if (viewNodecontainer != null) {
                StyleDescription styleDescription = viewNodecontainer.getStyle().getDescription();
                if (styleDescription instanceof SizeComputationContainerStyleDescription) {
                    String widthExpression = ((SizeComputationContainerStyleDescription) styleDescription).getWidthComputationExpression();
                    String heightExpression = ((SizeComputationContainerStyleDescription) styleDescription).getHeightComputationExpression();

                    computeWidthAndHeightExpressions(view, size, viewNodecontainer, widthExpression, heightExpression);

                }
            }
        }
    }

    private void computeWidthAndHeightExpressions(View view, Size size, DNodeContainer viewNodecontainer, String widthExpression, String heightExpression) {
        if (!widthExpression.equals("-1") || !heightExpression.equals("-1")) {
            try {
                Option<IInterpreter> iInterpreterOption = getInterpreter(viewNodecontainer);
                int widthExpressionResult = iInterpreterOption.get().evaluateInteger(viewNodecontainer.getTarget(), widthExpression);
                int heightExpressionResult = iInterpreterOption.get().evaluateInteger(viewNodecontainer.getTarget(), heightExpression);
                if (heightExpressionResult != 0 && heightExpressionResult != -1) {
                    size.setHeight(heightExpressionResult * LayoutUtils.SCALE);
                }
                if (widthExpressionResult != 0 && widthExpressionResult != -1) {
                    size.setWidth(widthExpressionResult * LayoutUtils.SCALE);
                }

                // mark this view as created to distinguish view that should not
                // be auto sized during the arrange all.
                view.eAdapters().add(JUST_CREATED);
            } catch (EvaluationException e) {
                // do nothing
            }
        }
    }

    private Option<IInterpreter> getInterpreter(DNodeContainer viewNodecontainer) {
        EObjectQuery eObjectQuery = new EObjectQuery(viewNodecontainer);
        Session session = eObjectQuery.getSession();
        if (session != null) {
            IInterpreter iInterpreter = session.getInterpreter();
            return Options.newSome(iInterpreter);
        }
        return Options.newNone();
    }
}
