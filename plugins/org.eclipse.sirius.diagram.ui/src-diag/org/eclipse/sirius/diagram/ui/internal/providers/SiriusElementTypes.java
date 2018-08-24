/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.providers;

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
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.swt.graphics.Image;

/**
 * @not-generated now that we have a new type, the dimension edge type
 */
public class SiriusElementTypes {

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
                return DiagramUIPlugin.getPlugin().getItemImageDescriptor(eClass.getEPackage().getEFactoryInstance().create(eClass));
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
            IdentityHashMap<IElementType, EClass> tmp = new IdentityHashMap<IElementType, EClass>();
            tmp.put(DDiagram_1000, DiagramPackage.eINSTANCE.getDDiagram());
            tmp.put(DNode_2001, DiagramPackage.eINSTANCE.getDNode());
            tmp.put(DNodeContainer_2002, DiagramPackage.eINSTANCE.getDNodeContainer());
            tmp.put(DNodeList_2003, DiagramPackage.eINSTANCE.getDNodeList());
            tmp.put(DNode_3001, DiagramPackage.eINSTANCE.getDNode());
            tmp.put(BundledImage_3004, DiagramPackage.eINSTANCE.getBundledImage());
            tmp.put(Dot_3002, DiagramPackage.eINSTANCE.getDot());
            tmp.put(GaugeCompositeStyle_3006, DiagramPackage.eINSTANCE.getGaugeCompositeStyle());
            tmp.put(Square_3003, DiagramPackage.eINSTANCE.getSquare());
            tmp.put(Ellipse_3016, DiagramPackage.eINSTANCE.getEllipse());
            tmp.put(Lozenge_3017, DiagramPackage.eINSTANCE.getLozenge());
            tmp.put(WorkspaceImage_3005, DiagramPackage.eINSTANCE.getWorkspaceImage());
            tmp.put(Note_3013, DiagramPackage.eINSTANCE.getNote());
            tmp.put(CustomStyle_3014, DiagramPackage.eINSTANCE.getCustomStyle());
            tmp.put(DNode_3007, DiagramPackage.eINSTANCE.getDNode());
            tmp.put(DNodeContainer_3008, DiagramPackage.eINSTANCE.getDNodeContainer());
            tmp.put(DNodeList_3009, DiagramPackage.eINSTANCE.getDNodeList());
            tmp.put(DNodeListElement_3010, DiagramPackage.eINSTANCE.getDNodeListElement());
            tmp.put(DNode_3012, DiagramPackage.eINSTANCE.getDNode());
            tmp.put(DEdge_4001, DiagramPackage.eINSTANCE.getDEdge());
            elements = tmp;
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
            HashSet<IElementType> tmp = new HashSet<IElementType>();
            tmp.add(DDiagram_1000);
            tmp.add(DNode_2001);
            tmp.add(DNodeContainer_2002);
            tmp.add(DNodeList_2003);
            tmp.add(DNode_3001);
            tmp.add(BundledImage_3004);
            tmp.add(Dot_3002);
            tmp.add(GaugeCompositeStyle_3006);
            tmp.add(Square_3003);
            tmp.add(Ellipse_3016);
            tmp.add(Lozenge_3017);
            tmp.add(WorkspaceImage_3005);
            tmp.add(Note_3013);
            tmp.add(CustomStyle_3014);
            tmp.add(DNode_3007);
            tmp.add(DNodeContainer_3008);
            tmp.add(DNodeList_3009);
            tmp.add(DNodeListElement_3010);
            tmp.add(DNode_3012);
            tmp.add(DEdge_4001);
            tmp.add(BracketEdge_4002);
            KNOWN_ELEMENT_TYPES = tmp;
        }
        return KNOWN_ELEMENT_TYPES.contains(elementType);
    }

}
