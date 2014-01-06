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
package org.eclipse.sirius.diagram.internal.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditorPlugin;
import org.eclipse.swt.graphics.Image;

/**
 * @not-generated now that we have a new type, the dimension edge type
 */
public class SiriusElementTypes extends ElementInitializers {

    /**
     * @was-generated
     */
    private SiriusElementTypes() {
    }

    /**
     * @not-generated
     */
    private static Map<IElementType, EClass> elements;

    /**
     * @was-generated
     */
    private static ImageRegistry imageRegistry;

    /**
     * @not-generated
     */
    private static Set<IElementType> KNOWN_ELEMENT_TYPES;

    /**
     * @was-generated
     */
    public static final IElementType DDiagram_1000 = getElementType("org.eclipse.sirius.diagram.DDiagram_1000"); //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final IElementType DNode_2001 = getElementType("org.eclipse.sirius.diagram.DNode_2001"); //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final IElementType DNodeContainer_2002 = getElementType("org.eclipse.sirius.diagram.DNodeContainer_2002"); //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final IElementType DNodeList_2003 = getElementType("org.eclipse.sirius.diagram.DNodeList_2003"); //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final IElementType DNode_3001 = getElementType("org.eclipse.sirius.diagram.DNode_3001"); //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final IElementType Dot_3002 = getElementType("org.eclipse.sirius.diagram.Dot_3002"); //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final IElementType Square_3003 = getElementType("org.eclipse.sirius.diagram.Square_3003"); //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final IElementType Ellipse_3016 = getElementType("org.eclipse.sirius.diagram.Ellipse_3016"); //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final IElementType Lozenge_3017 = getElementType("org.eclipse.sirius.diagram.Lozenge_3017"); //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final IElementType BundledImage_3004 = getElementType("org.eclipse.sirius.diagram.BundledImage_3004"); //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final IElementType WorkspaceImage_3005 = getElementType("org.eclipse.sirius.diagram.WorkspaceImage_3005"); //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final IElementType Note_3013 = getElementType("org.eclipse.sirius.diagram.Note_3013"); //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final IElementType GaugeCompositeStyle_3006 = getElementType("org.eclipse.sirius.diagram.GaugeCompositeStyle_3006"); //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final IElementType CustomStyle_3014 = getElementType("org.eclipse.sirius.diagram.CustomStyle_3014"); //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final IElementType DNode_3007 = getElementType("org.eclipse.sirius.diagram.DNode_3007"); //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final IElementType DNodeContainer_3008 = getElementType("org.eclipse.sirius.diagram.DNodeContainer_3008"); //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final IElementType DNodeList_3009 = getElementType("org.eclipse.sirius.diagram.DNodeList_3009"); //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final IElementType DNodeListElement_3010 = getElementType("org.eclipse.sirius.diagram.DNodeListElement_3010"); //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final IElementType DNode_3012 = getElementType("org.eclipse.sirius.diagram.DNode_3012"); //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static final IElementType DEdge_4001 = getElementType("org.eclipse.sirius.diagram.DEdge_4001"); //$NON-NLS-1$

    /** Type for bracket edge. */
    public static final IElementType BracketEdge_4002 = getElementType("org.eclipse.sirius.diagram.BracketEdge_4002"); //$NON-NLS-1$

    /**
     * @was-generated
     */
    private static ImageRegistry getImageRegistry() {
        if (imageRegistry == null) {
            imageRegistry = new ImageRegistry();
        }
        return imageRegistry;
    }

    /**
     * @was-generated
     */
    private static String getImageRegistryKey(ENamedElement element) {
        return element.getName();
    }

    /**
     * @was-generated
     */
    private static ImageDescriptor getProvidedImageDescriptor(ENamedElement element) {
        if (element instanceof EStructuralFeature) {
            EStructuralFeature feature = ((EStructuralFeature) element);
            EClass eContainingClass = feature.getEContainingClass();
            EClassifier eType = feature.getEType();
            if (eContainingClass != null && !eContainingClass.isAbstract()) {
                element = eContainingClass;
            } else if (eType instanceof EClass && !((EClass) eType).isAbstract()) {
                element = eType;
            }
        }
        if (element instanceof EClass) {
            EClass eClass = (EClass) element;
            if (!eClass.isAbstract()) {
                return SiriusDiagramEditorPlugin.getInstance().getItemImageDescriptor(eClass.getEPackage().getEFactoryInstance().create(eClass));
            }
        }
        // TODO : support structural features
        return null;
    }

    /**
     * @was-generated
     */
    public static ImageDescriptor getImageDescriptor(ENamedElement element) {
        String key = getImageRegistryKey(element);
        ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
        if (imageDescriptor == null) {
            imageDescriptor = getProvidedImageDescriptor(element);
            if (imageDescriptor == null) {
                imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
            }
            getImageRegistry().put(key, imageDescriptor);
        }
        return imageDescriptor;
    }

