/*******************************************************************************
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.editor.properties.sections.style.basiclabelstyledescription;

/**
 * An overridden {@link BasicLabelStyleDescriptionLabelSizePropertySection} to
 * change the minimal value of the label size.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 * 
 */
public class BasicLabelStyleDescriptionLabelSizePropertySectionSpec extends BasicLabelStyleDescriptionLabelSizePropertySection {

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
