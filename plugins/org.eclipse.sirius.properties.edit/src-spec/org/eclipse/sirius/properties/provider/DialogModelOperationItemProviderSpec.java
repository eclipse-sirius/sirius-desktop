/**
 * Copyright (c) 2017 Obeo.
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
import org.eclipse.emf.edit.provider.StyledString;
import org.eclipse.sirius.properties.DialogModelOperation;

/**
 * Subclass used to not have to modify the generated code.
 *
 * @author sbegaudeau
 */
public class DialogModelOperationItemProviderSpec extends DialogModelOperationItemProvider {

    /**
     * The constructor.
     *
     * @param adapterFactory
     *            The adapter factory
     */
    public DialogModelOperationItemProviderSpec(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public String getText(Object object) {
        return ((StyledString) getStyledText(object)).getString();
    }

    @Override
    public Object getStyledText(Object object) {
        String label = ((DialogModelOperation) object).getTitleExpression();
        StyledString styledLabel = new StyledString();
        if (label == null || label.length() == 0) {
            styledLabel.append(getString("_UI_DialogModelOperation_type")); //$NON-NLS-1$
        } else {
            styledLabel.append(label);
        }
        return styledLabel;
    }

}
