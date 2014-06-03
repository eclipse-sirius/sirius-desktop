/*******************************************************************************
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.menu.child;

import org.eclipse.sirius.editor.tools.api.menu.AbstractTypeRestrictingMenuBuilder;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * The conditional styles menu.
 * 
 * @author cbrun
 * 
 */
public class ConditionalStyleMenuBuilder extends AbstractTypeRestrictingMenuBuilder {
    /**
     * build the menu.
     */
    public ConditionalStyleMenuBuilder() {
        super();
        addValidType(DescriptionPackage.eINSTANCE.getConditionalStyleDescription());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLabel() {
        return "New Conditional Style";
    }

}
