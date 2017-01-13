/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.internal;

import java.util.Collection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Provides contextual information to clients about where a given element exists
 * in terms of Sirius elements. Depending on the "input" element this context
 * refers to, any of the context fields may be absent.
 * 
 * @author pcdavid
 * @since 4.0
 */
public final class SiriusContext {
    /**
     * The original element from which the context information was computed.
     */
    private Object input;

    /**
     * The Sirius Session of which the input is part of, if it could be
     * determined unambiguously.
     */
    private final Session session;

    /**
     * The Sirius representation of which the input is part of, if it could be
     * determined unambiguously.
     */
    private final DRepresentation representation;

    /**
     * The DSemanticDecorator associated witht the input, which only makes sense
     * if the input element itself is a DSemanticDecorator.
     */
    private final DSemanticDecorator semanticDecorator;

    /**
     * The main semantic element associated with the input, if it could be
     * determined unambiguously.
     */
    private final EObject mainSemanticElement;

    private SiriusContext(Object input, Session session, DRepresentation representation, DSemanticDecorator semanticDecorator, EObject mainSemanticElement) {
        this.input = input;
        this.session = session;
        this.representation = representation;
        this.semanticDecorator = semanticDecorator;
        this.mainSemanticElement = mainSemanticElement;
    }

    /**
     * Static factory method.
     * 
     * @param input
     *            the input element.
     * @return the computed context.
     */
    public static SiriusContext from(Object input) {
        SiriusContext result = new SiriusContext(input, null, null, null, null);
        if (input instanceof Session) {
            result = fromSession((Session) input);
        } else if (input instanceof DRepresentation) {
            result = fromDRepresentation((DRepresentation) input);
        } else if (input instanceof DRepresentationElement) {
            result = fromDRepresentationElement((DRepresentationElement) input);
        } else if (input instanceof EObject) {
            result = fromUnknownEObject((EObject) input);
        } else if (input instanceof IAdaptable) {
            Object adapted = ((IAdaptable) input).getAdapter(EObject.class);
            if (adapted instanceof EObject) {
                result = from(adapted);
                // Remember the original unadapted input.
                result.input = input;
            }
        }
        return result;
    }

    /**
     * From a session as a starting point, we can not deduce anything more
     * precise.
     */
    private static SiriusContext fromSession(Session session) {
        return new SiriusContext(session, session, null, null, null);
    }

    /**
     * From a DRepresentation, we can deduce the containing Session (if we are
     * inside an active one). Some representations (in practice, all current
     * cases) are also DSemanticDecorator and thus have an associated semantic
     * element.
     */
    private static SiriusContext fromDRepresentation(DRepresentation repr) {
        Session session = new EObjectQuery(repr).getSession();
        DSemanticDecorator decorator = null;
        if (repr instanceof DSemanticDecorator) {
            decorator = (DSemanticDecorator) repr;
        }
        EObject mainSemanticElement = null;
        if (decorator != null) {
            mainSemanticElement = decorator.getTarget();
        }
        return new SiriusContext(repr, session, repr, decorator, mainSemanticElement);
    }

    /**
     * From a DRepresentationElement we can deduce everything, both "up" to the
     * enclosing session and "down" to the associated semantic elements.
     */
    private static SiriusContext fromDRepresentationElement(DRepresentationElement dre) {
        Session session = new EObjectQuery(dre).getSession();
        Option<DRepresentation> repr = new EObjectQuery(dre).getRepresentation();
        return new SiriusContext(dre, session, repr.get(), dre, dre.getTarget());
    }

    /**
     * From an unkown EObject, we van only deduce the session (if it is part of
     * one), and maybe the semantic element of the object itself is inside one
     * of the session's semantic resources. This should only be called as a last
     * resort, if all other specific types of input about which we can know more
     * have been exhausted.
     */
    private static SiriusContext fromUnknownEObject(EObject obj) {
        Session session = SessionManager.INSTANCE.getSession(obj);
        // Session.getSemanticResources() only returns non-controlled resources,
        // so we need to identify the top-level Resource containing the element.
        Resource res = EcoreUtil.getRootContainer(obj).eResource();
        EObject semanticElement = null;
        if (session != null && res != null && session.getSemanticResources().contains(res)) {
            semanticElement = obj;
        }
        return new SiriusContext(obj, session, null, null, semanticElement);
    }

    /**
     * The original input element for which this context was created.
     * 
     * @return the original input element.
     */
    public Object getInput() {
        return input;
    }

    /**
     * Returns the session to which the input element is associated. May be
     * absent if the input element does not exist in the context of a Sirius
     * session.
     * 
     * @return the session to which the input element is associated.
     */
    public Option<Session> getSession() {
        return Options.fromNullable(session);
    }

    /**
     * Returns the Sirius representation to which the input element is
     * associated. May be absent if the input element does not exist in the
     * context of a single Sirius representation. Note for that semantic
     * elements, even if they are currently represented on only one
     * representation in their session, are not considered associated to it.
     * 
     * @return the Sirius representation to which the input element is
     *         associated.
     */
    public Option<DRepresentation> getDRepresentation() {
        return Options.fromNullable(representation);
    }

    /**
     * Returns the {@link DSemanticDecorator} associated to the input, which is
     * only available if the input is itself a {@link DSemanticDecorator}.
     * 
     * @return the {@link DSemanticDecorator} associated to the input.
     */
    public DSemanticDecorator getSemanticDecorator() {
        return semanticDecorator;
    }

    /**
     * Returns the main semantic element associated to the input. May be absent.
     * 
     * @return the main semantic element associated to the input.
     */
    public Option<EObject> getMainSemanticElement() {
        if (semanticDecorator != null) {
            return Options.newSome(semanticDecorator.getTarget());
        } else {
            return Options.newSome(mainSemanticElement);
        }
    }

    /**
     * Returns the additional semantic elements associated to the input. May be
     * absent.
     * 
     * @return the additional semantic elements associated to the input.
     */
    public Option<Collection<EObject>> getAdditionalSemanticElements() {
        if (semanticDecorator instanceof DRepresentationElement) {
            return Options.newSome((Collection<EObject>) ((DRepresentationElement) semanticDecorator).getSemanticElements());
        } else {
            return Options.newNone();
        }
    }
}
