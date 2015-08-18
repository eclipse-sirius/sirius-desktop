/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.command.builders;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.helper.task.ICommandTask;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.internal.helper.task.operations.SetValueTask;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.diagram.description.tool.ReconnectionKind;
import org.eclipse.sirius.diagram.tools.internal.command.reconnect.ReconnectSourceNodeCommand;
import org.eclipse.sirius.diagram.tools.internal.command.reconnect.SetEdgeActualMappingCommand;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.tools.internal.command.builders.ElementsToSelectTask;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.SetObject;
import org.eclipse.sirius.viewpoint.description.tool.SubVariable;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.sirius.viewpoint.description.tool.impl.ElementSelectVariableImpl;

/**
 * .
 * 
 * @author mchauvin
 */
public class ReconnectionCommandBuilder extends AbstractDiagramCommandBuilder {

    private final ReconnectEdgeDescription tool;

    private final DEdge edge;

    private final EdgeTarget reconnectionSource;

    private final EdgeTarget reconnectionTarget;

    private final EdgeTarget oldTarget;

    private final EdgeTarget oldSource;

    /**
     * Construct a new Reconnection command builder.
     * 
     * @param tool
     *            the reconnection tool
     * @param edge
     *            the edge which is reconnected
     * @param source
     *            the source of the reconnection
     * @param target
     *            the target of the reconnection
     */
    public ReconnectionCommandBuilder(final ReconnectEdgeDescription tool, final DEdge edge, final EdgeTarget source, final EdgeTarget target) {
        this.tool = tool;
        this.edge = edge;
        this.reconnectionSource = source;
        this.reconnectionTarget = target;
        this.oldTarget = edge.getTargetNode();
        this.oldSource = edge.getSourceNode();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.internal.command.builders.CommandBuilder#buildCommand()
     */
    @Override
    public Command buildCommand() {

        Command result = UnexecutableCommand.INSTANCE;

        if (permissionAuthority.canEditInstance(reconnectionSource) && permissionAuthority.canEditInstance(reconnectionTarget) && permissionAuthority.canEditInstance(edge)
        // Layouting mode on diagrams
        // if the ddiagram is in LayoutingMode, we do not allow reconnection
                && !isInLayoutingModeDiagram(edge)) {

            final EObject semanticSource = SiriusUtil.getNearestDecorateSemanticElement(reconnectionSource).getTarget();
            final EObject semanticTarget = SiriusUtil.getNearestDecorateSemanticElement(reconnectionTarget).getTarget();

            final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
            variables.put(tool.getElement(), edge.getTarget());
            variables.put(tool.getSource(), semanticSource);
            variables.put(tool.getSourceView(), reconnectionSource);
            variables.put(tool.getTarget(), semanticTarget);
            variables.put(tool.getTargetView(), reconnectionTarget);
            variables.put(tool.getEdgeView(), edge);

            // we create an hidden variable that will be used to
            // correct reconnection issues
            ICommandTask createOtherEndVariableTask = getOtherEndVariableCreationTask(variables);

            final DCommand cmd = createEnclosingCommand();
            cmd.getTasks().add(createOtherEndVariableTask);
            cmd.getTasks().add(new InitInterpreterVariablesTask(variables, InterpreterUtil.getInterpreter(reconnectionSource), uiCallback));
            Option<DDiagram> parentDiagram = getDDiagram();
            if (tool.getInitialOperation() != null && tool.getInitialOperation().getFirstModelOperations() != null) {
                cmd.getTasks().add(taskHelper.buildTaskFromModelOperation(parentDiagram.get(), edge.getTarget(), tool.getInitialOperation().getFirstModelOperations()));
            }
            final SetObject setObject = ToolFactory.eINSTANCE.createSetObject();
            final String featureName = getReconnectionKindFeatureName();
            setObject.setFeatureName(featureName);
            setObject.setObject(reconnectionTarget);

            Option<DRepresentation> representation = new EObjectQuery(edge).getRepresentation();
            final CommandContext edgeContext = new CommandContext(edge, representation.get());
            cmd.getTasks().add(new SetValueTask(edgeContext, this.modelAccessor, setObject, new EObjectQuery(edge).getSession().getInterpreter()));

            final EdgeMapping newEdgeMapping = getEdgeMappingReconnector();
            addRefreshTask(edge, cmd, tool);
            cmd.getTasks().add(new ElementsToSelectTask(tool, InterpreterUtil.getInterpreter(reconnectionSource), edge.getTarget(), parentDiagram.get()));

            final CompoundCommand cc = new CompoundCommand();
            if (newEdgeMapping != null && !newEdgeMapping.equals(edge.getActualMapping())) {
                cc.append(new SetEdgeActualMappingCommand(editingDomain, edge, newEdgeMapping));
            }
            if (reconnectionSource.equals(oldSource) && (newEdgeMapping != null && !newEdgeMapping.isUseDomainElement() || isEdgeActualMappingUsingDomainElement())) {
                cc.append(new ReconnectSourceNodeCommand(editingDomain, edge, reconnectionTarget, semanticTarget));
            }
            cc.append(cmd);
            result = cc;
        }
        return result;
    }

    private boolean isEdgeActualMappingUsingDomainElement() {
        boolean isEdgeActualMappingUsingDomainElement = false;
        if (edge.getActualMapping() instanceof EdgeMapping) {
            isEdgeActualMappingUsingDomainElement = !((EdgeMapping) edge.getActualMapping()).isUseDomainElement();
        } else if (edge.getActualMapping() instanceof EdgeMappingImport) {
            isEdgeActualMappingUsingDomainElement = !(getImportedMapping((EdgeMappingImport) edge.getActualMapping())).isUseDomainElement();
        }
        return isEdgeActualMappingUsingDomainElement;
    }

    private EdgeMapping getImportedMapping(EdgeMappingImport edgeMapping) {
        if (edgeMapping.getImportedMapping() instanceof EdgeMappingImport) {
            return getImportedMapping((EdgeMappingImport) edgeMapping.getImportedMapping());
        }
        Assert.isTrue(edgeMapping.getImportedMapping() instanceof EdgeMapping, "This should not happen as IEdgeMapping is only extended by EdgeMappingImport and EdgeMapping");
        return (EdgeMapping) edgeMapping.getImportedMapping();
    }

    private String getReconnectionKindFeatureName() {
        String featureName = "sourceNode"; //$NON-NLS-1$
        if (tool.getReconnectionKind() == ReconnectionKind.RECONNECT_TARGET_LITERAL) {
            featureName = "targetNode"; //$NON-NLS-1$
        } else if (tool.getReconnectionKind() == ReconnectionKind.RECONNECT_BOTH_LITERAL && oldTarget == reconnectionSource) {
            featureName = "targetNode"; //$NON-NLS-1$
        }
        return featureName;
    }

    private EdgeMapping getEdgeMappingReconnector() {
        EdgeMapping bestMapping = null;
        DiagramElementMapping sourceMapping = null;
        DiagramElementMapping targetMapping = null;

        if (tool.getReconnectionKind() == ReconnectionKind.RECONNECT_BOTH_LITERAL) {
            if (edge.getTargetNode() == reconnectionSource) {
                sourceMapping = getMapping(reconnectionTarget);
                targetMapping = getMapping(oldTarget);
            } else {
                sourceMapping = getMapping(oldSource);
                targetMapping = getMapping(reconnectionTarget);
            }
        } else if (tool.getReconnectionKind() == ReconnectionKind.RECONNECT_SOURCE_LITERAL) {
            sourceMapping = getMapping(reconnectionTarget);
            targetMapping = getMapping(oldTarget);
        } else if (tool.getReconnectionKind() == ReconnectionKind.RECONNECT_TARGET_LITERAL) {
            sourceMapping = getMapping(oldSource);
            targetMapping = getMapping(reconnectionTarget);
        }
        if (sourceMapping != null && targetMapping != null) {
            final Iterator<EdgeMapping> iterMappings = tool.getMappings().iterator();
            while (iterMappings.hasNext()) {
                final EdgeMapping currentMapping = iterMappings.next();
                if (currentMapping.getTargetMapping().contains(targetMapping) && currentMapping.getSourceMapping().contains(sourceMapping)) {
                    bestMapping = currentMapping;
                    if (currentMapping.equals(edge.getActualMapping())) {
                        break;
                    }
                }
            }
        }

        return bestMapping;
    }

    private DiagramElementMapping getMapping(final EdgeTarget target) {
        if (target instanceof DDiagramElement) {
            return ((DDiagramElement) target).getDiagramElementMapping();
        }
        return null;
    }

    /**
     * Creates a task that will initialize the otherEnd variable to its correct
     * value.
     * 
     * @param variables
     *            the map of variables that will be used during this
     *            reconnection command
     * @return a task that will initialize the otherEnd variable to its correct
     *         value
     */
    protected ICommandTask getOtherEndVariableCreationTask(final Map<AbstractVariable, Object> variables) {
        final OtherEndVariable otherEndVariable = getOtherEndVariable();
        final Object otherEndValue = getOtherEndValue();

        return new AbstractCommandTask() {
            @Override
            public String getLabel() {
                return "Initializing Variables";
            }

            @Override
            public void execute() throws MetaClassNotFoundException, FeatureNotFoundException {
                // We declare the OtherEndVariable as a subVariable of
                // SourceView
                tool.getSourceView().getSubVariables().add(otherEndVariable);
                variables.put(otherEndVariable, otherEndValue);
            }
        };
    }

    /**
     * Returns the otherEnd variable.
     * 
     * @return the otherEnd variable
     */
    protected OtherEndVariable getOtherEndVariable() {
        return new OtherEndVariable();
    }

    /**
     * Returns the value to assign to the otherEnd variable.
     * 
     * @return the end of the Edge before reconnection that is not represented
     *         by the SourceView variable
     */
    protected Object getOtherEndValue() {
        // If the reconnectionSource is equals to the sourceNode of the edge
        if (edge.getSourceNode().equals(reconnectionSource)) {
            // The other end will be the target node
            return edge.getTargetNode();
        } else {
            return edge.getSourceNode();
        }
    }

    /**
     * Variable representing the end of the Edge before reconnection that is not
     * represented by the SourceView variable.
     * 
     * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
     */
    public static class OtherEndVariable extends ElementSelectVariableImpl implements SubVariable {

        private static final String OTHER_END_VARIABLE_NAME = "otherEnd"; //$NON-NLS-1$

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.AbstractVariableImpl#getName()
         */
        @Override
        public String getName() {
            return OTHER_END_VARIABLE_NAME;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getEnclosingCommandLabel() {
        return new IdentifiedElementQuery(tool).getLabel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Option<DDiagram> getDDiagram() {
        return new EObjectQuery(edge).getParentDiagram();
    }
}
