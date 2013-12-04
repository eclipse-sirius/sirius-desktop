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
package org.eclipse.sirius.common.tools.api.editing;

/**
 * Describes a extension as contributed to the
 * "org.eclipse.sirius.common.editingDomainFactory" extension point. Use
 * StandaloneEditingDomainFactoryDescriptor to contribute programmatically to
 * the EditingDomainFactoryRegistry or a
 * "org.eclipse.sirius.common.editingDomainFactory" extension
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 * 
 * @since 0.9.0
 */
public interface EditingDomainFactoryDescriptor {

    /**
     * Name of the editingDomainFactory extension point's tag "extension"
     * attribute.
     */
    String EDITING_DOMAIN_FACTORY_CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /**
     * The unique identifier of the extension {@link IEditingDomainFactory}
     * extension.
     * 
     * @return the unique identifier of the extension
     *         {@link IEditingDomainFactory} extension
     */
    String getId();

    /**
     * The unique identifier of the extension {@link IEditingDomainFactory}
     * extension to override or null if no extension to override.
     * 
     * @return the unique identifier of the extension
     *         {@link IEditingDomainFactory} extension to override or null if no
     *         extension to override
     */
    String getOverrideValue();

    /**
     * The concrete implementation (i.e. IEditingDomainFactory) of the
     * extension.
     * 
     * @return the concrete implementation (i.e. IEditingDomainFactory) of the
     *         extension
     */
    IEditingDomainFactory getEditingDomainFactory();

}