    /**
     * @was-generated
     */
    public static Image getImage(ENamedElement element) {
        String key = getImageRegistryKey(element);
        Image image = getImageRegistry().get(key);
        if (image == null) {
            ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
            if (imageDescriptor == null) {
                imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
            }
            getImageRegistry().put(key, imageDescriptor);
            image = getImageRegistry().get(key);
        }
        return image;
    }

    /**
     * @was-generated
     */
    public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
        ENamedElement element = getElement(hint);
        if (element == null) {
            return null;
        }
        return getImageDescriptor(element);
    }

    /**
     * @was-generated
     */
    public static Image getImage(IAdaptable hint) {
        ENamedElement element = getElement(hint);
        if (element == null) {
            return null;
        }
        return getImage(element);
    }

    /**
     * Returns 'type' of the ecore object associated with the hint.
     * 
     * @was-generated NOT
     */
    public static ENamedElement getElement(IAdaptable hint) {
        Object type = hint.getAdapter(IElementType.class);
        if (elements == null) {
            elements = new IdentityHashMap<IElementType, EClass>();

            elements.put(DDiagram_1000, DiagramPackage.eINSTANCE.getDDiagram());

            elements.put(DNode_2001, DiagramPackage.eINSTANCE.getDNode());

            elements.put(DNodeContainer_2002, DiagramPackage.eINSTANCE.getDNodeContainer());

            elements.put(DNodeList_2003, DiagramPackage.eINSTANCE.getDNodeList());

            elements.put(DNode_3001, DiagramPackage.eINSTANCE.getDNode());

            elements.put(BundledImage_3004, DiagramPackage.eINSTANCE.getBundledImage());

            elements.put(Dot_3002, DiagramPackage.eINSTANCE.getDot());

            elements.put(GaugeCompositeStyle_3006, DiagramPackage.eINSTANCE.getGaugeCompositeStyle());

            elements.put(Square_3003, DiagramPackage.eINSTANCE.getSquare());

            elements.put(Ellipse_3016, DiagramPackage.eINSTANCE.getEllipse());

            elements.put(Lozenge_3017, DiagramPackage.eINSTANCE.getLozenge());

            elements.put(WorkspaceImage_3005, DiagramPackage.eINSTANCE.getWorkspaceImage());

            elements.put(Note_3013, DiagramPackage.eINSTANCE.getNote());

            elements.put(CustomStyle_3014, DiagramPackage.eINSTANCE.getCustomStyle());

            elements.put(DNode_3007, DiagramPackage.eINSTANCE.getDNode());

            elements.put(DNodeContainer_3008, DiagramPackage.eINSTANCE.getDNodeContainer());

            elements.put(DNodeList_3009, DiagramPackage.eINSTANCE.getDNodeList());

            elements.put(DNodeListElement_3010, DiagramPackage.eINSTANCE.getDNodeListElement());

            elements.put(DNode_3012, DiagramPackage.eINSTANCE.getDNode());

            elements.put(DEdge_4001, DiagramPackage.eINSTANCE.getDEdge());
        }
        return elements.get(type);
    }

    /**
     * @was-generated
     */
    private static IElementType getElementType(String id) {
        return ElementTypeRegistry.getInstance().getType(id);
    }

    /**
     * @was-generated NOT
     */
    public static boolean isKnownElementType(IElementType elementType) {
        if (KNOWN_ELEMENT_TYPES == null) {
            KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
            KNOWN_ELEMENT_TYPES.add(DDiagram_1000);
            KNOWN_ELEMENT_TYPES.add(DNode_2001);
            KNOWN_ELEMENT_TYPES.add(DNodeContainer_2002);
            KNOWN_ELEMENT_TYPES.add(DNodeList_2003);
            KNOWN_ELEMENT_TYPES.add(DNode_3001);
            KNOWN_ELEMENT_TYPES.add(BundledImage_3004);
            KNOWN_ELEMENT_TYPES.add(Dot_3002);
            KNOWN_ELEMENT_TYPES.add(GaugeCompositeStyle_3006);
            KNOWN_ELEMENT_TYPES.add(Square_3003);
            KNOWN_ELEMENT_TYPES.add(Ellipse_3016);
            KNOWN_ELEMENT_TYPES.add(Lozenge_3017);
            KNOWN_ELEMENT_TYPES.add(WorkspaceImage_3005);
            KNOWN_ELEMENT_TYPES.add(Note_3013);
            KNOWN_ELEMENT_TYPES.add(CustomStyle_3014);
            KNOWN_ELEMENT_TYPES.add(DNode_3007);
            KNOWN_ELEMENT_TYPES.add(DNodeContainer_3008);
            KNOWN_ELEMENT_TYPES.add(DNodeList_3009);
            KNOWN_ELEMENT_TYPES.add(DNodeListElement_3010);
            KNOWN_ELEMENT_TYPES.add(DNode_3012);
            KNOWN_ELEMENT_TYPES.add(DEdge_4001);
            KNOWN_ELEMENT_TYPES.add(BracketEdge_4002);
        }
        return KNOWN_ELEMENT_TYPES.contains(elementType);
    }

}
