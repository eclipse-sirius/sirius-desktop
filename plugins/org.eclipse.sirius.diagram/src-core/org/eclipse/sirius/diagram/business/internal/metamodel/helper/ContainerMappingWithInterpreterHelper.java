/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.helper;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.Messages;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.api.query.ContainerMappingQuery;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.extensions.IContainerMappingExt;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.AbstractNodeMappingSpecOperations;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.spec.ContainerMappingSpec;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;

/**
 * Utility class to factor customizations for ContainerMapping and related.
 * 
 * @author pcdavid
 */
public final class ContainerMappingWithInterpreterHelper {
    private IInterpreter interpreter;

    /**
     * Create the helper.
     * 
     * @param interpreter
     *            interpreter used to evaluate expressions.
     */
    public ContainerMappingWithInterpreterHelper(IInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    /**
     * Implementation of {@link ContainerMapping#clearDNodesDone()}.
     * 
     * @param self
     *            the container mapping.
     */
    public static void clearDNodesDone(IContainerMappingExt self) {
        self.getViewContainerDone().clear();
    }

    /**
     * Implementation of {@link ContainerMapping#findDNodeFromEObject(EObject)}.
     * 
     * @param self
     *            the container mapping.
     * @param object
     *            the semantic element.
     * @return the node that has been created by this mapping and the specified EObject as semantic element.
     */
    public static EList<DDiagramElement> findDNodeFromEObject(IContainerMappingExt self, EObject object) {
        EList result = self.getViewContainerDone().get(object);
        if (result == null) {
            result = new BasicEList<DDiagramElement>();
        }
        return result;
    }

    /**
     * Implementation of {@link ContainerMapping#addDoneNode(DSemanticDecorator)}.
     * 
     * @param self
     *            the container mapping.
     * @param node
     *            the node to add.
     */
    public static void addDoneNode(IContainerMappingExt self, DSemanticDecorator node) {
        EList<DSemanticDecorator> list = self.getViewContainerDone().get(node.getTarget());
        if (list == null) {
            list = new BasicEList<DSemanticDecorator>();
            self.getViewContainerDone().put(node.getTarget(), list);
        }
        list.add(node);
    }

    /**
     * Get mapping of dropped object.
     * 
     * @param dropTool
     *            tool that describes a Drag & Drop operation
     * @param targetContainer
     *            element that can managed drop requests
     * @param droppedElement
     *            object to drop
     * 
     * @return target mapping which matches
     */
    public static DiagramElementMapping getBestMapping(final ContainerDropDescription dropTool, final DragAndDropTarget targetContainer, final EObject droppedElement) {
        DiagramElementMapping bestMapping = null;
        Iterator<DiagramElementMapping> iterCandidates = null;
        if (targetContainer instanceof DDiagram) {
            final DDiagram diagram = (DDiagram) targetContainer;
            final DiagramDescription desc = diagram.getDescription();

            Collection<Viewpoint> selectedViewpoints = Collections.emptyList();
            if (diagram instanceof DSemanticDiagram) {
                Session session = SessionManager.INSTANCE.getSession(((DSemanticDiagram) diagram).getTarget());
                if (session != null) {
                    selectedViewpoints = session.getSelectedViewpoints(false);
                }
            }

            final Collection<DiagramElementMapping> allMappings = new LinkedList<DiagramElementMapping>(new DiagramComponentizationManager().getAllContainerMappings(selectedViewpoints, desc));
            allMappings.addAll(getAllMappingsWithSuperMappings(selectedViewpoints, desc));
            allMappings.addAll(new DiagramComponentizationManager().getAllEdgeMappings(selectedViewpoints, desc));
            iterCandidates = allMappings.iterator();

        } else if (targetContainer instanceof DDiagramElementContainer) {
            final DDiagramElementContainer elementContainer = (DDiagramElementContainer) targetContainer;
            RepresentationElementMapping mapping = elementContainer.getMapping();
            if (mapping instanceof ContainerMapping) {
                final ContainerMapping containerMapping = (ContainerMapping) mapping;
                final Collection<DiagramElementMapping> allMappings = new LinkedList<DiagramElementMapping>(MappingHelper.getAllContainerMappings(containerMapping));
                allMappings.addAll(getAllMappingsWithSuperMappings(containerMapping));
                allMappings.addAll(MappingHelper.getAllBorderedNodeMappings(containerMapping));
                final DDiagram diagram = elementContainer.getParentDiagram();
                final DiagramDescription desc = diagram.getDescription();
                allMappings.addAll(ContentHelper.getAllEdgeMappings(desc, false));
                iterCandidates = allMappings.iterator();
            }
        } else if (targetContainer instanceof DNode) {
            final DNode viewNode = (DNode) targetContainer;
            final NodeMapping nodeMapping = viewNode.getActualMapping();
            final Collection<DiagramElementMapping> allMappings = new LinkedList<DiagramElementMapping>(MappingHelper.getAllBorderedNodeMappings(nodeMapping));
            iterCandidates = allMappings.iterator();
        }
        if (iterCandidates == null) {
            SiriusPlugin.getDefault().error(MessageFormat.format(Messages.ContainerDropDescriptionSpec_unknownTgtMsg, targetContainer), new RuntimeException());
            return null;
        }
        Session session = SessionManager.INSTANCE.getSession(droppedElement);

        final ModelAccessor extendedPackage = session.getModelAccessor();
        while (iterCandidates.hasNext()) {
            final DiagramElementMapping currentMapping = iterCandidates.next();
            final String domainClass = getDomainClass(currentMapping);
            if (dropTool.getMappings().contains(currentMapping) && domainClass != null && !StringUtil.isEmpty(domainClass.trim())) {
                if (extendedPackage.eInstanceOf(droppedElement, domainClass)) {
                    final DDiagram diagram = getDiagram(targetContainer);
                    if (diagram != null) {
                        DiagramMappingsManager mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, diagram);
                        if (LayerHelper.isInActivatedLayer(mappingManager, diagram, currentMapping)) {
                            bestMapping = currentMapping;
                            break;
                        }
                    }
                }
            }
        }
        return bestMapping;
    }

