/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

/**
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
class CustomSiriusAdapterFactoryLabelProvider extends AdapterFactoryLabelProvider {
    private boolean showTypes;

    public CustomSiriusAdapterFactoryLabelProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }


    @Override
    public String getText(Object object) {
        String text = super.getText(object);
        if (object instanceof EObject && showTypes) {
            text = "<" + ((EObject) object).eClass().getName() + "> " + text;
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
