/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.editor.properties.sections.description.spec.foregroundstyledescription;

import org.eclipse.sirius.table.editor.properties.sections.description.foregroundstyledescription.ForegroundStyleDescriptionLabelSizePropertySection;

/**
 * An overridden {@link ForegroundStyleDescriptionLabelSizePropertySection} to
 * change the minimal value of the label size.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class ForegroundStyleDescriptionLabelSizePropertySectionSpec extends ForegroundStyleDescriptionLabelSizePropertySection {

    /**
     * Minimum value of the field. The label size is not allowed to be 0
     * anymore.
     */
    protected int minimumValue = 1;

    @Override
    public int getMinimumValue() {
        return minimumValue;
    }

}
