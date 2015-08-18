/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.business.internal.refresh;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.sirius.table.metamodel.table.description.BackgroundStyleDescription;
import org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription;

/**
 * A specific class to group a style ({@link ForegroundStyleDescription} or
 * {@link BackgroundStyleDescription}) and a boolean status to indicate if the
 * style is the default one.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class StyleWithDefaultStatus {

    private EObject style;

    private Boolean isDefaultStyle;

    /**
     * Default constructor.
     * 
     * @param style
     *            the style
     * @param isDefaultStyle
     *            tells if the style is the default one
     */
    public StyleWithDefaultStatus(EObject style, Boolean isDefaultStyle) {
        if (!(style instanceof ForegroundStyleDescription || style instanceof BackgroundStyleDescription)) {
            throw new IllegalArgumentException("The style attribute must be a ForegroundStyleDescription or a BackgroundStyleDescription.");
        }
        this.style = style;
        this.isDefaultStyle = isDefaultStyle;
    }

    /**
     * Get the style.
     * 
     * @return the style
     */
    protected EObject getStyle() {
        return style;
    }

    /**
     * Set the style.
     * 
     * @param style
     *            the style to set
     */
    protected void setStyle(EObject style) {
        this.style = style;
    }

    /**
     * Tells if the style is the default one.
     * 
     * @return the isDefaultStyle
     */
    protected Boolean isDefaultStyle() {
        return isDefaultStyle;
    }

    /**
     * Set the if the style is the default one.
     * 
     * @param isDefaultStyle
     *            the isDefaultStyle to set
     */
    protected void setIsDefaultStyle(Boolean isDefaultStyle) {
        this.isDefaultStyle = isDefaultStyle;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return style.toString() + "--> isDefault=" + isDefaultStyle(); //$NON-NLS-1$
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hashstyle = style != null ? style.hashCode() : 0;
        int hashisDefaultStyle = isDefaultStyle != null ? isDefaultStyle.hashCode() : 0;

        return (hashstyle + hashisDefaultStyle) * hashisDefaultStyle + hashstyle;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(java.lang.Object other) {
        if (other instanceof StyleWithDefaultStatus) {
            StyleWithDefaultStatus otherStyleWithDefaultStatus = (StyleWithDefaultStatus) other;
            return this.style == otherStyleWithDefaultStatus.style
                    || (this.style != null && otherStyleWithDefaultStatus.style != null && this.style.equals(otherStyleWithDefaultStatus.style))
                    && (this.isDefaultStyle == otherStyleWithDefaultStatus.isDefaultStyle || (this.isDefaultStyle != null && otherStyleWithDefaultStatus.isDefaultStyle != null && this.isDefaultStyle
                            .equals(otherStyleWithDefaultStatus.isDefaultStyle)));
        }
        return false;
    }
}
