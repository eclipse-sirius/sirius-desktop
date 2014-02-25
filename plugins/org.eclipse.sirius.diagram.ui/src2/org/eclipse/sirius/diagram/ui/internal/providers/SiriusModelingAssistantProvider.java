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
package org.eclipse.sirius.diagram.ui.internal.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.ModelingAssistantProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode3EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeList2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.part.Messages;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * This code was originally generated.
 * 
 * @author yann.mortier@obeo.fr
 */
public class SiriusModelingAssistantProvider extends ModelingAssistantProvider {
    /**
     * The generated version of getTypesForPopupBar.
     * 
     * @param host
     *            the host.
     * @return the types.
     */
    public List<?> getTypesForPopupBarGen(IAdaptable host) {
        IGraphicalEditPart editPart = (IGraphicalEditPart) host.getAdapter(IGraphicalEditPart.class);
        if (editPart instanceof DNodeEditPart) {
            List<IElementType> types = new ArrayList<IElementType>();
            types.add(SiriusElementTypes.DNode_3001);
            types.add(SiriusElementTypes.Dot_3002);
            types.add(SiriusElementTypes.Square_3003);
            types.add(SiriusElementTypes.Ellipse_3016);
            types.add(SiriusElementTypes.Lozenge_3017);
            types.add(SiriusElementTypes.BundledImage_3004);
            types.add(SiriusElementTypes.Note_3013);
            types.add(SiriusElementTypes.WorkspaceImage_3005);
            types.add(SiriusElementTypes.GaugeCompositeStyle_3006);
            types.add(SiriusElementTypes.CustomStyle_3014);
            return types;
        }
        if (editPart instanceof DNodeContainerEditPart) {
            List<IElementType> types = new ArrayList<IElementType>();
            types.add(SiriusElementTypes.DNode_3012);
            return types;
        }
        if (editPart instanceof DNodeListEditPart) {
            List<IElementType> types = new ArrayList<IElementType>();
            types.add(SiriusElementTypes.DNode_3012);
            types.add(SiriusElementTypes.DNodeListElement_3010);
            return types;
        }
        if (editPart instanceof DNode2EditPart) {
            List<IElementType> types = new ArrayList<IElementType>();
            types.add(SiriusElementTypes.BundledImage_3004);
            types.add(SiriusElementTypes.Dot_3002);
            types.add(SiriusElementTypes.GaugeCompositeStyle_3006);
            types.add(SiriusElementTypes.Square_3003);
            types.add(SiriusElementTypes.Ellipse_3016);
            types.add(SiriusElementTypes.Lozenge_3017);
            types.add(SiriusElementTypes.WorkspaceImage_3005);
            types.add(SiriusElementTypes.DNode_3001);
            return types;
        }
        if (editPart instanceof DNode3EditPart) {
            List<IElementType> types = new ArrayList<IElementType>();
            types.add(SiriusElementTypes.DNode_3001);
            types.add(SiriusElementTypes.Dot_3002);
            types.add(SiriusElementTypes.Square_3003);
            types.add(SiriusElementTypes.Ellipse_3016);
            types.add(SiriusElementTypes.Lozenge_3017);
            types.add(SiriusElementTypes.BundledImage_3004);
            types.add(SiriusElementTypes.Note_3013);
            types.add(SiriusElementTypes.WorkspaceImage_3005);
            types.add(SiriusElementTypes.GaugeCompositeStyle_3006);
            types.add(SiriusElementTypes.CustomStyle_3014);
            return types;
        }
        if (editPart instanceof DNodeContainer2EditPart) {
            List<IElementType> types = new ArrayList<IElementType>();
            types.add(SiriusElementTypes.DNode_3012);
            return types;
        }
        if (editPart instanceof DNodeList2EditPart) {
            List<IElementType> types = new ArrayList<IElementType>();
            types.add(SiriusElementTypes.DNode_3012);
            types.add(SiriusElementTypes.DNodeListElement_3010);
            return types;
        }
        if (editPart instanceof DNode4EditPart) {
            List<IElementType> types = new ArrayList<IElementType>();
            types.add(SiriusElementTypes.BundledImage_3004);
            types.add(SiriusElementTypes.Dot_3002);
            types.add(SiriusElementTypes.GaugeCompositeStyle_3006);
            types.add(SiriusElementTypes.Square_3003);
            types.add(SiriusElementTypes.Ellipse_3016);
            types.add(SiriusElementTypes.Lozenge_3017);
            types.add(SiriusElementTypes.WorkspaceImage_3005);
            types.add(SiriusElementTypes.DNode_3012);
            return types;
        }
        if (editPart instanceof DNodeContainerViewNodeContainerCompartmentEditPart) {
            List<IElementType> types = new ArrayList<IElementType>();
            types.add(SiriusElementTypes.DNode_3007);
            types.add(SiriusElementTypes.DNodeContainer_3008);
            types.add(SiriusElementTypes.DNodeList_3009);
            return types;
        }
        if (editPart instanceof DNodeContainerViewNodeContainerCompartment2EditPart) {
            List<IElementType> types = new ArrayList<IElementType>();
            types.add(SiriusElementTypes.DNode_3007);
            types.add(SiriusElementTypes.DNodeContainer_3008);
            types.add(SiriusElementTypes.DNodeList_3009);
            return types;
        }
        if (editPart instanceof DDiagramEditPart) {
            List<IElementType> types = new ArrayList<IElementType>();
            types.add(SiriusElementTypes.DNode_2001);
            types.add(SiriusElementTypes.DNodeContainer_2002);
            types.add(SiriusElementTypes.DNodeList_2003);
            return types;
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * @was-generated NOT : remove all Types.
     */
    public List<?> getTypesForPopupBar(IAdaptable host) {
        // LGO : "editPart = null" had been added to remove all types. putting
        // this
        // in a "*Gen" method to avoid too many redundant checks (if executed
        // too
        // many times, it can hinder performances (though so little ;))
        return Collections.EMPTY_LIST;
    }

    public List<?> getRelTypesOnSource(IAdaptable source) {
        return Collections.EMPTY_LIST;
    }

    public List<?> getRelTypesOnTarget(IAdaptable target) {
        return Collections.EMPTY_LIST;
    }

    public List<?> getRelTypesOnSourceAndTarget(IAdaptable source, IAdaptable target) {
        return Collections.EMPTY_LIST;
    }

    public List<?> getTypesForSource(IAdaptable target, IElementType relationshipType) {
        return Collections.EMPTY_LIST;
    }

    public List<?> getTypesForTarget(IAdaptable source, IElementType relationshipType) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @was-generated
     */
    public EObject selectExistingElementForSource(IAdaptable target, IElementType relationshipType) {
        return selectExistingElement(target, getTypesForSource(target, relationshipType));
    }

    /**
     * @was-generated
     */
    public EObject selectExistingElementForTarget(IAdaptable source, IElementType relationshipType) {
        return selectExistingElement(source, getTypesForTarget(source, relationshipType));
    }

    /**
     * @was-generated
     */
    protected EObject selectExistingElement(IAdaptable host, Collection<?> types) {
        if (types.isEmpty()) {
            return null;
        }
        IGraphicalEditPart editPart = (IGraphicalEditPart) host.getAdapter(IGraphicalEditPart.class);
        if (editPart == null) {
            return null;
        }
        Diagram diagram = (Diagram) editPart.getRoot().getContents().getModel();
        Collection<EObject> elements = new HashSet<EObject>();
        for (Iterator<EObject> it = diagram.getElement().eAllContents(); it.hasNext();) {
            EObject element = it.next();
            if (isApplicableElement(element, types)) {
                elements.add(element);
            }
        }
        if (elements.isEmpty()) {
            return null;
        }
        return selectElement(elements.toArray(new EObject[elements.size()]));
    }

    /**
     * @was-generated
     */
    protected boolean isApplicableElement(EObject element, Collection<?> types) {
        IElementType type = ElementTypeRegistry.getInstance().getElementType(element);
        return types.contains(type);
    }

    /**
     * @was-generated
     */
    protected EObject selectElement(EObject[] elements) {
        Shell shell = Display.getCurrent().getActiveShell();
        ILabelProvider labelProvider = new AdapterFactoryLabelProvider(DiagramUIPlugin.getPlugin().getItemProvidersAdapterFactory());
        ElementListSelectionDialog dialog = new ElementListSelectionDialog(shell, labelProvider);
        dialog.setMessage(Messages.SiriusModelingAssistantProviderMessage);
        dialog.setTitle(Messages.SiriusModelingAssistantProviderTitle);
        dialog.setMultipleSelection(false);
        dialog.setElements(elements);
        EObject selected = null;
        if (dialog.open() == Window.OK) {
            selected = (EObject) dialog.getFirstResult();
        }
        return selected;
    }
}
