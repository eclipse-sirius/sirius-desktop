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
package org.eclipse.sirius.diagram.editor.properties.sections.style.hidelabelcapabilitystyledescription;

import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;

/**
 * A specialization of
 * {@link NodeStyleDescriptionHideLabelByDefaultPropertySection} to disabled
 * this field when labelPosition is not equal to border.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * 
 */
public class HideLabelCapabilityStyleDescriptionHideLabelByDefaultPropertySectionSpec extends HideLabelCapabilityStyleDescriptionHideLabelByDefaultPropertySection {

    @Override
    public void refresh() {
        super.refresh();
        // Also refresh the readOnly state
        updateReadOnlyStatus();
    }

    @Override
    protected boolean shouldBeReadOnly() {
        boolean result = super.shouldBeReadOnly();
        // Check if the LabelPosition is border
        if (eObject instanceof NodeStyleDescription) {
            result = result || !LabelPosition.BORDER_LITERAL.equals(((NodeStyleDescription) eObject).getLabelPosition());
        }
        return result;
    }

    @Override
    protected void makeReadonly() {
        super.makeReadonly();
    }

    @Override
    protected void makeWrittable() {
        super.makeWrittable();
    }
}
