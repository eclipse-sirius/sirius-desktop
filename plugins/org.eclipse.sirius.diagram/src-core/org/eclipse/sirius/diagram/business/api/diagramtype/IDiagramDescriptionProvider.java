/*******************************************************************************
 * Copyright (c) 2009, 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.api.diagramtype;

import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Provides a new Diagram Type that can be managed by the diagram dialect.
 * 
 * @author ymortier
 */
public interface IDiagramDescriptionProvider {

    /**
     * Returns a new diagram description of this diagram type.
     * 
     * @return a new diagram description of this diagram type.
     */
    DiagramDescription createDiagramDescription();

    /**
     * Return command parameters representing mapping types specific to this
     * diagram type which should be integrated in the Layers menus.
     * 
     * @return command parameters to integrate in the Layers menus.
     */
    Collection<? extends CommandParameter> collectMappingsCommands();

    /**
     * Return command parameters to integrate in the ToolSection type.
     * 
     * @param context
     *            the context in which the tools will appear.
     * 
     * @return command parameters to integrate in the ToolSection type.
     */
    Collection<? extends CommandParameter> collectToolCommands(EObject context);

    /**
     * Return the adapter factory specific for the given diagram type.
     * 
     * @return the adapter factory specific for the given diagram type.
     */
    AdapterFactory getAdapterFactory();

    /**
     * Creates an {@link IInterpretedExpressionTargetSwitch} that will query
     * representation descriptions to determine useful informations (like the
     * type to consider for Interpreted expressions).
     * 
     * @param feature
     *            the feature to consider (for example
     *            {@link org.eclipse.sirius.viewpoint.description.DescriptionPackage#eINSTANCE
     *            #getDiagramElementMapping_SemanticCandidatesExpression()}
     * @param parentSwitch
     *            the {@link IInterpretedExpressionTargetSwitch} to call
     * @return an {@link IInterpretedExpressionTargetSwitch} that will query
     * 
     *         representation descriptions to determine useful informations
     *         (like the type to consider for Interpreted expressions)
     * @since 0.9.0
     */
    IInterpretedExpressionTargetSwitch createInterpretedExpressionSwitch(EStructuralFeature feature, IInterpretedExpressionTargetSwitch parentSwitch);

    /**
     * Indicates if the given ePackage is handled by this
     * {@link IDiagramDescriptionProvider}. If true, it means that an
     * {@link IInterpretedExpressionTargetSwitch} can be contributed with the
     * {@link IDiagramDescriptionProvider#createInterpretedExpressionSwitch(EStructuralFeature, IInterpretedExpressionTargetSwitch)}
     * method.
     * 
     * @param ePackage
     *            the {@link EPackage} to test
     * @return true if the given ePackage is handled by this
     *         {@link IDiagramDescriptionProvider}, false otherwise
     * @since 0.9.0
     */
    boolean handles(EPackage ePackage);

    /**
     * Indicates if the given Diagram description provider allows the activation
     * of Layouting Mode on handled representations.
     * 
     * @return true if the given Diagram description provider allows the
     *         activation of Layouting Mode on handled representations, false
     *         otherwise.
     * 
     * @since 0.9.0
     */
    boolean allowsLayoutingModeActivation();


    /**
     * Indicates if the given Diagram description provider allows the activation of Layouting Mode on handled
     * representations.
     * 
     * @return true if the given Diagram description provider allows the activation of Layouting Mode on handled
     *         representations, false otherwise.
     * 
     * @since 6.1.0
     */
    boolean allowsVisibilityModeActivation();
    
    /**
     * Indicates if this Diagram description provider allows the Pin/Unpin
     * actions on the specified element.
     * 
     * @param element
     *            the element to check.
     * 
     * @return true if this provider allows the specified element to be Pinned
     *         or Unpinned, false otherwise.
     * 
     * @since 1.0.0M7
     */
    boolean allowsPinUnpin(DDiagramElement element);

    /**
     * Indicates if this Diagram description provider allows the Show/hide
     * actions on the specified element.
     * 
     * @param element
     *            the element to check.
     * 
     * @return true if this provider allows the specified element to be Hiddend
     *         or Revealed, false otherwise.
     * 
     * @since 1.0.0M7
     */
    boolean allowsHideReveal(DDiagramElement element);

    /**
     * Indicates if this Diagram description provider allows the Copy/Paster
     * Layout actions on the specified element.
     * 
     * @param element
     *            the element to check.
     * 
     * @return true if this provider allows the Copy/Paste Layout actions on the
     *         specified element, false otherwise.
     * 
     * @since 1.0.0M7
     * @deprecated since 4.1.0 Use
     *             {@link #allowsCopyPasteFormat(DSemanticDecorator)} instead
     */
    @Deprecated
    boolean allowsCopyPasteLayout(DSemanticDecorator element);

    /**
     * Indicates if this Diagram description provider allows the Copy/Paste
     * Format actions on the specified element (including paste style and paste
     * layout).
     * 
     * @param element
     *            the element to check.
     * 
     * @return true if this provider allows the Copy/Paster Layout actions on
     *         the specified element, false otherwise.
     * 
     * @since 4.1.0
     */
    boolean allowsCopyPasteFormat(DSemanticDecorator element);

    /**
     * Indicates if the given Diagram description provider support the header
     * section in diagram to display header datas. This header section is
     * displayed between the tabbar and the diagram (just above the ruler if
     * this one is displayed).<BR>
     * If this diagram type support header, it should return the data to display
     * through {@link #getHeaderData(DDiagram)} method.
     * 
     * @return true if the given Diagram description provider manage the header
     *         section, false otherwise.
     */
    boolean supportHeader();

    /**
     * Return the list of {@link HeaderData}. This {@link HeaderData} should be
     * sorted according to xLocation and they should not have overlap.
     * 
     * @param diagram
     *            the current diagram
     * 
     * @return the list of header data.
     */
    LinkedList<HeaderData> getHeaderData(DDiagram diagram);

    /**
     * Get the {@link ICollapseUpdater} if this <code>diagram</code> is handle
     * by this {@link IDiagramDescriptionProvider} or an empty {@link Option}
     * otherwise.
     * 
     * @param diagram
     *            the current diagram
     * 
     * @return an optional {@link ICollapseUpdater} for this kind of
     *         <code>diagram</code>.
     */
    Option<? extends ICollapseUpdater> getCollapseUpdater(DDiagram diagram);

    /**
     * Allows the {@link IDiagramDescriptionProvider} to customize the tooltip
     * displayed in VSM editor.
     * 
     * @param toolTipText
     *            the initial tool tip
     * @param eObject
     *            the current eObject
     * @param feature
     *            the current feature
     * 
     * @return a customized tooltip if needed, the initial tooltip otherwise.
     * @since 3.0.0 M5
     */
    String completeToolTipText(String toolTipText, EObject eObject, EStructuralFeature feature);

    /**
     * Allows the {@link IDiagramDescriptionProvider} to customize the tooltip
     * displayed in VSM editor.
     * 
     * @param toolTipText
     *            the initial tool tip
     * @param eObject
     *            the current eObject
     * 
     * @return a customized tooltip if needed, the initial tooltip otherwise.
     * @since 1.0.0 M6
     * @deprecated this method has not access to the feature of eObject. This is
     *             supported in
     *             org.eclipse.sirius.diagram.business.api.diagramtype
     *             .IDiagramDescriptionProvider.completeToolTipText(String,
     *             EObject, EStructuralFeature)
     */
    @Deprecated
    String completeToolTipText(String toolTipText, EObject eObject);
}
