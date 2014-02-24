/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.description.filter.spec;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.diagram.description.NodeMappingImport;
import org.eclipse.sirius.diagram.description.filter.FilterPackage;
import org.eclipse.sirius.diagram.description.filter.impl.MappingFilterImpl;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * Customizations for the implementation of <code>MappingFilter</code>.
 * 
 * @author cbrun
 * 
 */
public class MappingFilterSpec extends MappingFilterImpl {

    private boolean checkExpression(final EObject element, final EStructuralFeature eFeature) {
        final IInterpreter acceleoInterpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(element);
        boolean valid = true;
        final String expression = (String) this.eGet(eFeature);
        if (expression != null) {
            try {
                valid = acceleoInterpreter.evaluateBoolean(element, expression);
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(this, eFeature, e);
            }
        }
        return valid;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVisible(final DDiagramElement element) {
        Boolean visible = null;
        /*
         * If there is no mapping then we don't use them
         */
        if (getMappings().size() == 0) {
            if (getSemanticConditionExpression() != null && !StringUtil.isEmpty(getSemanticConditionExpression().trim())) {
                if (!checkExpression(element, FilterPackage.eINSTANCE.getMappingFilter_SemanticConditionExpression())) {
                    visible = Boolean.FALSE;
                }
            }
            if (getViewConditionExpression() != null && !StringUtil.isEmpty(getViewConditionExpression().trim())) {
                if (!checkExpression(element, FilterPackage.eINSTANCE.getMappingFilter_ViewConditionExpression())) {
                    visible = Boolean.FALSE;
                }
            }
        } else {
            final RepresentationElementMapping mapping = element.getMapping();
            if (mapping != null && doManage(mapping)) {
                final boolean hasSemanticExpression = getSemanticConditionExpression() != null && !StringUtil.isEmpty(getSemanticConditionExpression().trim());
                final boolean hasViewExpression = getViewConditionExpression() != null && !StringUtil.isEmpty(getViewConditionExpression().trim());
                if (!hasSemanticExpression && !hasViewExpression) {
                    visible = Boolean.FALSE;
                }
                if (hasSemanticExpression) {
                    final EObject semantic = element.getTarget();
                    if (semantic != null && !checkExpression(semantic, FilterPackage.eINSTANCE.getMappingFilter_SemanticConditionExpression())) {
                        visible = Boolean.FALSE;
                    }
                }
                if (hasViewExpression) {
                    if (!checkExpression(element, FilterPackage.eINSTANCE.getMappingFilter_ViewConditionExpression())) {
                        visible = Boolean.FALSE;
                    }
                }

            }
        }
        if (visible == null) {
            visible = Boolean.TRUE;
        }
        return visible.booleanValue();
    }

    private boolean doManage(EObject mapping) {
        boolean result = false;
        if (getMappings().contains(mapping)) {
            result = true;
        } else {
            EObject importedMapping = getImportedMapping(mapping);
            if (importedMapping != null) {
                result = doManage(importedMapping) && isInheritsAncestorFilters(mapping);
            }
        }
        return result;
    }

    private EObject getImportedMapping(EObject mapping) {
        EObject result = null;
        if (mapping instanceof NodeMappingImport) {
            result = ((NodeMappingImport) mapping).getImportedMapping();
        } else if (mapping instanceof ContainerMappingImport) {
            result = ((ContainerMappingImport) mapping).getImportedMapping();
        } else if (mapping instanceof EdgeMappingImport) {
            result = ((EdgeMappingImport) mapping).getImportedMapping();
        }
        return result;
    }

    private boolean isInheritsAncestorFilters(EObject mapping) {
        boolean result = false;
        if (mapping instanceof AbstractMappingImport) {
            result = ((AbstractMappingImport) mapping).isInheritsAncestorFilters();
        } else if (mapping instanceof EdgeMappingImport) {
            result = ((EdgeMappingImport) mapping).isInheritsAncestorFilters();
        }
        return result;
    }
}
