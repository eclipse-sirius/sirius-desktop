/*******************************************************************************
 * Copyright (c) 2008, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.description.tool.spec;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.Messages;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.LayerHelper;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.tool.impl.ContainerDropDescriptionImpl;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Implementation of ContainerDropDescription.
 * 
 * @author ymortier
 */
public class ContainerDropDescriptionSpec extends ContainerDropDescriptionImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ContainerDropDescriptionImpl#getBestMapping(org.eclipse.sirius.diagram.DragAndDropTarget,
     *      org.eclipse.emf.ecore.EObject)
     */
    @Override
    public DiagramElementMapping getBestMapping(final DragAndDropTarget targetContainer, final EObject droppedElement) {
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
                final Collection<DiagramElementMapping> allMappings = new LinkedList<DiagramElementMapping>(containerMapping.getAllContainerMappings());
                allMappings.addAll(getAllMappingsWithSuperMappings(containerMapping));
                allMappings.addAll(containerMapping.getAllBorderedNodeMappings());
                final DDiagram diagram = elementContainer.getParentDiagram();
                final DiagramDescription desc = diagram.getDescription();
                allMappings.addAll(desc.getAllEdgeMappings());
                iterCandidates = allMappings.iterator();
            }
        } else if (targetContainer instanceof DNode) {
            final DNode viewNode = (DNode) targetContainer;
            final NodeMapping nodeMapping = viewNode.getActualMapping();
            final Collection<DiagramElementMapping> allMappings = new LinkedList<DiagramElementMapping>(nodeMapping.getAllBorderedNodeMappings());
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
            final String domainClass = ContainerDropDescriptionSpec.getDomainClass(currentMapping);
            if (this.getMappings().contains(currentMapping) && domainClass != null && !StringUtil.isEmpty(domainClass.trim())) {
                if (extendedPackage.eInstanceOf(droppedElement, domainClass)) {
                    final DDiagram diagram = ContainerDropDescriptionSpec.getDiagram(targetContainer);
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ContainerDropDescriptionImpl#getContainers()
     */
    @Override
    public EList<DragAndDropTargetDescription> getContainers() {
        Resource r = this.eResource();
        if (r == null) {
            throw new UnsupportedOperationException();
        }
        ECrossReferenceAdapter crossReferencer = ECrossReferenceAdapter.getCrossReferenceAdapter(r);
        if (crossReferencer == null) {
            throw new UnsupportedOperationException();
        }
        final List<DragAndDropTargetDescription> dndTargetDescriptions = new LinkedList<DragAndDropTargetDescription>();
        final Collection<Setting> settings = crossReferencer.getInverseReferences(this, true);
        for (final Setting setting : settings) {
            final EObject eReferencer = setting.getEObject();
            final EStructuralFeature eFeature = setting.getEStructuralFeature();
            if (eReferencer instanceof DragAndDropTargetDescription && eFeature.equals(DescriptionPackage.eINSTANCE.getDragAndDropTargetDescription_DropDescriptions())) {
                dndTargetDescriptions.add((DragAndDropTargetDescription) eReferencer);
            }
        }
        return new BasicEList<DragAndDropTargetDescription>(dndTargetDescriptions);
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

    private Collection<DiagramElementMapping> getAllMappingsWithSuperMappings(final ContainerMapping containerMapping) {
        final Collection<DiagramElementMapping> result = new ArrayList<DiagramElementMapping>();
        final Iterator<NodeMapping> it = containerMapping.getAllNodeMappings().iterator();
        while (it.hasNext()) {
            final NodeMapping nM = it.next();
            result.add(nM);
        }
        return result;
    }

    private Collection<DiagramElementMapping> getAllMappingsWithSuperMappings(Collection<Viewpoint> selectedViewpoints, final DiagramDescription desc) {
        final Collection<DiagramElementMapping> result = new ArrayList<DiagramElementMapping>();
        final Iterator<NodeMapping> it = new DiagramComponentizationManager().getAllNodeMappings(selectedViewpoints, desc).iterator();
        while (it.hasNext()) {
            final NodeMapping nM = it.next();
            result.add(nM);
        }
        return result;
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

}
