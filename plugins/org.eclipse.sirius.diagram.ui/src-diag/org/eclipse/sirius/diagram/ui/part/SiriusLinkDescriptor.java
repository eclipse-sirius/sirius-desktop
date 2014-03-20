/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.part;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @was-generated
 */
public class SiriusLinkDescriptor extends SiriusNodeDescriptor {

    /**
     * @was-generated
     */
    private EObject mySource;

    /**
     * @was-generated
     */
    private EObject myDestination;

    /**
     * @was-generated
     */
    private IAdaptable mySemanticAdapter;

    /**
     * @was-generated
     */
    private SiriusLinkDescriptor(EObject source, EObject destination, EObject linkElement, int linkVID) {
        super(linkElement, linkVID);
        mySource = source;
        myDestination = destination;
    }

    /**
     * @was-generated
     */
    public SiriusLinkDescriptor(EObject source, EObject destination, IElementType elementType, int linkVID) {
        this(source, destination, (EObject) null, linkVID);
        final IElementType elementTypeCopy = elementType;
        mySemanticAdapter = new IAdaptable() {
            public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
                if (IElementType.class.equals(adapter)) {
                    return elementTypeCopy;
                }
                return null;
            }
        };
    }

    /**
     * @was-generated
     */
    public SiriusLinkDescriptor(EObject source, EObject destination, EObject linkElement, IElementType elementType, int linkVID) {
        this(source, destination, linkElement, linkVID);
        final IElementType elementTypeCopy = elementType;
        mySemanticAdapter = new EObjectAdapter(linkElement) {
            public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
                if (IElementType.class.equals(adapter)) {
                    return elementTypeCopy;
                }
                return super.getAdapter(adapter);
            }
        };
    }

    /**
     * @was-generated
     */
    public EObject getSource() {
        return mySource;
    }

    /**
     * @was-generated
     */
    public EObject getDestination() {
        return myDestination;
    }

    /**
     * @was-generated
     */
    public IAdaptable getSemanticAdapter() {
        return mySemanticAdapter;
    }

}
