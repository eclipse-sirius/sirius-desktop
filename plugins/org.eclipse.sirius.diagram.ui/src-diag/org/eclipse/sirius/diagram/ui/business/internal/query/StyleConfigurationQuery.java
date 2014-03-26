/*******************************************************************************
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.query;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.swt.graphics.Image;

/**
 * <p>
 * A class aggregating all the queries (read-only!) having a
 * {@link org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.StyleConfiguration}
 * as a starting point.
 * </p>
 * 
 * <p>
 * Uses the Sirius Diagram preferences to determine whether icons of labels
 * should be displayed or not.
 * </p>
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class StyleConfigurationQuery {

    /**
     * The {@link StyleConfiguration} that will be queried.
     */
    protected StyleConfiguration styleConfig;

    /**
     * Creates a new StyleConfigurationQuery.
     * 
     * @param styleConfiguration
     *            the {@link StyleConfiguration} that will be queried
     */
    public StyleConfigurationQuery(StyleConfiguration styleConfiguration) {
        this.styleConfig = styleConfiguration;
    }

    /**
     * <p>
     * Returns the icon of the specified {@link DDiagramElement}.
     * </p>
     * <p>
     * * <b>Note : </b> If end-user wants to hide this element's icon (see
     * {@link SiriusDiagramUiPreferencesKeys#PREF_HIDE_LABEL_ICONS_ON_SHAPES}
     * and
     * {@link SiriusDiagramUiPreferencesKeys#PREF_HIDE_LABEL_ICONS_ON_CONNECTORS}
     * ), this will always return null.
     * </p>
     * 
     * @param vpElement
     *            the {@link DDiagramElement}.
     * @param editPart
     *            the edit part
     * @return the icon of the specified {@link DDiagramElement}.
     */
    public Image getLabelIcon(DDiagramElement vpElement, IGraphicalEditPart editPart) {
        // If end-user does not want to hide this element's icon
        if (!labelIconsMustBeHidden(vpElement)) {
            // We return the icon as defined in the styleConfig
            return styleConfig.getLabelIcon(vpElement, editPart);
        }
        return null;
    }

    /**
     * Indicates whether the icon of the given element should be hidden, based
     * on the Sirius preferences values.
     * 
     * @param element
     *            the element to test
     * @return true if the icon of the given element should be displayed, false
     *         if it should be hidden
     */
    protected boolean labelIconsMustBeHidden(DDiagramElement element) {
        boolean mustHideLabelIconsOnShape = false;
        IPreferenceStore prefs = DiagramUIPlugin.getPlugin().getPreferenceStore();

        // We get the preference according to the given element's type
        if (element instanceof DEdge) {
            mustHideLabelIconsOnShape = prefs.getBoolean(SiriusDiagramUiPreferencesKeys.PREF_HIDE_LABEL_ICONS_ON_CONNECTORS.name());
        } else if (element instanceof AbstractDNode) {
            mustHideLabelIconsOnShape = prefs.getBoolean(SiriusDiagramUiPreferencesKeys.PREF_HIDE_LABEL_ICONS_ON_SHAPES.name());
        }
        return mustHideLabelIconsOnShape;
    }

}
