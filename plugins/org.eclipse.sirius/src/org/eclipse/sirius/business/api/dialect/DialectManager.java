/*******************************************************************************
 * Copyright (c) 2007, 2022 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.business.api.dialect;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.dialect.DialectManagerImpl;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;

/**
 * Instance managing the dialects. </br>
 * This interface must not be implemented by clients.
 * 
 * @author cbrun
 * 
 */
public interface DialectManager extends DialectServices {
    /**
     * Singleton instance of the dialect manager.
     */
    DialectManager INSTANCE = DialectManagerImpl.init();

    /**
     * Dialect manager extension point ID.
     */
    String ID = "org.eclipse.sirius.dialect"; //$NON-NLS-1$

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
    void enableDialect(Dialect dialect);

    /**
     * Disable a dialect. If it was not enabled : do nothing.
     * 
     * @param dialect
     *            dialect to disable.
     */
    void disableDialect(Dialect dialect);

    /**
     * Return the representation descriptors, in the given session, whose target is the given {@code semantic}.</br>
     * 
     * @param semantic
     *            targeted semantic element.
     * @param session
     *            the current session.
     * @return the list of representation descriptors.
     */
    Collection<DRepresentationDescriptor> getRepresentationDescriptors(EObject semantic, Session session);

    /**
     * Return all the representations of the given session.</br>
     * 
     * @param session
     *            the current session.
     * @return the list of representation descriptors.
     */
    Collection<DRepresentationDescriptor> getAllRepresentationDescriptors(Session session);

    /**
     * Return the representations, of the given session, from a description.
     * 
     * @param representationDescription
     *            the representation description instance
     * @param session
     *            the current session.
     * @return the list of representation descriptors.
     */
    Collection<DRepresentationDescriptor> getRepresentationDescriptors(RepresentationDescription representationDescription, Session session);

    /**
     * Return the representations, of the given session, whose target is the given {@code semantic}.</br>
     * This methods will load all the targeted representations.
     * 
     * @param semantic
     *            targeted semantic element.
     * @param session
     *            the current session.
     * @return the list of representations.
     */
    Collection<DRepresentation> getRepresentations(EObject semantic, Session session);

    /**
     * Return the representations, of the given session, whose target is the given {@code semantic}. If {@code semantic}
     * is null, this methods acts as {@code getAllLoadedRepresentations(Session)}</br>
     * This methods will return ONLY the representations that are loaded.
     * 
     * @param semantic
     *            targeted semantic element.
     * @param session
     *            the current session.
     * @return the non null list of representations.
     */
    Collection<DRepresentation> getLoadedRepresentations(EObject semantic, Session session);

    /**
     * Return all the representations of the given session.</br>
     * This methods will load all the representations.
     * 
     * @param session
     *            the current session.
     * @return the list of representations.
     */
    Collection<DRepresentation> getAllRepresentations(Session session);

    /**
     * Return all the representations of the given session.</br>
     * This methods will return ONLY the representations that are loaded.
     * 
     * @param session
     *            the current session.
     * @return the non null list of representations.
     */
    Collection<DRepresentation> getAllLoadedRepresentations(Session session);

    /**
     * Return the representations, of the given session, from a description.
     * 
     * @param representationDescription
     *            the representation description instance
     * @param session
     *            the current session.
     * @return the list of representations.
     */
    Collection<DRepresentation> getRepresentations(RepresentationDescription representationDescription, Session session);
}
