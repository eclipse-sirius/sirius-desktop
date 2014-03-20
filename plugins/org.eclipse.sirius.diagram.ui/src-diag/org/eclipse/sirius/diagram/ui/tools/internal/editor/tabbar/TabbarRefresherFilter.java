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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * A {@link NotificationFilter} to refresh the tabbar only about some features
 * changes.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TabbarRefresherFilter extends NotificationFilter.Custom {

    @Override
    public boolean matches(Notification notification) {
        // CHECKSTYLE:OFF
        boolean matches = !notification.isTouch()
                && (notification.getFeature() == DiagramPackage.Literals.DDIAGRAM_ELEMENT__VISIBLE || notification.getFeature() == DiagramPackage.Literals.DEDGE__IS_FOLD
                        || notification.getFeature() == DiagramPackage.Literals.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS || notification.getFeature() == DiagramPackage.Literals.DDIAGRAM__ACTIVATED_FILTERS
                        || notification.getFeature() == DiagramPackage.Literals.DDIAGRAM__ACTIVATED_LAYERS || notification.getFeature() == DiagramPackage.Literals.ABSTRACT_DNODE__ARRANGE_CONSTRAINTS
                        || notification.getFeature() == DiagramPackage.Literals.DEDGE__ARRANGE_CONSTRAINTS || notification.getFeature() == ViewpointPackage.Literals.CUSTOMIZABLE__CUSTOM_FEATURES
                        || notification.getFeature() == DiagramPackage.Literals.DNODE__OWNED_STYLE || notification.getFeature() == DiagramPackage.Literals.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE
                        || notification.getFeature() == DiagramPackage.Literals.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS || ViewQuery.CUSTOMIZABLE_GMF_STYLE_ATTRIBUTES.contains(notification.getFeature()));
        // CHECKSTYLE:ON
        return matches;
    }
}
