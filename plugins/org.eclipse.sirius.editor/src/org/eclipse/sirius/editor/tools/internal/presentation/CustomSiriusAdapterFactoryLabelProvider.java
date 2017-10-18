/*******************************************************************************
 * Copyright (c) 2011, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.presentation;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider.StyledLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
class CustomSiriusAdapterFactoryLabelProvider extends StyledLabelProvider {
    private boolean showTypes;

    CustomSiriusAdapterFactoryLabelProvider(AdapterFactory adapterFactory, Viewer viewer) {
        super(adapterFactory, viewer);
    }

    @Override
    public StyledString getStyledText(Object object) {
        StyledString text = super.getStyledText(object);
        if (object instanceof EObject && showTypes) {
            StyledString styledString = new StyledString();
            styledString.append("<" + ((EObject) object).eClass().getName() + "> ", StyledString.QUALIFIER_STYLER);
            styledString.append(text);
            text = styledString;
        }
        return text;
    }

    /**
     * @return the showTypes
     */
    public boolean isShowTypes() {
        return showTypes;
    }

    /**
     * @param showTypes
     *            the showTypes to set
     */
    public void setShowTypes(boolean showTypes) {
        this.showTypes = showTypes;
    }

}
