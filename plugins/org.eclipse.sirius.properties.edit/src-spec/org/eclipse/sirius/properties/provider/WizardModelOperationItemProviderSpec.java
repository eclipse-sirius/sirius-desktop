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
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.StyledString;
import org.eclipse.sirius.properties.WizardModelOperation;

/**
 * Subclass used to not have to modify the generated code.
 *
 * @author sbegaudeau
 */
public class WizardModelOperationItemProviderSpec extends WizardModelOperationItemProvider {

    /**
     * The constructor.
     *
     * @param adapterFactory
     *            The adapter factory
     */
    public WizardModelOperationItemProviderSpec(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public String getText(Object object) {
        return ((StyledString) getStyledText(object)).getString();
    }

    @Override
    public Object getStyledText(Object object) {
        String label = ((WizardModelOperation) object).getTitleExpression();
        StyledString styledLabel = new StyledString();
        if (label == null || label.length() == 0) {
            styledLabel.append(getString("_UI_WizardModelOperation_type")); //$NON-NLS-1$
        } else {
            styledLabel.append(label);
        }
        return styledLabel;
    }

    @Override
    protected CommandParameter createChildParameter(Object feature, Object child) {
        Utils.addNoopNavigationOperations(child);
        return super.createChildParameter(feature, child);
    }

}
