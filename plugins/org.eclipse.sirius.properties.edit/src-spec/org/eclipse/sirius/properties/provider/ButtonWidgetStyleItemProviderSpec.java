/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.sirius.properties.ButtonWidgetStyle;

/**
 * Subclass used to not have to modify the generated code.
 *
 * @author sbegaudeau
 */
public class ButtonWidgetStyleItemProviderSpec extends ButtonWidgetStyleItemProvider {

    /**
     * The constructor.
     *
     * @param adapterFactory
     *            The adapter factory
     */
    public ButtonWidgetStyleItemProviderSpec(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public String getText(Object object) {
        String label = ((ButtonWidgetStyle) object).getLabelFontNameExpression();
        return label == null || label.length() == 0 ? getString("_UI_ButtonWidgetStyle_type") : //$NON-NLS-1$
                label;
    }
}
