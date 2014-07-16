/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.copier;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.identifier.RepresentationElementIdentifier;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.business.internal.dialect.identifier.DiagramIdentifier;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;

/**
 * An extended copier to handle correctly semantic containment.
 * 
 * @author mchauvin
 */
public class GMFDiagramCopier extends EcoreUtil.Copier {

    private static final long serialVersionUID = 1L;

    private static final int MAGIC_INDEX = 1234;

    private Map<EObject, RepresentationElementIdentifier> originalIdentifiers;

    private Map<EObject, RepresentationElementIdentifier> copyIdentifiers;

    /**
     * Construct a new instance.
     * 
     * @param originalRepresentation
     *            the original representation
     * @param copyRepresentation
     *            the representation copy
     */
    public GMFDiagramCopier(final DDiagram originalRepresentation, final DDiagram copyRepresentation) {
        super();
        createIdentifiers(originalRepresentation, copyRepresentation);
    }

    private void createIdentifiers(final DDiagram originalRepresentation, final DDiagram copyRepresentation) {
        originalIdentifiers = new HashMap<EObject, RepresentationElementIdentifier>();
        final DiagramIdentifier originalDi = (DiagramIdentifier) DialectManager.INSTANCE.createIdentifier(originalRepresentation, originalIdentifiers);
        originalDi.setIndex(MAGIC_INDEX);
        createIdentifiers(originalRepresentation, originalIdentifiers);

        copyIdentifiers = new HashMap<EObject, RepresentationElementIdentifier>();
        final DiagramIdentifier copyDi = (DiagramIdentifier) DialectManager.INSTANCE.createIdentifier(copyRepresentation, copyIdentifiers);
        copyDi.setIndex(MAGIC_INDEX);
        createIdentifiers(copyRepresentation, copyIdentifiers);

        for (final DRepresentationElement element : copyRepresentation.getRepresentationElements()) {
            DialectManager.INSTANCE.createIdentifier(element, copyIdentifiers);
        }
    }

    private void createIdentifiers(final DRepresentation representation, final Map<EObject, RepresentationElementIdentifier> identifiers) {
        for (final DRepresentationElement element : representation.getRepresentationElements()) {
            DialectManager.INSTANCE.createIdentifier(element, identifiers);
            if (element instanceof DNode) {
                final NodeStyle style = ((DNode) element).getOwnedStyle();
                if (style != null) {
                    DialectManager.INSTANCE.createIdentifier(style, identifiers);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.util.EcoreUtil.Copier#copyReference(org.eclipse.emf.ecore.EReference,
     *      org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
     */
    @Override
    protected void copyReference(final EReference eReference, final EObject eObject, final EObject copyEObject) {
        if (eObject instanceof View && eReference == NotationPackage.eINSTANCE.getView_Element()) {
            final View copyView = (View) copyEObject;
            final EObject originalElement = (EObject) eObject.eGet(eReference);
            if (originalElement != null) {
                /* get identifier from the original element */
                final RepresentationElementIdentifier originalIdentifier = originalIdentifiers.get(originalElement);

                for (final Map.Entry<EObject, RepresentationElementIdentifier> entry : copyIdentifiers.entrySet()) {
                    final RepresentationElementIdentifier copyIdentifier = entry.getValue();
                    if (originalIdentifier.equals(copyIdentifier)) {
                        final View originalView = (View) eObject;
                        /* handle unsettable property */
                        if (originalView.isSetElement()) {
                            copyView.setElement(entry.getKey());
                        }
                        return;
                    }
                }
            }
        }
        super.copyReference(eReference, eObject, copyEObject);
    }
}
