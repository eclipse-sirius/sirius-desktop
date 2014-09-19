/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.style.labelalignmentdescription;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.sirius.editor.properties.sections.style.labelstyledescription.LabelStyleDescriptionLabelAlignmentPropertySection;
import org.eclipse.sirius.viewpoint.LabelAlignment;

/**
 * Override
 * org.eclipse.sirius.editor.properties.sections.style.labelstyledescription
 * .LabelStyleDescriptionLabelAlignmentPropertySection.getChoiceOfValues() to
 * change label alignement order in properties view.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 *
 */
public class BasicLabelStyleDescriptionLabelAlignmentPropertySectionSpec extends LabelStyleDescriptionLabelAlignmentPropertySection {

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractRadioButtonPropertySection#getEnumerationFeatureValues()
     */
    @Override
    protected List<?> getChoiceOfValues() {
        // Change the current label alignment order in properties view
        // (Center-Left-Right) to the new ordre (Left-Center-Right).
        List<LabelAlignment> VALUES = Collections.unmodifiableList(Arrays.asList(new LabelAlignment[] { LabelAlignment.LEFT, LabelAlignment.CENTER, LabelAlignment.RIGHT, }));
        return VALUES;
    }

}
