/*****************************************************************************************
 * Copyright (c) 2010, 2011 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *****************************************************************************************/
package org.eclipse.sirius.common.acceleo.mtl.business.internal.interpreter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.acceleo.common.interpreter.CompilationResult;
import org.eclipse.acceleo.model.mtl.Module;
import org.eclipse.acceleo.model.mtl.Query;
import org.eclipse.acceleo.model.mtl.QueryInvocation;
import org.eclipse.acceleo.model.mtl.Template;
import org.eclipse.acceleo.model.mtl.TemplateInvocation;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.ecore.OperationCallExp;
import org.eclipse.ocl.ecore.Variable;
import org.eclipse.ocl.utilities.ASTNode;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

/**
 * Utility class used during the compilation. This class contains methods
 * duplicate from {@link org.eclipse.acceleo.parser.AcceleoParser} and not
 * public. It allows to reduce the size of each resource created for emtl.
 * 
 * @author lredor
 */
public class AcceleoParserTrimUtil {

    /**
     * The operations in the module.
     */
    private ListMultimap<Module, ASTNode> operationsInModule = ArrayListMultimap.create();

    /**
     * Trim the useless data in the given resource, containing the compiled
     * expression of the {@link CompilationResult}, by removing the signature of
     * non used EOperations, Templates and Queries. Method duplicate from
     * {@link org.eclipse.acceleo.parser.AcceleoParser#trimEnvironment(org.eclipse.emf.ecore.resource.ResourceSet)}
     * .
     * 
     * @param compilationResultEnv
     *            The compilation result.
     */
    public void trimEnvironment(CompilationResult compilationResultEnv) {
        if (compilationResultEnv.getCompiledExpression() instanceof EObject) {
            Resource resource = ((EObject) compilationResultEnv.getCompiledExpression()).eResource();
            if (resource != null) {
                trimEnvironment(resource);
            }
        }
    }

    /**
     * Trim the useless data in the given resource by removing the signature of
     * non used EOperations, Templates and Queries. Method duplicate from
     * {@link org.eclipse.acceleo.parser.AcceleoParser#trimEnvironment(org.eclipse.emf.ecore.resource.ResourceSet)}
     * .
     * 
     * @param resource
     *            The resource.
     */
    private void trimEnvironment(Resource resource) {
        List<EObject> contents = resource.getContents();
        if (contents.size() > 0 && contents.get(0) instanceof Module) {
            Module module = (Module) contents.get(0);
            fillCache(module);

            List<EOperation> eOperations = new ArrayList<EOperation>();

            for (int i = 1; i < contents.size(); i++) {
                EObject eObject = contents.get(i);
                TreeIterator<EObject> eAllContents = eObject.eAllContents();
                while (eAllContents.hasNext()) {
                    EObject next = eAllContents.next();
                    if (next instanceof EOperation && !eOperations.contains(next) && !operationUsed((EOperation) next, module, resource)) {
                        eOperations.add((EOperation) next);
                    }
                }
            }

            // Trim environment
            for (EOperation eOperation : eOperations) {
                EcoreUtil.remove(eOperation);
            }

            // We no longer need this
            emptyCache();
        }
    }

    /**
     * Fills the cache with the data from a given module.
     * 
     * @param module
     *            The module
     */
    private void fillCache(Module module) {
        TreeIterator<EObject> eAllContents = module.eAllContents();
        while (eAllContents.hasNext()) {
            EObject next = eAllContents.next();
            if (next.eResource() != module.eResource()) {
                eAllContents.prune();
                continue;
            }
            if (next instanceof OperationCallExp) {
                OperationCallExp operationCallExp = (OperationCallExp) next;
                operationsInModule.put(module, operationCallExp);
            } else if (next instanceof TemplateInvocation) {
                TemplateInvocation templateInvocation = (TemplateInvocation) next;
                operationsInModule.put(module, templateInvocation);
            } else if (next instanceof Template) {
                Template template = (Template) next;
                operationsInModule.put(module, template);
            } else if (next instanceof QueryInvocation) {
                QueryInvocation queryInvocation = (QueryInvocation) next;
                operationsInModule.put(module, queryInvocation);
            } else if (next instanceof Query) {
                Query query = (Query) next;
                operationsInModule.put(module, query);
            }
        }
    }

    /**
     * Clears up the cache we created.
     */
    private void emptyCache() {
        operationsInModule.clear();
    }