    private static Collection<DiagramElementMapping> getAllMappingsWithSuperMappings(final ContainerMapping containerMapping) {
        final Collection<DiagramElementMapping> result = new ArrayList<DiagramElementMapping>();
        final Iterator<NodeMapping> it = MappingHelper.getAllNodeMappings(containerMapping).iterator();
        while (it.hasNext()) {
            final NodeMapping nM = it.next();
            result.add(nM);
        }
        return result;
    }

    private static Collection<DiagramElementMapping> getAllMappingsWithSuperMappings(Collection<Viewpoint> selectedViewpoints, final DiagramDescription desc) {
        final Collection<DiagramElementMapping> result = new ArrayList<DiagramElementMapping>();
        final Iterator<NodeMapping> it = new DiagramComponentizationManager().getAllNodeMappings(selectedViewpoints, desc).iterator();
        while (it.hasNext()) {
            final NodeMapping nM = it.next();
            result.add(nM);
        }
        return result;
    }

    private static DDiagram getDiagram(final DragAndDropTarget target) {
        DDiagram diagram = null;
        if (target instanceof DDiagramElement) {
            diagram = ((DDiagramElement) target).getParentDiagram();
        } else if (target instanceof DDiagram) {
            diagram = (DDiagram) target;
        }

        return diagram;
    }

    private static String getDomainClass(final DiagramElementMapping mapping) {
        String domainClass = null;
        if (mapping instanceof EdgeMapping) {
            final EdgeMapping edgeMapping = (EdgeMapping) mapping;
            if (edgeMapping.isUseDomainElement()) {
                domainClass = edgeMapping.getDomainClass();
            }
        } else if (mapping instanceof AbstractNodeMapping) {
            domainClass = ((AbstractNodeMapping) mapping).getDomainClass();
        }
        return domainClass;
    }

    /**
     * Implementation of {@link ContainerMapping#createContainer(EObject, EObject, DDiagram)}.
     * 
     * @param self
     *            the container mapping.
     * @param modelElement
     *            the semantic model element.
     * @param container
     *            the semantic container.
     * @param dDiagram
     *            the viewpoint element.
     * @return the created container.
     */
    public DDiagramElementContainer createContainer(IContainerMappingExt self, EObject modelElement, EObject container, DDiagram dDiagram) {

        DDiagramElementContainer newContainer = null;
        if (new ContainerMappingQuery(self).isListContainer()) {
            newContainer = DiagramFactory.eINSTANCE.createDNodeList();
        } else {
            // Other behaviors : ContainerLayout.FreeForm/VerticalStack
            newContainer = DiagramFactory.eINSTANCE.createDNodeContainer();
            DNodeContainer nodeContainer = DiagramFactory.eINSTANCE.createDNodeContainer();
            nodeContainer.setChildrenPresentation(self.getChildrenPresentation());
            newContainer = nodeContainer;
        }

        // getting the right style description : default or conditional
        final ContainerStyleDescription style = (ContainerStyleDescription) new MappingWithInterpreterHelper(interpreter).getBestStyleDescription(self, modelElement, newContainer, container,
                dDiagram);

        newContainer.setTarget(modelElement);
        newContainer.setActualMapping(self);

        DiagramElementMappingHelper.refreshSemanticElements(self, newContainer, interpreter);

        interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, dDiagram);
        interpreter.setVariable(IInterpreterSiriusVariables.VIEW, newContainer);
        if (style != null && style.getLabelExpression() != null) {
            String name;
            try {
                name = interpreter.evaluateString(modelElement, style.getLabelExpression());
                newContainer.setName(name);
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(style, StylePackage.eINSTANCE.getBasicLabelStyleDescription_LabelExpression(), e);
            }
        }

