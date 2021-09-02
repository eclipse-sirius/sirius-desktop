/*******************************************************************************
 * Copyright (c) 2015, 2021 Obeo.
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
package org.eclipse.sirius.model.tools.internal;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtains translated strings.
 * 
 * @author Florian Barbin
 *
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, SiriusModelPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF

    @TranslatableMessage
    public static String PaneBasedSelectionWizardDescriptionImpl_choiceOfValuesMsg;

    @TranslatableMessage
    public static String PaneBasedSelectionWizardDescriptionImpl_selectedValuesMsg;

    @TranslatableMessage
    public static String SelectionWizardDescriptionImpl_title;

    @TranslatableMessage
    public static String ValidationRuleImpl_elementHas;

    // CHECKSTYLE:ON
    private Messages() {
        // Prevents instanciation.
    }
}