    /**
     * Indicates if the given operation represents an element (EOperation,
     * Template or Query) used in the given module.
     * 
     * @param operation
     *            The operation
     * @param module
     *            The module
     * @param resource
     *            The {@link Resource} containing this module.
     * @return <code>true</code> if the operation is usefull, <code>false</code>
     *         otherwise.
     */
    private boolean operationUsed(EOperation operation, Module module, Resource resource) {
        boolean result = false;

        List<ASTNode> nodes = operationsInModule.get(module);
        final Set<Module> importedModules = new HashSet<Module>(module.getImports());
        final Set<Module> extendedModules = new HashSet<Module>(module.getExtends());
        for (int i = 0; i < nodes.size() && !result; i++) {
            final ASTNode astNode = nodes.get(i);
            if (astNode instanceof OperationCallExp) {
                OperationCallExp operationCallExp = (OperationCallExp) astNode;
                if (operationCallExp.getReferredOperation().equals(operation)) {
                    result = true;
                }
            } else if (astNode instanceof TemplateInvocation) {
                final TemplateInvocation templateInvocation = (TemplateInvocation) astNode;
                final EObject definitionContainer = templateInvocation.getDefinition().eContainer();
                if (definitionContainer == module || importedModules.contains(definitionContainer) || extendedModules.contains(definitionContainer)) {
                    result = templateEqual(templateInvocation.getDefinition(), operation);
                }
            } else if (astNode instanceof Template) {
                final EObject astNodeContainer = astNode.eContainer();
                if (astNodeContainer == module || importedModules.contains(astNodeContainer) || extendedModules.contains(astNodeContainer)) {
                    result = templateEqual((Template) astNode, operation);
                    Template template = (Template) astNode;
                    List<Template> overrides = template.getOverrides();
                    for (Template overridenTemplates : overrides) {
                        result = result || templateEqual(overridenTemplates, operation);
                    }
                }
            } else if (astNode instanceof QueryInvocation) {
                final QueryInvocation queryInvocation = (QueryInvocation) astNode;
                final EObject definitionContainer = queryInvocation.getDefinition().eContainer();
                if (definitionContainer == module || importedModules.contains(definitionContainer) || extendedModules.contains(definitionContainer)) {
                    result = queryEqual(queryInvocation.getDefinition(), operation);
                }
            } else if (astNode instanceof Query) {
                final EObject astNodeContainer = astNode.eContainer();
                if (astNodeContainer == module || importedModules.contains(astNodeContainer) || extendedModules.contains(astNodeContainer)) {
                    result = queryEqual((Query) astNode, operation);
                }
            }
        }

        return result;
    }

    /**
     * Indicates if the query matches the given operation.
     * 
     * @param definition
     *            The definition of the query.
     * @param operation
     *            The operation.
     * @return <code>true</code> if the operation represents the query,
     *         <code>false</code> otherwise.
     */
    private boolean queryEqual(Query definition, EOperation operation) {
        boolean result = true;
        result = result && definition.getName().equals(operation.getName());
        List<Variable> parameters = definition.getParameter();
        List<EParameter> eParameters = operation.getEParameters();

        result = result && parameters.size() == eParameters.size();
        if (result) {
            for (int i = 0; i < parameters.size(); i++) {
                Variable variable = parameters.get(i);
                EParameter eParameter = eParameters.get(i);
                if (variable.getName() != null) {
                    result = result && variable.getName().equals(eParameter.getName());
                }
                if (variable.getEType() != null) {
                    result = result && variable.getEType().equals(eParameter.getEType());
                }
            }
        }

        return result;
    }

    /**
     * Indicates if the template matches the given operation.
     * 
     * @param definition
     *            The template definition.
     * @param operation
     *            the operation.
     * @return <code>true</code> if the operation represents the template,
     *         <code>false</code> otherwise.
     */
    private boolean templateEqual(Template definition, EOperation operation) {
        boolean result = true;
        result = result && definition.getName().equals(operation.getName());
        List<Variable> parameters = definition.getParameter();
        List<EParameter> eParameters = operation.getEParameters();

        result = result && parameters.size() == eParameters.size();
        if (result) {
            for (int i = 0; i < parameters.size(); i++) {
                Variable variable = parameters.get(i);
                EParameter eParameter = eParameters.get(i);
                if (variable.getName() != null) {
                    result = result && variable.getName().equals(eParameter.getName());
                }
                if (variable.getEType() != null) {
                    result = result && variable.getEType().equals(eParameter.getEType());
                }
            }
        }

        return result;
    }
}