        /*
         * handling style
         */
        final ContainerStyle containerStyle = (ContainerStyle) new MappingWithInterpreterHelper(interpreter).getBestStyle(self, modelElement, newContainer, container, dDiagram);
        if (containerStyle != null) {
            newContainer.setOwnedStyle(containerStyle);
        }
        if (newContainer.getOwnedStyle() != null) {
            Option<ContainerStyle> noPreviousStyle = Options.newNone();
            new StyleHelper(interpreter).refreshStyle(newContainer.getOwnedStyle(), noPreviousStyle);
        }
        MappingWithInterpreterHelper.addDoneNode(self, newContainer);

        interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
        interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);

        /*
         * Iterate over the border mapping to initialize the border nodes.
         */
        AbstractNodeMappingSpecOperations.createBorderingNodes(self, modelElement, newContainer, Collections.EMPTY_LIST, dDiagram);

        return newContainer;
    }

    /**
     * Implementation of {@link ContainerMapping#updateContainer(DDiagramElementContainer)}.
     * 
     * @param self
     *            the container mapping.
     * @param container
     *            the container to update.
     */
    public void updateContainer(IContainerMappingExt self, DDiagramElementContainer container) {

        // getting the right style description : default or conditional
        final DSemanticDecorator cContainer = (DSemanticDecorator) container.eContainer();
        ContainerStyleDescription style = null;

        DDiagram parentDiagram = container.getParentDiagram();
        if (cContainer != null) {
            style = (ContainerStyleDescription) new MappingWithInterpreterHelper(interpreter).getBestStyleDescription(self, container.getTarget(), container, container, parentDiagram);
        }

        interpreter.setVariable(IInterpreterSiriusVariables.VIEW, container);
        if (style != null && style.getLabelExpression() != null) {

            try {
                final String name = interpreter.evaluateString(container.getTarget(), style.getLabelExpression());
                container.setName(name);
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(style, StylePackage.eINSTANCE.getBasicLabelStyleDescription_LabelExpression(), e);
            }
        }

        if (style != null && style.getTooltipExpression() != null) {
            try {
                interpreter.setVariable(IInterpreterSiriusVariables.VIEW, container);
                final String tooltip = interpreter.evaluateString(container.getTarget(), style.getTooltipExpression());
                container.setTooltipText(tooltip);
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(style, StylePackage.eINSTANCE.getTooltipStyleDescription_TooltipExpression(), e);
            } finally {
                interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);
            }
        }
        interpreter.unSetVariable(IInterpreterSiriusVariables.VIEW);

        // semantic elements
        DiagramElementMappingHelper.refreshSemanticElements(self, container, interpreter);

        /*
         * handling style
         */
        EObject containerVariable = null;
        if (container.eContainer() instanceof DSemanticDecorator) {
            containerVariable = ((DSemanticDecorator) container.eContainer()).getTarget();
        }
        final Style currentStyle = container.getStyle();
        final Style bestStyle = new MappingWithInterpreterHelper(interpreter).getBestStyle(self, container.getTarget(), container, containerVariable, parentDiagram);

        StyleHelper sHelper = new StyleHelper(interpreter);
        if (currentStyle == null) {
            sHelper.setAndRefreshStyle(container, null, bestStyle);
        } else if (currentStyle.getCustomFeatures().isEmpty()) {
            if (currentStyle.getDescription() != bestStyle.getDescription() || !currentStyle.equals(bestStyle)) {
                sHelper.setAndRefreshStyle(container, currentStyle, bestStyle);
            } else {
                sHelper.refreshStyle(currentStyle);
            }
        }

        MappingWithInterpreterHelper.addDoneNode(self, container);
    }

    /**
     * Returns the best style for the given model element.
     * 
     * @param containerMapping
     *            the mapping associated to the element.
     * @param modelElement
     *            the model element for which we want the best style.
     * @param viewVariable
     *            the view variable
     * @param containerVariable
     *            the container variable.
     * @return the best style for the given model element.
     */
    public ContainerStyle getBestStyle(ContainerMapping containerMapping, final EObject modelElement, final EObject viewVariable, final EObject containerVariable) {
        ContainerStyle result = null;
        if (containerMapping instanceof ContainerMappingImport) {
            ContainerMappingImport containerMappingImport = (ContainerMappingImport) containerMapping;
            StyleHelper sHelper = new StyleHelper(SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(modelElement));
            result = sHelper.createContainerStyle(containerMapping.getStyle());
            ContainerMapping importedMappingStyle = containerMappingImport.getImportedMapping();
            if (result == null && importedMappingStyle != null && importedMappingStyle != containerMapping) {
                /*
                 * Here if you are importing a node and if we did not used custom styles, then we want to re-use it's
                 * styles.
                 */
                if (importedMappingStyle instanceof ContainerMappingSpec) {
                    result = (ContainerStyle) new MappingWithInterpreterHelper(interpreter).getBestStyle(importedMappingStyle, modelElement, viewVariable, containerVariable,
                            new EObjectQuery(viewVariable).getParentDiagram().get());
                } else {
                    result = getBestStyle(importedMappingStyle, modelElement, viewVariable, containerVariable);
                }
            }
        }
        return result;
    }

}
