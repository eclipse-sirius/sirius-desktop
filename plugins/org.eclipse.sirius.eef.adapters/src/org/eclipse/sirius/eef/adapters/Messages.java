/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.eef.adapters;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtains translated strings.
 * 
 * @author pcdavid
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, EEFAdapterPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF
    @TranslatableMessage
    public static String OpenPropertiesWizardAction_errorOpeningWizard;

    @TranslatableMessage
    public static String SiriusFilteringSectionDescriptor_missingContributorIdInSectionFilter;

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instanciation.
    }
}
