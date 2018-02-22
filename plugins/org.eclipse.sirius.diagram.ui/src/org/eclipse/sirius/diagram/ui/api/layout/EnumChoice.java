/*******************************************************************************
 * Copyright (c) 2018 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.api.layout;

/**
 * 
 * A component defining an Enum choice with its name and a description. It is used to provide Enum choice for an enum
 * option of a layout algorithm in VSM editor.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class EnumChoice {
    /**
     * The name of the enum choice.
     */
    private String name;

    /**
     * The description of the enum choice.
     */
    private String description;

    /**
     * Initialize this Enum choice with given name and description.
     * 
     * @param name
     *            the name of this enum choice.
     * @param description
     *            the description of this enum choice.
     */
    public EnumChoice(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Returns the description of the enum choice.
     * 
     * @return the description of the enum choice.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the name of the enum choice.
     * 
     * @return the name of the enum choice.
     */
    public String getName() {
        return name;
    }

}
