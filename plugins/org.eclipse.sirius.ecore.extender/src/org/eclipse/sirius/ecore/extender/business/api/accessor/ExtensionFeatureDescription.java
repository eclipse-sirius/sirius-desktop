/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.extender.business.api.accessor;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;

/**
 * The most basic description of an extending feature contributed by the
 * extenders. This class should be subclass.
 * 
 * @author cbrun
 */
public class ExtensionFeatureDescription {

    private boolean isReference;

    private boolean isContainment;

    private String name;

    private String typeName;

    /**
     * Create a new feature description.
     * 
     * @param name
     *            name of the feature.
     * @param isReference
     *            true if the feature is a reference.
     * @param isContainment
     *            true if the feature is a containment reference.
     * @param targetTypeName
     *            the feature type name.
     */
    public ExtensionFeatureDescription(final String name, final boolean isReference, final boolean isContainment, final String targetTypeName) {
        this.isReference = isReference;
        this.isContainment = isContainment;
        this.name = name;
        this.typeName = targetTypeName;
    }

    /**
     * return true if the extension is an attribute.
     * 
     * @return true if the extension is an attribute.
     */
    public boolean isAttribute() {
        return !isReference;
    }

    /**
     * return true if the extension feature is a reference.
     * 
     * @return true if the extension feature is a reference.
     */
    public boolean isReference() {
        return isReference;
    }

    /**
     * return the extension feature name.
     * 
     * @return the extension feature name.
     */
    public String getName() {
        return name;
    }

    /**
     * return true if the feature is a contained reference.
     * 
     * @return true if the feature is a contained reference.
     */
    public boolean isContainment() {
        if (isReference()) {
            return isContainment;
        }
        return false;
    }

    /**
     * return the target type name.
     * 
     * @return the target type name.
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        //
        // The hashCode method can return the same value for two objects even if
        // these two objects are not equal.
        // a1.equals(a2) doesn't imply a1.hashCode == a2.hashCode.
        // So, we return the hashCode of name for performance reasons.
        return name.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        //
        // Compare all attributes.
        if (obj instanceof ExtensionFeatureDescription) {
            final ExtensionFeatureDescription extFeatureDesc = (ExtensionFeatureDescription) obj;
            boolean equals = this.isContainment == extFeatureDesc.isContainment && this.isReference == extFeatureDesc.isReference;
            if (equals) {
                equals = this.name.equals(extFeatureDesc.name);
                if (equals) {
                    equals = (this.typeName == null && extFeatureDesc.typeName == null) || (this.typeName.equals(extFeatureDesc.getTypeName()));
                    return equals;
                }
            }
        }
        return false;
    }

    /**
     * Returns the property descriptor. The result may be <code>null</code>.
     * 
     * @param instance
     *            the instance.
     * @return the property descriptor.
     */
    public IItemPropertyDescriptor getPropertyDescriptor(final Object instance) {
        return null;
    }
}
