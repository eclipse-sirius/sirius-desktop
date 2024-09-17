/*******************************************************************************
 * Copyright (c) 2024 Obeo.
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
package org.eclipse.sirius.diagram.ui.tools.api.color;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.swt.widgets.Shell;

/**
 * Service responsible for retrieving an {@link IColorPalettePopup} implementation. This service allows dynamic
 * selection of a color palette popup based on available providers, which can be either custom or the default Sirius
 * provider. The popup helps users select colors for graphical elements.
 * <p>
 * It selects the appropriate {@link ColorPalettePopupProvider} according to priority, with the ability to fall back on
 * the default provider when no custom one is available.
 * </p>
 * 
 * @author <a href="mailto:glenn.plouhinec@obeo.fr">Glenn Plouhinec</a>
 */
public final class ColorPalettePopupService {

    /**
     * Extension point ID for {@link ColorPalettePopupProvider}.
     */
    public static final String ID = "org.eclipse.sirius.diagram.ui.colorPalettePopupProvider"; //$NON-NLS-1$

    /**
     * Extension point attribute for the {@link ColorPalettePopupProvider} class.
     */
    public static final String CLASS_ATTRIBUTE = "providerClass"; //$NON-NLS-1$

    private static ColorPalettePopupProvider defaultSiriusProvider;

    private static List<ColorPalettePopupProvider> customProviders;

    /**
     * Private constructor to prevent instantiation.
     */
    private ColorPalettePopupService() {
    }

    /**
     * Retrieves the appropriate {@link IColorPalettePopup} to display based on the given session, edit parts, and
     * property ID. The method first checks custom providers sorted by priority and falls back on the default provider
     * if necessary.
     * 
     * @param parent
     *            the parent shell where the popup should appear on.
     * @param session
     *            the current sirius session.
     * @param editParts
     *            the list of selected edit parts.
     * @param propertyId
     *            the propertyID, which could be "notation.FillStyle.fillColor", "notation.LineStyle.lineColor", or
     *            "notation.FontStyle.fontColor".
     * @return the {@link IColorPalettePopup} instance to be used
     */
    public static IColorPalettePopup getColorPalettePopup(Shell parent, Session session, List<IGraphicalEditPart> editParts, String propertyId) {
        List<ColorPalettePopupProvider> sortedCustomProviders = ColorPalettePopupService.getCustomProviders().stream() //
                .sorted(Comparator.comparingInt(ColorPalettePopupProvider::getPriority).reversed()) //
                .toList();
        for (ColorPalettePopupProvider provider : sortedCustomProviders) {
            if (provider.provides(session)) {
                return provider.getColorPalettePopup(parent, session, editParts, propertyId);
            }
        }
        return ColorPalettePopupService.getDefaultProvider().getColorPalettePopup(parent, session, editParts, propertyId);
    }

    /**
     * Retrieves the default Sirius {@link ColorPalettePopupProvider} that is used when no custom provider is available.
     *
     * @return the default Sirius provider
     */
    private static ColorPalettePopupProvider getDefaultProvider() {
        if (defaultSiriusProvider == null) {
            initializeProviders();
        }
        return defaultSiriusProvider;
    }

    /**
     * Retrieves the list of all custom {@link ColorPalettePopupProvider} instances available.
     *
     * @return the list of custom providers
     */
    private static List<ColorPalettePopupProvider> getCustomProviders() {
        if (customProviders == null) {
            initializeProviders();
        }
        return customProviders;
    }

    /**
     * Initializes both the custom and default {@link ColorPalettePopupProvider} instances by loading them from the
     * defined extension points.
     */
    private static void initializeProviders() {
        Map<String, Collection<ColorPalettePopupProvider>> providers = EclipseUtil.getExtensionPluginsByKey(ColorPalettePopupProvider.class, ID, CLASS_ATTRIBUTE, "id"); //$NON-NLS-1$
        defaultSiriusProvider = new DefaultColorPalettePopupProvider();

        customProviders = new ArrayList<>();
        for (Entry<String, Collection<ColorPalettePopupProvider>> entry : providers.entrySet()) {
            customProviders.addAll(entry.getValue());
        }
    }
}
