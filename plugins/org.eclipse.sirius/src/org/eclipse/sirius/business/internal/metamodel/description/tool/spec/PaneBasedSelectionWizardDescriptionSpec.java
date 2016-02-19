/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.metamodel.description.tool.spec;

import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.viewpoint.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl;

/**
 * Customization of {@link PaneBasedSelectionWizardDescriptionImpl}.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class PaneBasedSelectionWizardDescriptionSpec extends PaneBasedSelectionWizardDescriptionImpl {

    @Override
    public String getWindowTitle() {
        return MessageTranslator.INSTANCE.getMessage(super.getWindowTitle());
    }

    @Override
    public String getMessage() {
        return MessageTranslator.INSTANCE.getMessage(super.getMessage());
    }

    @Override
    public String getChoiceOfValuesMessage() {
        return MessageTranslator.INSTANCE.getMessage(super.getChoiceOfValuesMessage());
    }

    @Override
    public String getSelectedValuesMessage() {
        return MessageTranslator.INSTANCE.getMessage(super.getSelectedValuesMessage());
    }
}
