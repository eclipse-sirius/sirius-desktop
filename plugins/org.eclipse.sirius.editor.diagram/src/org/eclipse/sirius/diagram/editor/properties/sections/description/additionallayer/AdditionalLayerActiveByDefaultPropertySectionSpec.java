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
package org.eclipse.sirius.diagram.editor.properties.sections.description.additionallayer;

import org.eclipse.sirius.diagram.description.AdditionalLayer;

/**
 * A specific implementation of
 * {@link AdditionalLayerActiveByDefaultPropertySection} to deactivate/activate
 * the "Active By Default" checkbox when optional checkbox is unchecked /
 * checked
 * 
 * @author fbarbin
 * 
 */
public class AdditionalLayerActiveByDefaultPropertySectionSpec extends AdditionalLayerActiveByDefaultPropertySection {

    @Override
    protected boolean shouldBeReadOnly() {
        boolean value = super.shouldBeReadOnly();
        if (!value) {
            value = !isOptional();
        }
        return value;
    }

    @Override
    public void refresh() {
        super.refresh();
        updateReadOnlyStatus();
    }

    private boolean isOptional() {
        return ((AdditionalLayer) eObject).isOptional();
    }

}
