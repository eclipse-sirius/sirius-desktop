/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.section.description.containermapping;

import java.util.Collections;
import java.util.List;

import org.eclipse.sirius.editor.properties.sections.description.containermapping.ContainerMappingChildrenPresentationPropertySection;
import org.eclipse.sirius.viewpoint.ContainerLayout;

import com.google.common.collect.Lists;

/**
 * A section for the childrenPresentation property of a ContainerMapping object.
 * 
 * It expose only the FreeForm and List capabilities but not the experimental
 * region support.
 */
public class ContainerMappingChildrenPresentationPropertySectionSpec extends ContainerMappingChildrenPresentationPropertySection {

    private final List<?> values = Collections.unmodifiableList(Lists.newArrayList(ContainerLayout.FREE_FORM, ContainerLayout.LIST));

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractRadioButtonPropertySection#getEnumerationFeatureValues()
     */
    protected List<?> getChoiceOfValues() {
        return values;
    }
}
