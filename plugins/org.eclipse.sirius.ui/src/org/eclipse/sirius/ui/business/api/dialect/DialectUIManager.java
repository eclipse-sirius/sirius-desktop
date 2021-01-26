/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.business.api.dialect;

import org.eclipse.sirius.ui.business.internal.dialect.DialectUIManagerImpl;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * Instance managing the dialects.
 * 
 * @author cbrun
 * 
 */
public interface DialectUIManager extends DialectUIServices {
    /**
     * Singleton instance of the dialect manager.
     */
    DialectUIManager INSTANCE = DialectUIManagerImpl.init();

    /**
     * Dialect manager extension point ID.
     */
    String ID = "org.eclipse.sirius.ui.dialectui"; //$NON-NLS-1$

    /**
     * Extension point attribute for the dialect class.
     */
    String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /**
     * Enable a new dialect.
     * 
     * @param dialect
     *            dialect to enable.
     */
    void enableDialectUI(DialectUI dialect);

    /**
     * Disable a dialect. If it was not enabled : do nothing.
     * 
     * @param dialect
     *            dialect to disable.
     */
    void disableDialectUI(DialectUI dialect);

    /**
     * Return true if at least one dialect is able to export this <code>representation</code> to this
     * <code>format</code>, false otherwise.
     * 
     * @param representation
     *            The representation to export.
     * @param format
     *            The desired format.
     * @return true if at least one dialect is able to export this <code>representation </code> to this
     *         <code>format</code>, false otherwise
     */
    boolean canExport(DRepresentation representation, ExportFormat format);

    /**
     * Return true if at least one dialect is able to export the <code>representation</code> associated to the
     * <code>repDescriptor</code> to this <code>format</code>, false otherwise.
     * 
     * @param repDescriptor
     *            Represents the representation to export.
     * @param format
     *            The desired format.
     * @return true if at least one dialect is able to export this <code>representation </code> to this
     *         <code>format</code>, false otherwise
     */
    boolean canExport(DRepresentationDescriptor repDescriptor, ExportFormat format);
}
