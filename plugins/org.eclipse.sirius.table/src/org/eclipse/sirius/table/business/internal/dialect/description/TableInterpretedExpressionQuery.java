/*******************************************************************************
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.business.internal.dialect.description;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.dialect.description.AbstractInterpretedExpressionQuery;
import org.eclipse.sirius.business.api.dialect.description.DefaultInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionTargetSwitch;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.LabelEditTool;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables;

import com.google.common.collect.Sets;

/**
 * Query allowing to get the target domain classes and available packages for a
 * given Interpreted expression. This diagram query will treat all generic
 * description elements and those related to the table concept.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class TableInterpretedExpressionQuery extends AbstractInterpretedExpressionQuery {

    /**
     * Default constructor.
     * 
     * @param target
     *            the target containing the InterpretedExpression (NodeMapping,
     *            ModelOperation...)
     * @param feature
     *            the feature corresponding to the InterpretedExpression to
     *            evaluate ( NodeMapping.semanticCandidatesExpression...)
     */
    public TableInterpretedExpressionQuery(EObject target, EStructuralFeature feature) {
        super(target, feature);
    }

    @Override
    public Collection<EPackage> getPackagesToImport() {
        Collection<EPackage> superResult = super.getPackagesToImport();
        superResult.add(TablePackage.eINSTANCE);
        superResult.add(DescriptionPackage.eINSTANCE);
        return superResult;
    }

    @Override
    protected void initializeTargetSwitch() {
        targetSwitch = new TableGlobalInterpretedTargetSwitch();
    }

    @Override
    protected void appendAllLocalVariableDefinitions(Map<String, Collection<VariableType>> definitions, EObject context) {
        super.appendAllLocalVariableDefinitions(definitions, context);

        // The "Direct edit" and the "Create Cell" tools define numbered
        // variables based on their mask.
        EditMaskVariables emv = null;
        if (context instanceof LabelEditTool) {
            emv = ((LabelEditTool) context).getMask();
            for (AbstractVariable subVar : ((LabelEditTool) context).getVariables()) {
                availableVariables.put(subVar.getName(), getVariableTypeName(subVar));
            }
        } else if (context instanceof CreateCellTool) {
            emv = ((CreateCellTool) context).getMask();
        }
        if (emv != null) {
            appendEditMaskVariables(emv, definitions);
        }
    }

    @Override
    protected Option<EObject> getToolContext() {
        Option<EObject> result = super.getToolContext();
        if (!result.some()) {
            result = new EObjectQuery(target).getFirstAncestorOfType(DescriptionPackage.eINSTANCE.getTableTool());
        }
        return result;
    }

    /**
     * An {@link IInterpretedExpressionTargetSwitch} that delegates to the
     * defaultSwitch or the table specific switch, according to the package of
     * the considered element.
     * 
     * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
     * 
     */
    private class TableGlobalInterpretedTargetSwitch implements IInterpretedExpressionTargetSwitch {

        private final DefaultInterpretedExpressionTargetSwitch defaultSwitch = new DefaultInterpretedExpressionTargetSwitch(feature, this);

        private final TableInterpretedTargetSwitch specificTableSwitch = new TableInterpretedTargetSwitch(feature, this);

        @Override
        public Option<Collection<String>> doSwitch(EObject target, boolean considerFeature) {
            Collection<String> targetTypes = Sets.newLinkedHashSet();
            Option<Collection<String>> expressionTarget = Options.newSome(targetTypes);
            if (target != null) {
                String packageURI = target.eClass().getEPackage().getNsURI();
                // We first try to apply the Table specific switch
                if (DescriptionPackage.eINSTANCE.getNsURI().equals(packageURI)) {
                    specificTableSwitch.setConsiderFeature(considerFeature);
                    expressionTarget = specificTableSwitch.doSwitch(target);
                }
                // If no result has been found, we use the default switch
                if (expressionTarget.some() && expressionTarget.get().isEmpty()) {
                    expressionTarget = defaultSwitch.doSwitch(target, considerFeature);
                }
            }
            return expressionTarget;
        }

        @Override
        public EObject getFirstRelevantContainer(EObject obj) {
            // Can be null only during default switch initialization.
            return defaultSwitch != null ? defaultSwitch.getFirstRelevantContainer(obj) : null;
        }
    }
}
