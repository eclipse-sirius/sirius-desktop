/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.view.factories;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;

/**
 * Abstract class to Generalized all common behavior between
 * DNodeContainerViewFactory, DNodeContainer2ViewFactory, DNodeListViewFactory
 * and DNodeList2ViewFactory.
 * 
 * @author fbarbin
 * 
 */
public class AbstractContainerViewFactory extends AbstractDesignerNodeFactory {

    private static final Integer AUTO_SIZE = Integer.valueOf(-1);

    /**
     * An adapter to mark the View as layout by the ViewPointLayoutDataManager.
     */
    private static final Adapter JUST_CREATED = new Adapter() {

        public void setTarget(final Notifier newTarget) {
        }

        public void notifyChanged(final Notification notification) {
        }

        public boolean isAdapterForType(final Object type) {
            return type.equals(AbstractContainerViewFactory.class);
        }

        public Notifier getTarget() {
            return null;
        }

    };

    /**
     * Computes the container size.
     * 
     * @param view
     *            the view itself
     * @param semanticAdapter
     *            the semantic element of the view
     */
    protected void updateLayoutConstraint(View view, IAdaptable semanticAdapter) {
        if (view instanceof Node && ((Node) view).getLayoutConstraint() instanceof Size) {
            Size size = (Size) ((Node) view).getLayoutConstraint();
            DDiagramElementContainer viewNodecontainer = (DDiagramElementContainer) semanticAdapter.getAdapter(DDiagramElementContainer.class);
            if (viewNodecontainer != null) {
                Integer width = viewNodecontainer.getWidth();
                Integer height = viewNodecontainer.getHeight();
                if (width == null || width <= 0) {
                    width = AUTO_SIZE;
                }

                if (height == null || height <= 0) {
                    height = AUTO_SIZE;
                }
                computeWidthAndHeightExpressions(view, size, viewNodecontainer, width, height);
            }
        }
    }

    private void computeWidthAndHeightExpressions(View view, Size size, DDiagramElementContainer viewNodecontainer, Integer width, Integer height) {
        boolean hasToBeMarked = false;

        if (!height.equals(AUTO_SIZE)) {
            size.setHeight(height * LayoutUtils.SCALE);
            hasToBeMarked = true;
        }
        if (!width.equals(AUTO_SIZE)) {
            size.setWidth(width * LayoutUtils.SCALE);
            hasToBeMarked = true;
        }

        // mark this view as created to distinguish view that should not
        // be auto sized during the arrange all.
        if (hasToBeMarked) {
            view.eAdapters().add(JUST_CREATED);
        }
    }
}
